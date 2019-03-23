package com.jtbdevelopment.TwistedMazes.state.maze.twod.model.rectangle;

import com.jtbdevelopment.TwistedMazes.state.maze.twod.model.AbstractCell;
import com.jtbdevelopment.TwistedMazes.state.maze.twod.model.Cell;
import com.jtbdevelopment.TwistedMazes.state.maze.twod.model.DirectionalCell;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Date: 8/27/18 Time: 9:38 AM
 */
public class RectangleCell extends AbstractCell<DirectionalCell> implements DirectionalCell {

  private DirectionalCell north, east, south, west;

  public RectangleCell(int row, int col) {
    super(row, col);
  }

  @Override
  public Set<Cell> getNeighbors() {
    return Stream.of(north, east, south, west).filter(Objects::nonNull)
        .collect(Collectors.toSet());
  }

  @Override
  public DirectionalCell getNorth() {
    return north;
  }

  public void setNorth(DirectionalCell north) {
    this.north = north;
  }

  @Override
  public DirectionalCell getEast() {
    return east;
  }

  public void setEast(DirectionalCell east) {
    this.east = east;
  }

  @Override
  public DirectionalCell getSouth() {
    return south;
  }

  public void setSouth(DirectionalCell south) {
    this.south = south;
  }

  @Override
  public DirectionalCell getWest() {
    return west;
  }

  public void setWest(DirectionalCell west) {
    this.west = west;
  }

}
