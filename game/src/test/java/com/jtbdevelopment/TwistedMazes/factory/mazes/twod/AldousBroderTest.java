package com.jtbdevelopment.TwistedMazes.factory.mazes.twod;

/**
 * Date: 9/20/18 Time: 7:57 PM
 */
public class AldousBroderTest extends AbstractGraphical2DMazeWithMaskingTest {

  private AldousBroder aldousBroder = new AldousBroder();

  @Override
  protected String getFileName() {
    return "aldousbroder";
  }

  @Override
  protected Generator2DMaze getGenerator() {
    return aldousBroder;
  }

}
