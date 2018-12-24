package com.jtbdevelopment.TwistedMazes.state.maze.twod.display;

import com.jtbdevelopment.TwistedMazes.state.maze.twod.model.Distances;
import com.jtbdevelopment.TwistedMazes.state.maze.twod.model.rectangle.RectangleCell;
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

  @Override
  public String toString() {
    return new RectangleGrid(grid) {
      @Override
      protected String cellContent(RectangleCell cell) {
        if (distances != null && distances.hasCell(cell)) {
          return " " + Integer.toString(distances.getDistance(cell), 36) + " ";
        }
        return super.cellContent(cell);
      }
    }.toString();
  }
}
