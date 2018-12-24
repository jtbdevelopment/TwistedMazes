package com.jtbdevelopment.TwistedMazes.factory.mazes.twod;

import com.jtbdevelopment.TwistedMazes.state.maze.twod.masking.MaskedGridASCIIResourceReader;
import com.jtbdevelopment.TwistedMazes.state.maze.twod.masking.MaskedGridPNGResourceReader;
import com.jtbdevelopment.TwistedMazes.state.maze.twod.model.Distances;
import com.jtbdevelopment.TwistedMazes.state.maze.twod.model.rectangle.MaskedRectangleGrid;
import com.jtbdevelopment.TwistedMazes.state.maze.twod.model.rectangle.RectangleGrid;
import com.jtbdevelopment.TwistedMazes.state.maze.twod.model.rectangle.RectangleMask;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;

/**
 * Date: 9/20/18 Time: 7:59 PM
 */
public abstract class AbstractGraphical2DMazeWithMaskingTest extends AbstractGraphical2DMazeTest {

  private final Logger logger = LoggerFactory.getLogger(getClass());
  protected MaskedRectangleGrid manualMaskedGrid;

  @Before
  public void setup() {
    RectangleMask mask = new RectangleMask(20, 20);
    mask.disable(0, 0);
    mask.disable(2, 2);
    mask.disable(4, 4);
    manualMaskedGrid = new MaskedRectangleGrid(mask);
  }


  @Test
  public void testPrintAMaskedMaze() throws IOException {
    getGenerator().make2DMaze(manualMaskedGrid);
    logger.info(System.lineSeparator() + manualMaskedGrid.toString());

    createPNGImages(manualMaskedGrid, getFileName() + "masked");
  }

  private void createPNGImages(final RectangleGrid grid, final String rootName)
    throws IOException {
    Files.write(Paths.get(rootName + ".png"), gridToPNG.convert(grid),
      StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.CREATE);

    Distances distances = longestPathCalculator.calcLongestPath(grid);
    Files.write(Paths.get(rootName + "heatmap.png"), distanceGridToPNG.convert(grid, distances),
      StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.CREATE);
  }

  @Test
  public void testPringAMaskedtMazeUsingSampleASCII() throws IOException {
    MaskedGridASCIIResourceReader reader = new MaskedGridASCIIResourceReader();
    ClassPathResource file = new ClassPathResource("/masks/sampleasciimask.txt");
    MaskedRectangleGrid maskedGrid = reader.readMaskedGrid(file);

    getGenerator().make2DMaze(maskedGrid);
    logger.info(System.lineSeparator() + maskedGrid.toString());

    createPNGImages(maskedGrid, getFileName() + "sampleasciimasked");
  }

  @Test
  public void testPringAMaskedMazeUsingSamplePNG() throws IOException {
    MaskedGridPNGResourceReader reader = new MaskedGridPNGResourceReader();
    ClassPathResource file = new ClassPathResource("/masks/samplepngmask.png");
    MaskedRectangleGrid maskedGrid = reader.readMaskedGrid(file);

    getGenerator().make2DMaze(maskedGrid);
    logger.info(System.lineSeparator() + maskedGrid.toString());

    createPNGImages(maskedGrid, getFileName() + "samplepngmasked");
  }
}
