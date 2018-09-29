package com.jtbdevelopment.TwistedMazes.factory.mazes.twod;

/**
 * Date: 8/28/18 Time: 6:51 PM
 */
public class BinaryTreeTest extends AbstractGraphical2DMazeTest {

  private BinaryTree binaryTree = new BinaryTree();

  @Override
  protected String getFileName() {
    return "binarytree";
  }

  @Override
  protected Generator2DMaze getGenerator() {
    return binaryTree;
  }

}
