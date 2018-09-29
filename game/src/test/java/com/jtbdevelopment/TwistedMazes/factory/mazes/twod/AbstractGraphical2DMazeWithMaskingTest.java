package com.jtbdevelopment.TwistedMazes.factory.mazes.twod;

import com.jtbdevelopment.TwistedMazes.state.maze.twod.model.Distances;
import com.jtbdevelopment.TwistedMazes.state.maze.twod.model.Grid;
import com.jtbdevelopment.TwistedMazes.state.maze.twod.model.Mask;
import com.jtbdevelopment.TwistedMazes.state.maze.twod.model.MaskedGrid;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Date: 9/20/18 Time: 7:59 PM
 */
public abstract class AbstractGraphical2DMazeWithMaskingTest extends AbstractGraphical2DMazeTest {

  private final Logger logger = LoggerFactory.getLogger(getClass());
  protected Grid grid = new Grid(20, 20);
  protected MaskedGrid maskedGrid;

  @Before
  public void setup() {
    Mask mask = new Mask(20, 20);
    mask.disable(0, 0);
    mask.disable(2, 2);
    mask.disable(4, 4);
    maskedGrid = new MaskedGrid(mask);
  }


  @Test
  public void testPrintAMaskedMaze() throws IOException {
    getGenerator().make2DMaze(maskedGrid);
    logger.info(System.lineSeparator() + maskedGrid.toString());

    createPNGImages(maskedGrid, getFileName() + "masked");
  }

  private void createPNGImages(final Grid grid, final String rootName)
    throws IOException {
    Files.write(Paths.get(rootName + ".png"), gridToPNG.convert(grid),
      StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.CREATE);

    Distances distances = longestPathCalculator.calcLongestPath(grid);
    Files.write(Paths.get(rootName + "heatmap.png"), distanceGridToPNG.convert(grid, distances),
      StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.CREATE);
  }
}
