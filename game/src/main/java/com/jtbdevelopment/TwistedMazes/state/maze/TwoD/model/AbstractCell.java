package com.jtbdevelopment.TwistedMazes.state.maze.twod.model;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Date: 12/3/18 Time: 6:36 PM
 */
@SuppressWarnings("unchecked")
public abstract class AbstractCell {

  protected final int row;
  protected final int col;
  private Set<AbstractCell> linkedCells = new HashSet<>();

  protected AbstractCell(
      int row, int col) {
    this.row = row;
    this.col = col;
  }

  public <C extends AbstractCell> Set<C> getLinkedCells() {
    return (Set<C>) Collections.unmodifiableSet(linkedCells);
  }

  public abstract Set<AbstractCell> getNeighbors();

  public boolean isLinked(final AbstractCell cell) {
    return cell != null && linkedCells.contains(cell);
  }

  public void linkCell(final AbstractCell cell) {
    linkCell(cell, true);
  }

  public void unlinkCell(final AbstractCell cell) {
    unlinkCell(cell, true);
  }

  protected void linkCell(final AbstractCell cell, final boolean bi) {
    if (cell != null) {
      linkedCells.add(cell);
      if (bi) {
        cell.linkCell(this, false);
      }
    }
  }

  protected void unlinkCell(final AbstractCell cell, final boolean bi) {
    if (cell != null) {
      linkedCells.remove(cell);
      if (bi) {
        cell.unlinkCell(this, false);
      }
    }
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
