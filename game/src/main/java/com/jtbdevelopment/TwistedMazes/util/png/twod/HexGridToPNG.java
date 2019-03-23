package com.jtbdevelopment.TwistedMazes.util.png.twod;

import com.jtbdevelopment.TwistedMazes.state.maze.twod.model.hex.HexCell;
import com.jtbdevelopment.TwistedMazes.state.maze.twod.model.hex.HexGrid;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.stream.Stream;
import javax.imageio.ImageIO;
import javax.imageio.stream.MemoryCacheImageOutputStream;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class HexGridToPNG implements Converter<HexGrid, byte[]> {

  private static final int CELL_SIZE = 10;
  private static final Color WHITE = Color.WHITE;
  private static final Color BLACK = Color.BLACK;

  protected Color backgroundForCell(final HexCell cell) {
    return WHITE;
  }

  @Override
  public byte[] convert(final HexGrid value) {
    double aSize = CELL_SIZE / 2.0;
    double bSize = CELL_SIZE * Math.sqrt(3) / 2.0;
    double width = CELL_SIZE * 2;
    double height = bSize * 2;

    int imageWidth = (int) (3 * aSize * value.getCols() + aSize + 0.5);
    int imageHeigth = (int) (value.getRows() * height + bSize + 0.5);

    Dimension imgDim = new Dimension(imageWidth + 1, imageHeigth + 1);
    BufferedImage mazeImage = new BufferedImage(imgDim.width, imgDim.height,
        BufferedImage.TYPE_INT_RGB);

    Graphics2D g2d = mazeImage.createGraphics();
    g2d.setColor(WHITE);
    g2d.fillRect(0, 0, imgDim.width, imgDim.height);
    g2d.setColor(BLACK);

    Stream.of(false).forEach(background -> {
      value.stream().forEach(cell -> {
        double cx = CELL_SIZE + (3 * cell.getCol() * aSize);
        double cy = bSize + (cell.getRow() * height);
        cy += cell.getCol() % 2 == 0 ? 0 : bSize;

        // f/n = far/near
        // n/s/e/w = north/south/east/west
        // m = middle
        int x_fw = (int) Math.round(cx - CELL_SIZE);
        int x_nw = (int) Math.round(cx - aSize);
        int x_ne = (int) Math.round(cx + aSize);
        int x_fe = (int) Math.round(cx + CELL_SIZE);
        int y_n = (int) Math.round(cy - bSize);
        int y_s = (int) Math.round(cy + bSize);
        int y_m = (int) Math.round(cy);
        if (background) {
          g2d.setColor(backgroundForCell(cell));
          g2d.fillPolygon(
              new int[]{x_fw, x_nw, x_ne, x_fe, x_ne, x_nw},
              new int[]{y_m, y_n, y_n, y_m, y_s, y_s},
              6);
        } else {
          g2d.setColor(BLACK);
          if (cell.getSouthwest() == null) {
            g2d.drawLine(x_fw, y_m, x_nw, y_s);
          }
          if (cell.getNorthwest() == null) {
            g2d.drawLine(x_fw, y_m, x_nw, y_n);
          }
          if (cell.getNorth() == null) {
            g2d.drawLine(x_nw, y_n, x_ne, y_n);
          }
          if (!cell.isLinked(cell.getNortheast())) {
            g2d.drawLine(x_ne, y_n, x_fe, y_m);
          }
          if (!cell.isLinked(cell.getSoutheast())) {
            g2d.drawLine(x_fe, y_m, x_ne, y_s);
          }
          if (!cell.isLinked(cell.getSouth())) {
            g2d.drawLine(x_ne, y_s, x_nw, y_s);
          }
        }
      });

    });

    try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
      ImageIO.write(mazeImage, "png", new MemoryCacheImageOutputStream(outputStream));
      return outputStream.toByteArray();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
