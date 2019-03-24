package com.jtbdevelopment.TwistedMazes.state.maze.twod.model.triangle;

import com.jtbdevelopment.TwistedMazes.state.maze.twod.model.rectangle.RectangleCell;

public class TriangleCell extends RectangleCell {

  private final boolean upright;

  public TriangleCell(final int row, final int col) {
    super(row, col);
    upright = (row + col) % 2 == 0;
  }

  public boolean isUpright() {
    return upright;
  }
}
