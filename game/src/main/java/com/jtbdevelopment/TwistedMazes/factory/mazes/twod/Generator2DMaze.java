package com.jtbdevelopment.TwistedMazes.factory.mazes.twod;

import com.jtbdevelopment.TwistedMazes.state.maze.twod.model.Grid;

/**
 * Date: 9/21/18 Time: 6:28 PM
 */
public interface Generator2DMaze<G extends Grid> {

  void make2DMaze(G grid);
}
