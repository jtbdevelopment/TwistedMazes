package com.jtbdevelopment.TwistedMazes.factory.mazes.twod;

import com.jtbdevelopment.TwistedMazes.state.maze.twod.model.Grid;
import java.io.IOException;
import org.junit.Test;

/**
 * Date: 9/21/18 Time: 6:58 PM
 */
public class RecursiveBacktrackerTest extends AbstractGraphicalMazeTest {

  private RecursiveBacktracker backtracker = new RecursiveBacktracker();

  @Test
  public void printABinaryTreeMaze() throws IOException {
    Grid grid = new Grid(15, 15);
    backtracker.make2DMaze(grid);
    System.out.print(grid.toString());

    createPNGImages(grid, "recursivebacktracker");
  }

}
