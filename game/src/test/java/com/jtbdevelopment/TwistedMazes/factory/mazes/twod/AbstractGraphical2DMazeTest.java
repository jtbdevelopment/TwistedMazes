package com.jtbdevelopment.TwistedMazes.factory.mazes.twod;

import com.jtbdevelopment.TwistedMazes.state.maze.twod.calculators.DijkstraDistancesCalculator;
import com.jtbdevelopment.TwistedMazes.state.maze.twod.calculators.LongestPathCalculator;
import com.jtbdevelopment.TwistedMazes.state.maze.twod.model.Distances;
import com.jtbdevelopment.TwistedMazes.state.maze.twod.model.rectangle.RectangleGrid;
import com.jtbdevelopment.TwistedMazes.util.png.twod.DistanceGridToPNG;
import com.jtbdevelopment.TwistedMazes.util.png.twod.GridToPNG;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Date: 9/20/18 Time: 7:59 PM
 */
public abstract class AbstractGraphical2DMazeTest {

  protected final Logger logger = LoggerFactory.getLogger(getClass());
  protected RectangleGrid grid = new RectangleGrid(20, 20);
  protected GridToPNG gridToPNG = new GridToPNG();
  protected DistanceGridToPNG distanceGridToPNG = new DistanceGridToPNG();
  protected DijkstraDistancesCalculator distancesCalculator = new DijkstraDistancesCalculator();
  protected LongestPathCalculator longestPathCalculator = new LongestPathCalculator(
    distancesCalculator);


  protected abstract String getFileName();

  protected abstract Generator2DMaze getGenerator();

  @Test
  public void testPrintARegularMaze() throws IOException {
    getGenerator().make2DMaze(grid);
    logger.info(System.lineSeparator() + grid.toString());

    createPNGImages(grid, getFileName());
  }

  private void createPNGImages(final RectangleGrid grid, final String rootName)
    throws IOException {
    Files.write(Paths.get(rootName + ".png"), gridToPNG.convert(grid),
      StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.CREATE);

    Distances distances = longestPathCalculator.calcLongestPath(grid);
    Files.write(Paths.get(rootName + "heatmap.png"), distanceGridToPNG.convert(grid, distances),
      StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.CREATE);
  }
}
