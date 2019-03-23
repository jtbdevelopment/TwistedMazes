package com.jtbdevelopment.TwistedMazes.state.maze.twod.model;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Date: 12/3/18 Time: 6:36 PM
 */
@SuppressWarnings("unchecked")
public abstract class AbstractCell<C extends Cell> implements Cell {

  protected final int row;
  protected final int col;
  private Set<Cell> linkedCells = new HashSet<>();

  protected AbstractCell(
      int row, int col) {
    this.row = row;
    this.col = col;
  }

  @Override
  public <C extends Cell> Set<C> getLinkedCells() {
    return (Set<C>) Collections.unmodifiableSet(linkedCells);
  }

  @Override
  public boolean isLinked(final Cell cell) {
    return cell != null && linkedCells.contains(cell);
  }

  @Override
  public void linkCell(final Cell cell) {
    linkCell(cell, true);
  }

  @Override
  public void unlinkCell(final Cell cell) {
    unlinkCell(cell, true);
  }

  @Override
  public void linkCell(final Cell cell, final boolean bi) {
    if (cell != null) {
      linkedCells.add(cell);
      if (bi) {
        cell.linkCell(this, false);
      }
    }
  }

  @Override
  public void unlinkCell(final Cell cell, final boolean bi) {
    if (cell != null) {
      linkedCells.remove(cell);
      if (bi) {
        cell.unlinkCell(this, false);
      }
    }
  }

  @Override
  public int getRow() {
    return row;
  }

  @Override
  public int getCol() {
    return col;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof AbstractCell)) {
      return false;
    }
    AbstractCell cell1 = (AbstractCell) o;
    return row == cell1.row &&
        col == cell1.col;
  }

  @Override
  public int hashCode() {
    return Objects.hash(row, col);
  }
}
