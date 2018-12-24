package com.jtbdevelopment.TwistedMazes.state.maze.twod.masking;

import com.jtbdevelopment.TwistedMazes.state.maze.twod.model.rectangle.MaskedRectangleGrid;
import com.jtbdevelopment.TwistedMazes.state.maze.twod.model.rectangle.RectangleMask;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

/**
 * Date: 11/3/18 Time: 4:42 PM
 */
@Component
public class MaskedGridPNGResourceReader implements MaskedGridResourceReader {

  @Override
  public MaskedRectangleGrid readMaskedGrid(final Resource resource) {
    try {
      BufferedImage image = ImageIO.read(resource.getInputStream());
      RectangleMask mask = new RectangleMask(image.getHeight(), image.getWidth());
      for (int row = 0; row < mask.getRows(); ++row) {
        for (int col = 0; col < mask.getCols(); ++col) {
          int rgb = image.getRGB(col, row);
          if (rgb == Color.BLACK.getRGB()) {
            mask.disable(row, col);
          }
        }
      }
      return new MaskedRectangleGrid(mask);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
