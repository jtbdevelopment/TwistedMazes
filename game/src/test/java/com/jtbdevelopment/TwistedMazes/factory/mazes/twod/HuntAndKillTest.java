package com.jtbdevelopment.TwistedMazes.factory.mazes.twod;

import com.jtbdevelopment.TwistedMazes.state.maze.twod.model.Grid;
import java.io.IOException;
import org.junit.Test;

/**
 * Date: 9/21/18 Time: 6:44 PM
 */
public class HuntAndKillTest extends AbstractGraphicalMazeTest {

  private HuntAndKill huntAndKill = new HuntAndKill();

  @Test
  public void printABinaryTreeMaze() throws IOException {
    Grid grid = new Grid(15, 15);
    huntAndKill.make2DMaze(grid);
    System.out.print(grid.toString());

    createPNGImages(grid, "huntandkill");
  }
}
