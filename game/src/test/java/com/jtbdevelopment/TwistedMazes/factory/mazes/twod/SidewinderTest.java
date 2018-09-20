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
import org.junit.Test;

/**
 * Date: 8/28/18 Time: 7:03 PM
 */
public class SidewinderTest {

  private GridToPNG gridToPNG = new GridToPNG();
  private DistanceGridToPNG distanceGridToPNG = new DistanceGridToPNG();
  private DijkstraDistancesCalculator distancesCalculator = new DijkstraDistancesCalculator();
  private LongestPathCalculator longestPathCalculator = new LongestPathCalculator(
      distancesCalculator);
  private Sidewinder sidewinder = new Sidewinder();

  @Test
  public void runSidewinderAlgo() throws IOException {
    Grid grid = new Grid(25, 25);
    sidewinder.make2DMaze(grid);
    System.out.print(grid.toString());
    Files.write(Paths.get("sidewinder.png"), gridToPNG.convert(grid),
        StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.CREATE);

    Distances distances = longestPathCalculator.calcLongestPath(grid);
    Files.write(Paths.get("sidewindercolored.png"), distanceGridToPNG.convert(grid, distances),
        StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.CREATE);
  }
}
