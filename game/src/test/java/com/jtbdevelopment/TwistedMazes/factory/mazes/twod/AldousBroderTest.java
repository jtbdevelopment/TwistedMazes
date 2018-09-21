package com.jtbdevelopment.TwistedMazes.factory.mazes.twod;

import com.jtbdevelopment.TwistedMazes.state.maze.twod.model.Grid;
import java.io.IOException;
import org.junit.Test;

/**
 * Date: 9/20/18 Time: 7:57 PM
 */
public class AldousBroderTest extends AbstractGraphicalMazeTest {

  private AldousBroder aldousBroder = new AldousBroder();

  @Test
  public void printAnAldousBroderMaze() throws IOException {
    Grid grid = new Grid(20, 20);
    aldousBroder.make2DMaze(grid);
    System.out.print(grid.toString());

    createPNGImages(grid, "aldousbroder");
  }

}
