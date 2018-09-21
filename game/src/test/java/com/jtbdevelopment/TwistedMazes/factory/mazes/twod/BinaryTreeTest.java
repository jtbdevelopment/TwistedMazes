package com.jtbdevelopment.TwistedMazes.factory.mazes.twod;

import com.jtbdevelopment.TwistedMazes.state.maze.twod.model.Grid;
import java.io.IOException;
import org.junit.Test;

/**
 * Date: 8/28/18 Time: 6:51 PM
 */
public class BinaryTreeTest extends AbstractGraphicalMazeTest {

  private BinaryTree binaryTree = new BinaryTree();

  @Test
  public void printABinaryTreeMaze() throws IOException {
    Grid grid = new Grid(15, 15);
    binaryTree.make2DMaze(grid);
    System.out.print(grid.toString());

    createPNGImages(grid, "binarytree");
  }
}
