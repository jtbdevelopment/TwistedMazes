package com.jtbdevelopment.TwistedMazes.factory.mazes.twod;

import com.jtbdevelopment.TwistedMazes.state.maze.twod.calculators.DijkstraDistancesCalculator;
import com.jtbdevelopment.TwistedMazes.state.maze.twod.calculators.LongestPathCalculator;
import com.jtbdevelopment.TwistedMazes.state.maze.twod.model.Distances;
import com.jtbdevelopment.TwistedMazes.state.maze.twod.model.Grid;
import com.jtbdevelopment.TwistedMazes.util.png.twod.DistanceGridToPNG;
import com.jtbdevelopment.TwistedMazes.util.png.twod.GridToPNG;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * Date: 9/20/18 Time: 7:59 PM
 */
public class AbstractGraphicalMazeTest {

  private GridToPNG gridToPNG = new GridToPNG();
  private DistanceGridToPNG distanceGridToPNG = new DistanceGridToPNG();
  private DijkstraDistancesCalculator distancesCalculator = new DijkstraDistancesCalculator();
  private LongestPathCalculator longestPathCalculator = new LongestPathCalculator(
    distancesCalculator);

  protected void createPNGImages(final Grid grid, final String rootName)
    throws IOException {
    Files.write(Paths.get(rootName + ".png"), gridToPNG.convert(grid),
      StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.CREATE);

    Distances distances = longestPathCalculator.calcLongestPath(grid);
    Files.write(Paths.get(rootName + "heatmap.png"), distanceGridToPNG.convert(grid, distances),
      StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.CREATE);
  }
}
