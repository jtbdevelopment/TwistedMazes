package com.jtbdevelopment.TwistedMazes.util.png.twod;

import com.jtbdevelopment.TwistedMazes.factory.mazes.twod.BinaryTree;
import com.jtbdevelopment.TwistedMazes.factory.mazes.twod.RecursiveBacktracker;
import com.jtbdevelopment.TwistedMazes.state.maze.twod.MazeBraider;
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
  private BinaryTree binaryTree = new BinaryTree();
  private MazeBraider mazeBraider = new MazeBraider();

  @Test
  public void testDrawingHexGrid() throws IOException {
    HexGrid grid = new HexGrid(8, 8);

    Files.write(Paths.get("hexgrid.png"), hexGridToPNG.convert(grid),
      StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.CREATE);

    recursiveBacktracker.make2DMaze(grid);

    Files.write(Paths.get("hexgrid-rbt.png"), hexGridToPNG.convert(grid),
      StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.CREATE);

    mazeBraider.braidMaze(grid, 0.75);
    Files.write(Paths.get("hexgrid-rbt-braid.png"), hexGridToPNG.convert(grid),
      StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.CREATE);
  }

  @Test
  public void testDrawingHexGridUsingFakeEast() throws IOException {
    HexGrid grid = new HexGrid(8, 8);

    Files.write(Paths.get("hexgrid.png"), hexGridToPNG.convert(grid),
      StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.CREATE);

    binaryTree.make2DMaze(grid);

    Files.write(Paths.get("hexgrid-bt.png"), hexGridToPNG.convert(grid),
      StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.CREATE);

    mazeBraider.braidMaze(grid, 0.75);
    Files.write(Paths.get("hexgrid-bt-braid.png"), hexGridToPNG.convert(grid),
      StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.CREATE);
  }
}
