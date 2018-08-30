package com.jtbdevelopment.TwistedMazes.util.png.twod;

import com.jtbdevelopment.TwistedMazes.state.maze.twod.Grid;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import javax.imageio.stream.MemoryCacheImageOutputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Component
public class GridToPNG implements Converter<Grid, byte[]> {

  private static final int CELL_SIZE = 10;

  @Override
  public byte[] convert(final Grid value) {
    int width = value.getCols() * CELL_SIZE;
    int height = value.getRows() * CELL_SIZE;

    Dimension imgDim = new Dimension(width + 1, height + 1);
    BufferedImage mazeImage = new BufferedImage(imgDim.width, imgDim.height, BufferedImage.TYPE_INT_RGB);


    Graphics2D g2d = mazeImage.createGraphics();
    g2d.setColor(Color.WHITE);
    g2d.fillRect(0, 0, imgDim.width, imgDim.height);
    g2d.setColor(Color.BLACK);
    //BasicStroke bs = new BasicStroke(1);
    //g2d.setStroke(bs);

    value.stream().forEach(cell -> {
      int x1 = cell.getCol() * CELL_SIZE;
      int y1 = cell.getRow() * CELL_SIZE;
      int x2 = (cell.getCol() + 1) * CELL_SIZE;
      int y2 = (cell.getRow() + 1) * CELL_SIZE;
      if (cell.getNorth() == null) {
        g2d.drawLine(x1, y1, x2, y1);
      }
      if (cell.getWest() == null) {
        g2d.drawLine(x1, y1, x1, y2);
      }

      if (!cell.isLinked(cell.getSouth())) {
        g2d.drawLine(x1, y2, x2, y2);
      }
      if (!cell.isLinked(cell.getEast())) {
        g2d.drawLine(x2, y1, x2, y2);
      }
    });

    try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
      ImageIO.write(mazeImage, "png", new MemoryCacheImageOutputStream(outputStream));
      return outputStream.toByteArray();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
