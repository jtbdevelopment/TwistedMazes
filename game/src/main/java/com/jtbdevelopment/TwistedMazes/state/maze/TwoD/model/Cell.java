package com.jtbdevelopment.TwistedMazes.state.maze.twod.model;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Date: 8/27/18 Time: 9:38 AM
 */
public class Cell {

  private final int row;
  private final int col;
  private Cell north, east, south, west;
  private Set<Cell> linkedCells = new HashSet<>();

  public Cell(int row, int col) {
    this.row = row;
    this.col = col;
  }

  public Set<Cell> getLinkedCells() {
    return Collections.unmodifiableSet(linkedCells);
  }

  public Set<Cell> getNeighbors() {
    return Stream.of(north, east, south, west).filter(Objects::nonNull).collect(Collectors.toSet());
  }

  public boolean isLinked(final Cell cell) {
    return linkedCells.contains(cell);
  }

  public void linkCell(final Cell cell) {
    linkCell(cell, true);
  }

  public void unlinkCell(final Cell cell) {
    unlinkCell(cell, true);
  }

  private void linkCell(final Cell cell, final boolean bi) {
    linkedCells.add(cell);
    if (bi) {
      cell.linkCell(this, false);
    }
  }

  private void unlinkCell(final Cell cell, final boolean bi) {
    linkedCells.add(cell);
    if (bi) {
      cell.unlinkCell(this, false);
    }
  }

  public Cell getNorth() {
    return north;
  }

  public void setNorth(Cell north) {
    this.north = north;
  }

  public Cell getEast() {
    return east;
  }

  public void setEast(Cell east) {
    this.east = east;
  }

  public Cell getSouth() {
    return south;
  }

  public void setSouth(Cell south) {
    this.south = south;
  }

  public Cell getWest() {
    return west;
  }

  public void setWest(Cell west) {
    this.west = west;
  }

  public int getRow() {
    return row;
  }

  public int getCol() {
    return col;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Cell)) {
      return false;
    }
    Cell cell1 = (Cell) o;
    return row == cell1.row &&
        col == cell1.col;
  }

  @Override
  public int hashCode() {
    return Objects.hash(row, col);
  }
}
