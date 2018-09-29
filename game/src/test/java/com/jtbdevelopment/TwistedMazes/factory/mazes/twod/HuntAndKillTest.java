package com.jtbdevelopment.TwistedMazes.factory.mazes.twod;

/**
 * Date: 9/21/18 Time: 6:44 PM
 */
public class HuntAndKillTest extends AbstractGraphical2DMazeWithMaskingTest {

  private HuntAndKill huntAndKill = new HuntAndKill();

  @Override
  protected String getFileName() {
    return "huntandkill";
  }

  @Override
  protected Generator2DMaze getGenerator() {
    return huntAndKill;
  }
}
