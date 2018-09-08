package com.jtbdevelopment.TwistedMazes.state.maze.twod;

import com.jtbdevelopment.TwistedMazes.factory.mazes.twod.BinaryTree;
import org.junit.Test;

/**
 * Date: 9/8/18 Time: 6:26 AM
 */
public class DistancesGridOverlayTest {

  private BinaryTree tree = new BinaryTree();
  private DijkstraDistancesCalculator calculator = new DijkstraDistancesCalculator();

  @Test
  public void testDrawingGridWithOverlay() {
    Grid grid = new Grid(5, 5);
    tree.make2DMaze(grid);
    Distances distances = calculator.computeDistances(grid.getCell(0, 0));

    System.out.print(new DistancesGridOverlay(grid, distances).toString());
  }

  @Test
  public void testDrawingGridWithOverlayPathFromTopLeftToBottomRight() {
    Grid grid = new Grid(5, 5);
    tree.make2DMaze(grid);
    Distances distances = calculator.computeDistances(grid.getCell(0, 0));
    Distances pathDistances = distances.pathTo(grid.getCell(4, 0));
    System.out.print(new DistancesGridOverlay(grid, distances).toString());
    System.out.print(new DistancesGridOverlay(grid, pathDistances).toString());
  }
}
