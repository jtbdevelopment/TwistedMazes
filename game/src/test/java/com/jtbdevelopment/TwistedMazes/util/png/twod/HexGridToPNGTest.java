package com.jtbdevelopment.TwistedMazes.util.png.twod;

import com.jtbdevelopment.TwistedMazes.factory.mazes.twod.RecursiveBacktracker;
import com.jtbdevelopment.TwistedMazes.state.maze.twod.model.hex.HexGrid;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import org.junit.Test;

/**
 * Date: 1/19/19 Time: 4:32 PM
 */
public class HexGridToPNGTest {

  private HexGridToPNG hexGridToPNG = new HexGridToPNG();
  private RecursiveBacktracker recursiveBacktracker = new RecursiveBacktracker();

  @Test
  public void testDrawingPolarGrid() throws IOException {
    HexGrid grid = new HexGrid(8, 8);

    Files.write(Paths.get("hexgrid.png"), hexGridToPNG.convert(grid),
      StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.CREATE);

    recursiveBacktracker.make2DMaze(grid);

    Files.write(Paths.get("hexgrid-rbt.png"), hexGridToPNG.convert(grid),
      StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.CREATE);
  }

}
