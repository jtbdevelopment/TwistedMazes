package com.jtbdevelopment.TwistedMazes.state.maze.twod.masking;

import static org.junit.Assert.assertEquals;

import com.jtbdevelopment.TwistedMazes.state.maze.twod.model.MaskedGrid;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;

/**
 * Date: 11/3/18 Time: 4:58 PM
 */
public class MaskedGridPNGResourceReaderTest {

  private MaskedGridPNGResourceReader reader = new MaskedGridPNGResourceReader();

  @Test
  public void testReadsSampleASCIIFileAndMakesGrid() {
    ClassPathResource file = new ClassPathResource("/masks/samplepngmask.png");
    MaskedGrid maskedGrid = reader.readMaskedGrid(file);
    assertEquals(100, maskedGrid.getRows());
    assertEquals(100, maskedGrid.getCols());
  }

}
