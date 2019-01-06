package com.jtbdevelopment.TwistedMazes.util.png.twod;

import com.jtbdevelopment.TwistedMazes.state.maze.twod.model.polar.PolarCell;
import com.jtbdevelopment.TwistedMazes.state.maze.twod.model.polar.PolarGrid;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.imageio.stream.MemoryCacheImageOutputStream;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class PolarGridToPNG implements Converter<PolarGrid, byte[]> {

  private static final int CELL_SIZE = 10;
  private static final Color WHITE = Color.WHITE;
  private static final Color BLACK = Color.BLACK;

  protected Color backgroundForCell(final PolarCell cell) {
    return WHITE;
  }

  @Override
  public byte[] convert(final PolarGrid value) {
    int height = value.getRows() * CELL_SIZE * 2;
    int width = height;
    int centerX = width / 2;
    int centerY = height / 2;
    Dimension imgDim = new Dimension(width + 1, height + 1);
    BufferedImage mazeImage = new BufferedImage(imgDim.width, imgDim.height,
        BufferedImage.TYPE_INT_RGB);

    Graphics2D g2d = mazeImage.createGraphics();
    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    g2d.setColor(WHITE);
    g2d.fillRect(0, 0, imgDim.width, imgDim.height);
    g2d.setColor(BLACK);

    value.stream().forEach(cell -> {
      if (cell.getRow() == 0) {
        return;
      }
      double theta = 2 * Math.PI / value.getRow(cell.getRow()).size();
      double inner_radius = cell.getRow() * CELL_SIZE;
      double outer_radius = (cell.getRow() + 1) * CELL_SIZE;
      double theta_counterclockwise = cell.getCol() * theta;
      double theta_clockwise = (cell.getCol() + 1) * theta;

      int ax = centerX + ((int) (inner_radius * Math.cos(theta_counterclockwise)));
      int ay = centerY + ((int) (inner_radius * Math.sin(theta_counterclockwise)));
      int bx = centerX + ((int) (outer_radius * Math.cos(theta_counterclockwise)));
      int by = centerY + ((int) (outer_radius * Math.sin(theta_counterclockwise)));
      int cx = centerX + ((int) (inner_radius * Math.cos(theta_clockwise)));
      int cy = centerY + ((int) (inner_radius * Math.sin(theta_clockwise)));
      int dx = centerX + ((int) (outer_radius * Math.cos(theta_clockwise)));
      int dy = centerY + ((int) (outer_radius * Math.sin(theta_clockwise)));
      if (cell.getInward() != null && !cell.isLinked(cell.getInward())) {
        g2d.drawLine(ax, ay, cx, cy);
      }
      if (cell.getCw() != null && !cell.isLinked(cell.getCw())) {
        g2d.drawLine(cx, cy, dx, dy);
      }
    });
    g2d.setColor(BLACK);
    g2d.drawOval(0, 0, width, height);

    try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
      ImageIO.write(mazeImage, "png", new MemoryCacheImageOutputStream(outputStream));
      return outputStream.toByteArray();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
