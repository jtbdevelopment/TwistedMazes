package com.jtbdevelopment.TwistedMazes.state.maze.twod.masking;

import com.jtbdevelopment.TwistedMazes.state.maze.twod.model.Mask;
import com.jtbdevelopment.TwistedMazes.state.maze.twod.model.MaskedGrid;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

/**
 * Date: 11/3/18 Time: 4:00 PM
 */
@Component
public class MaskedGridASCIIFileReader {

  public MaskedGrid readMaskedGrid(final Resource resource) {
    try {
      List<String> lines = Files.readAllLines(resource.getFile().toPath());
      assert (lines.size() > 0);
      int cols = lines.get(0).length();
      if (!lines.stream().allMatch(l -> l.length() == cols)) {
        throw new IllegalArgumentException("all lines should be same length");
      }
      Mask mask = new Mask(lines.size(), cols);
      for (int row = 0; row < lines.size(); ++row) {
        char[] line = lines.get(row).toCharArray();
        for (int col = 0; col < cols; ++col) {
          mask.setEnabled(row, col, line[col] != 'X');
        }
      }
      return new MaskedGrid(mask);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
