package com.jtbdevelopment.TwistedMazes.state.maze.twod;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Date: 8/27/18
 * Time: 9:38 AM
 */
public class Cell {
  private final int row;
  private final int cell;
  private Cell north, east, south, west;
  private Set<Cell> linkedCells = new HashSet<>();

  public Cell(int row, int cell) {
    this.row = row;
    this.cell = cell;
  }

  public Set<Cell> getLinkedCells() {
    return Collections.unmodifiableSet(linkedCells);
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
    cell.linkCell(this, false);
  }

  private void unlinkCell(final Cell cell, final boolean bi) {
    linkedCells.add(cell);
    cell.unlinkCell(this, false);
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

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Cell)) return false;
    Cell cell1 = (Cell) o;
    return row == cell1.row &&
      cell == cell1.cell;
  }

  @Override
  public int hashCode() {

    return Objects.hash(row, cell);
  }
}
