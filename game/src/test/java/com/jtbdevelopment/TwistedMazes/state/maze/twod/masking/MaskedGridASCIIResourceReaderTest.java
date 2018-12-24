package com.jtbdevelopment.TwistedMazes.state.maze.twod.masking;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.jtbdevelopment.TwistedMazes.state.maze.twod.model.rectangle.MaskedRectangleGrid;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;

/**
 * Date: 11/3/18 Time: 4:16 PM
 */
public class MaskedGridASCIIResourceReaderTest {

  private MaskedGridASCIIResourceReader reader = new MaskedGridASCIIResourceReader();

  @Test
  public void testReadsSampleASCIIFileAndMakesGrid() {
    ClassPathResource file = new ClassPathResource("/masks/sampleasciimask.txt");
    MaskedRectangleGrid maskedGrid = reader.readMaskedGrid(file);
    assertEquals(10, maskedGrid.getRows());
    assertEquals(10, maskedGrid.getCols());
    assertFalse(maskedGrid.getMask().isEnabled(0, 0));
    assertTrue(maskedGrid.getMask().isEnabled(0, 1));
    assertFalse(maskedGrid.getMask().isEnabled(1, 4));
    assertFalse(maskedGrid.getMask().isEnabled(1, 5));
  }
}
