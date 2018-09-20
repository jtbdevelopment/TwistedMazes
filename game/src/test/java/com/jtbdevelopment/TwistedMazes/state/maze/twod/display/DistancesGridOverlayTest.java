package com.jtbdevelopment.TwistedMazes.state.maze.twod.display;

import com.jtbdevelopment.TwistedMazes.factory.mazes.twod.BinaryTree;
import com.jtbdevelopment.TwistedMazes.state.maze.twod.calculators.DijkstraDistancesCalculator;
import com.jtbdevelopment.TwistedMazes.state.maze.twod.calculators.LongestPathCalculator;
import com.jtbdevelopment.TwistedMazes.state.maze.twod.calculators.ShortestDistanceCalculator;
import com.jtbdevelopment.TwistedMazes.state.maze.twod.model.Distances;
import com.jtbdevelopment.TwistedMazes.state.maze.twod.model.Grid;
import org.junit.Test;

/**
 * Date: 9/8/18 Time: 6:26 AM
 */
public class DistancesGridOverlayTest {

  private BinaryTree tree = new BinaryTree();
  private DijkstraDistancesCalculator distancesCalculator = new DijkstraDistancesCalculator();
  private ShortestDistanceCalculator shortestDistanceCalculator = new ShortestDistanceCalculator();
  private LongestPathCalculator longestPathCalculator = new LongestPathCalculator(
    distancesCalculator);

  @Test
  public void testDrawingGridWithOverlay() {
    Grid grid = new Grid(5, 5);
    tree.make2DMaze(grid);
    Distances distances = distancesCalculator.computeDistances(grid.getCell(0, 0));

    System.out.print(new DistancesGridOverlay(grid, distances).toString());
  }

  @Test
  public void testDrawingGridWithOverlayPathFromTopLeftToBottomRight() {
    Grid grid = new Grid(5, 5);
    tree.make2DMaze(grid);
    Distances distances = distancesCalculator.computeDistances(grid.getCell(0, 0));
    Distances pathDistances = shortestDistanceCalculator.pathTo(distances, grid.getCell(4, 0));
    System.out.print(new DistancesGridOverlay(grid, distances).toString());
    System.out.print(new DistancesGridOverlay(grid, pathDistances).toString());
  }

  @Test
  public void testDrawingWithMaxDistancePath() {
    Grid grid = new Grid(5, 5);
    tree.make2DMaze(grid);
    Distances distances = longestPathCalculator.calcLongestPath(grid);
    Distances pathDistances = shortestDistanceCalculator
      .pathTo(distances, distances.maxDistanceCell());
    System.out.print(new DistancesGridOverlay(grid, distances).toString());
    System.out.print(new DistancesGridOverlay(grid, pathDistances).toString());
  }
}
