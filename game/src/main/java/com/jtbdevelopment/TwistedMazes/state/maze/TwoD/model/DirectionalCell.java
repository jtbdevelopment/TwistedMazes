package com.jtbdevelopment.TwistedMazes.state.maze.twod.model;

/**
 * Date: 2/24/19 Time: 5:58 PM
 */
public interface DirectionalCell extends Cell {

  DirectionalCell getNorth();

  DirectionalCell getEast();

  DirectionalCell getSouth();

  DirectionalCell getWest();

}
