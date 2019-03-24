package com.jtbdevelopment.TwistedMazes.util.png.twod;

import com.jtbdevelopment.TwistedMazes.state.maze.twod.model.triangle.TriangleCell;
import com.jtbdevelopment.TwistedMazes.state.maze.twod.model.triangle.TriangleGrid;
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
public class TriangleGridToPNG implements Converter<TriangleGrid, byte[]> {

  private static final int CELL_SIZE = 40;
  private static final Color WHITE = Color.WHITE;
  private static final Color BLACK = Color.BLACK;

  protected Color backgroundForCell(final TriangleCell cell) {
    return WHITE;
  }

  @Override
  public byte[] convert(final TriangleGrid value) {
    double halfWidth = CELL_SIZE / 2.0;
    double height = (((double) CELL_SIZE) * Math.sqrt(3)) / 2.0;
    double halfHeight = height / 2.0;
    int imageWidth = (int) Math.round(((value.getCols() + 1) * CELL_SIZE) / 2.0);
    int imageHeight = (int) Math.round(value.getRows() * height);

    Dimension imgDim = new Dimension(imageWidth + 1, imageHeight + 3);
    BufferedImage mazeImage = new BufferedImage(imgDim.width, imgDim.height,
        BufferedImage.TYPE_INT_RGB);

    Graphics2D g2d = mazeImage.createGraphics();
    g2d.setColor(WHITE);
    g2d.fillRect(0, 0, imgDim.width, imgDim.height);
    g2d.setColor(BLACK);

    Stream.of(false).forEach(background -> {
      value.stream().forEach(cell -> {
        double cx = halfWidth + cell.getCol() * halfWidth;
        double cy = halfHeight + cell.getRow() * height;

        int westX = (int) Math.round(cx - halfWidth);
        int midX = (int) Math.round(cx);
        int eastX = (int) Math.round(cx + halfWidth);
        int apexY;
        int baseY;

        if (cell.isUpright()) {
          apexY = (int) Math.round(cy - halfHeight);
          baseY = (int) Math.round(cy + halfHeight);
        } else {
          apexY = (int) Math.round(cy + halfHeight);
          baseY = (int) Math.round(cy - halfHeight);
        }

        if (cell.getWest() == null) {
          g2d.drawLine(westX, baseY, midX, apexY);
        }
        if (!cell.isLinked(cell.getEast())) {
          g2d.drawLine(eastX, baseY, midX, apexY);
        }

        boolean noSouth = cell.isUpright() && cell.getSouth() == null;
        boolean noSouthLink = (!cell.isUpright()) && (!cell.isLinked(cell.getNorth()));
        if (noSouth || noSouthLink) {
          g2d.drawLine(eastX, baseY, westX, baseY);
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
