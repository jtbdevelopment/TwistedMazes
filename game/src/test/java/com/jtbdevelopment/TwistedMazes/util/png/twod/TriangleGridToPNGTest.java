package com.jtbdevelopment.TwistedMazes.util.png.twod;

import com.jtbdevelopment.TwistedMazes.factory.mazes.twod.RecursiveBacktracker;
import com.jtbdevelopment.TwistedMazes.factory.mazes.twod.Wilsons;
import com.jtbdevelopment.TwistedMazes.state.maze.twod.model.triangle.TriangleGrid;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import org.junit.Test;

/**
 * Date: 1/19/19 Time: 4:32 PM
 */
public class TriangleGridToPNGTest {

  private TriangleGridToPNG triangleGridToPNG = new TriangleGridToPNG();
  private RecursiveBacktracker recursiveBacktracker = new RecursiveBacktracker();
  private Wilsons wilsons = new Wilsons();

  @Test
  public void testDrawingTriangleGrid() throws IOException {
    TriangleGrid grid = new TriangleGrid(10, 10);

    Files.write(Paths.get("trianglegrid.png"), triangleGridToPNG.convert(grid),
        StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.CREATE);

    recursiveBacktracker.make2DMaze(grid);

    Files.write(Paths.get("trianglegrid-rbt.png"), triangleGridToPNG.convert(grid),
        StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.CREATE);
  }

  @Test
  public void testDrawingHexGridUsingFakeEast() throws IOException {
    TriangleGrid grid = new TriangleGrid(10, 10);

    Files.write(Paths.get("trianglegrid.png"), triangleGridToPNG.convert(grid),
        StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.CREATE);

    wilsons.make2DMaze(grid);

    Files.write(Paths.get("trianglegrid-wilson.png"), triangleGridToPNG.convert(grid),
        StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.CREATE);
  }
}
