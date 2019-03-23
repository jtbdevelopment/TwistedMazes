package com.jtbdevelopment.TwistedMazes.util.png.twod;

import com.jtbdevelopment.TwistedMazes.factory.mazes.twod.RecursiveBacktracker;
import com.jtbdevelopment.TwistedMazes.state.maze.twod.model.polar.PolarGrid;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import org.junit.Test;

/**
 * Date: 11/17/18 Time: 2:01 PM
 */
public class PolarGridToPNGTest {

  private PolarGridToPNG polarGridToPNG = new PolarGridToPNG();
  private RecursiveBacktracker recursiveBacktracker = new RecursiveBacktracker();

  @Test
  public void testDrawingPolarGrid() throws IOException {
    PolarGrid grid = new PolarGrid(8);

    Files.write(Paths.get("polargrid.png"), polarGridToPNG.convert(grid),
        StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.CREATE);

    recursiveBacktracker.make2DMaze(grid);

    Files.write(Paths.get("polargrid-rbt.png"), polarGridToPNG.convert(grid),
        StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.CREATE);

  }
}
