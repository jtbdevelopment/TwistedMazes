package com.jtbdevelopment.TwistedMazes.util.png.twod;

import com.jtbdevelopment.TwistedMazes.state.maze.twod.model.hex.HexGrid;
import com.jtbdevelopment.TwistedMazes.state.maze.twod.model.rectangle.RectangleCell;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.imageio.stream.MemoryCacheImageOutputStream;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class HexGridToPNG implements Converter<HexGrid, byte[]> {

  private static final int CELL_SIZE = 10;
  private static final Color WHITE = Color.WHITE;
  private static final Color BLACK = Color.BLACK;

  protected Color backgroundForCell(final RectangleCell cell) {
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
    /*
[:backgrounds, :walls].each do |mode| each_cell do |cell|
cx = size + 3 * cell.column * a_size cy = b_size + cell.row * height
cy += b_size if cell.column.odd?
      # f/n = far/near
      # n/s/e/w = north/south/east/west
x_fw=(cx- x_nw=(cx- x_ne=(cx+ x_fe=(cx+
#m=middle
size).to_i
a_size).to_i
a_size).to_i
size).to_i
y_n = (cy - b_size).to_i
y_m = cy.to_i
y_s = (cy + b_size).to_i
if mode == :backgrounds
color = background_color_for(cell) if color
    points = [[x_fw, y_m], [x_nw, y_n], [x_ne, y_n],
              [x_fe, y_m], [x_ne, y_s], [x_nw, y_s]]
    img.polygon(points, color, color)
endelse
Displaying a Hex Grid • 119
 Prepared exclusively for J Buscemi
report erratum • discuss
- - - -
45 - - - - 50 - -
img.line(x_fw, y_m, x_nw, y_s, wall) unless cell.southwest img.line(x_fw, y_m, x_nw, y_n, wall) unless cell.northwest img.line(x_nw, y_n, x_ne, y_n, wall) unless cell.north
img.line(x_ne, y_n, x_fe, y_m, wall) unless cell.linked?(cell.northeast) img.line(x_fe, y_m, x_ne, y_s, wall) unless cell.linked?(cell.southeast) img.line(x_ne, y_s, x_nw, y_s, wall) unless cell.linked?(cell.south)
end end
end
     */
    int width = value.getCols() * CELL_SIZE;
    int height = value.getRows() * CELL_SIZE;

    Dimension imgDim = new Dimension(width + 1, height + 1);
    BufferedImage mazeImage = new BufferedImage(imgDim.width, imgDim.height,
        BufferedImage.TYPE_INT_RGB);

    Graphics2D g2d = mazeImage.createGraphics();
    g2d.setColor(WHITE);
    g2d.fillRect(0, 0, imgDim.width, imgDim.height);
    g2d.setColor(BLACK);

    value.stream().forEach(cell -> {
      int x1 = cell.getCol() * CELL_SIZE;
      int y1 = cell.getRow() * CELL_SIZE;
      int x2 = (cell.getCol() + 1) * CELL_SIZE;
      int y2 = (cell.getRow() + 1) * CELL_SIZE;
      g2d.setColor(backgroundForCell(cell));
      g2d.fillRect(x1, y1, x2, y2);
    });
    g2d.setColor(BLACK);
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
