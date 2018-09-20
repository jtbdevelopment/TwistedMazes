package com.jtbdevelopment.TwistedMazes.factory.mazes.twod;

import com.jtbdevelopment.TwistedMazes.state.maze.twod.model.Grid;
import org.junit.Test;

/**
 * Date: 8/28/18
 * Time: 6:51 PM
 */
public class BinaryTreeTest {
  private BinaryTree binaryTree = new BinaryTree();

  @Test
  public void printABinaryTreeMaze() {
    Grid grid = new Grid(4, 4);
    binaryTree.make2DMaze(grid);
    System.out.print(grid.toString());
  }
}
