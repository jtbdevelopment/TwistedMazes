package com.jtbdevelopment.TwistedMazes.factory.mazes.twod;

import com.jtbdevelopment.TwistedMazes.state.maze.twod.Grid;
import com.jtbdevelopment.TwistedMazes.util.png.twod.GridToPNG;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * Date: 8/28/18
 * Time: 7:03 PM
 */
public class SidewinderTest {
  private GridToPNG gridToPNG = new GridToPNG();
  private Sidewinder sidewinder = new Sidewinder();

  @Test
  public void runSidewinderAlgo() throws IOException {
    Grid grid = new Grid(4, 4);
    sidewinder.make2DMaze(grid);
    System.out.print(grid.toString());
    Files.write(Paths.get("sidewinder.png"), gridToPNG.convert(grid), StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.CREATE);
  }
}
