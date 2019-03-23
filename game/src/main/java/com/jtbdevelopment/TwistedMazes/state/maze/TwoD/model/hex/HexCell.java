package com.jtbdevelopment.TwistedMazes.state.maze.twod.model.hex;

import com.jtbdevelopment.TwistedMazes.state.maze.twod.model.AbstractCell;
import com.jtbdevelopment.TwistedMazes.state.maze.twod.model.DirectionalCell;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Date: 1/6/19 Time: 4:08 PM
 */
public class HexCell extends AbstractCell<DirectionalCell> implements DirectionalCell {

  private HexCell north, northeast, northwest, south, southeast, southwest;

  public HexCell(final int row, final int col) {
    super(row, col);
  }

  @Override
  public Set<DirectionalCell> getNeighbors() {
    return
        Stream.of(north, northeast, southeast, south, southwest, northwest)
            .filter(Objects::nonNull)
            .collect(Collectors.toSet());
  }

  public HexCell getNorth() {
    return north;
  }

  public void setNorth(final HexCell north) {
    this.north = north;
  }

  public HexCell getNortheast() {
    return northeast;
  }

  public void setNortheast(final HexCell northeast) {
    this.northeast = northeast;
  }

  public HexCell getNorthwest() {
    return northwest;
  }

  public void setNorthwest(final HexCell northwest) {
    this.northwest = northwest;
  }

  public HexCell getSouth() {
    return south;
  }

  public void setSouth(final HexCell south) {
    this.south = south;
  }

  public HexCell getSoutheast() {
    return southeast;
  }

  public void setSoutheast(final HexCell southeast) {
    this.southeast = southeast;
  }

  public HexCell getSouthwest() {
    return southwest;
  }

  public void setSouthwest(final HexCell southwest) {
    this.southwest = southwest;
  }

  @Override
  public DirectionalCell getEast() {
    return northeast;
  }

  @Override
  public DirectionalCell getWest() {
    return southwest;
  }
}
