package com.jtbdevelopment.TwistedMazes.state.maze.twod;

/**
 * Date: 9/8/18 Time: 6:17 AM
 */
public class DistancesGridOverlay {

  private final Distances distances;
  private final Grid grid;

  public DistancesGridOverlay(
      final Grid grid,
      final Distances distances) {
    this.distances = distances;
    this.grid = grid;
  }

  @Override
  public String toString() {
    return new Grid(grid) {
      @Override
      protected String cellContent(Cell cell) {
        if (distances != null && distances.hasCell(cell)) {
          return " " + Integer.toString(distances.getDistance(cell), 36) + " ";
        }
        return super.cellContent(cell);
      }
    }.toString();
  }
}
