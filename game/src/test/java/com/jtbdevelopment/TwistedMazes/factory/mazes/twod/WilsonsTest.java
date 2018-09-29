package com.jtbdevelopment.TwistedMazes.factory.mazes.twod;

/**
 * Date: 9/20/18 Time: 8:23 PM
 */
public class WilsonsTest extends AbstractGraphical2DMazeWithMaskingTest {

  private Wilsons wilsons = new Wilsons();

  @Override
  protected String getFileName() {
    return "wilsons";
  }

  @Override
  protected Generator2DMaze getGenerator() {
    return wilsons;
  }
}
