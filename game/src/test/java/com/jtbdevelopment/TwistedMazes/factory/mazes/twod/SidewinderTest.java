package com.jtbdevelopment.TwistedMazes.factory.mazes.twod;

import com.jtbdevelopment.TwistedMazes.state.maze.twod.model.Grid;
import java.io.IOException;
import org.junit.Test;

/**
 * Date: 8/28/18 Time: 7:03 PM
 */
public class SidewinderTest extends AbstractGraphicalMazeTest {

  private Sidewinder sidewinder = new Sidewinder();

  @Test
  public void runSidewinderAlgo() throws IOException {
    Grid grid = new Grid(25, 25);
    sidewinder.make2DMaze(grid);
    System.out.print(grid.toString());

    createPNGImages(grid, "sidewinder");
  }

}
