package com.jtbdevelopment.TwistedMazes.state.maze.twod.model.rectangle;

import com.jtbdevelopment.TwistedMazes.state.maze.twod.model.AbstractCell;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Date: 8/27/18 Time: 9:38 AM
 */
public class RectangleCell extends AbstractCell {

  private RectangleCell north, east, south, west;

  public RectangleCell(int row, int col) {
    super(row, col);
  }

  @Override
  public Set<AbstractCell> getNeighbors() {
    return Stream.of(north, east, south, west).filter(Objects::nonNull)
        .collect(Collectors.toSet());
  }

  public RectangleCell getNorth() {
    return north;
  }

  public void setNorth(RectangleCell north) {
    this.north = north;
  }

  public RectangleCell getEast() {
    return east;
  }

  public void setEast(RectangleCell east) {
    this.east = east;
  }

  public RectangleCell getSouth() {
    return south;
  }

  public void setSouth(RectangleCell south) {
    this.south = south;
  }

  public RectangleCell getWest() {
    return west;
  }

  public void setWest(RectangleCell west) {
    this.west = west;
  }

}
