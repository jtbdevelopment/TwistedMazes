package com.jtbdevelopment.TwistedMazes.factory.mazes.twod;

import com.jtbdevelopment.TwistedMazes.state.maze.twod.model.Grid;
import java.io.IOException;
import org.junit.Test;

/**
 * Date: 9/20/18 Time: 8:23 PM
 */
public class WilsonsTest extends AbstractGraphicalMazeTest {

  private Wilsons wilsons = new Wilsons();

  @Test
  public void printAnAldousBroderMaze() throws IOException {
    Grid grid = new Grid(20, 20);
    wilsons.make2DMaze(grid);
    System.out.print(grid.toString());

    createPNGImages(grid, "wilsons");
  }

}
