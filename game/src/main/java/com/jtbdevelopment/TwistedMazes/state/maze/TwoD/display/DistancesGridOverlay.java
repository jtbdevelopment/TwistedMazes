package com.jtbdevelopment.TwistedMazes.state.maze.twod.display;

import com.jtbdevelopment.TwistedMazes.state.maze.twod.model.Distances;
import com.jtbdevelopment.TwistedMazes.state.maze.twod.model.rectangle.RectangleGrid;

/**
 * Date: 9/8/18 Time: 6:17 AM
 */
public class DistancesGridOverlay {

  private final Distances distances;
  private final RectangleGrid grid;

  public DistancesGridOverlay(
      final RectangleGrid grid,
      final Distances distances) {
    this.distances = distances;
    this.grid = grid;
  }
}
