package com.jtbdevelopment.TwistedMazes.state.maze.twod.model.rectangle;

import java.util.List;
import java.util.stream.IntStream;

/**
 * Date: 8/27/18 Time: 9:48 AM
 */
public class MaskedRectangleGrid extends RectangleGrid {

  private final RectangleMask mask;

  public MaskedRectangleGrid(final RectangleMask mask) {
    super();
    this.mask = new RectangleMask(mask);
    initializeGrid(mask.getRows(), mask.getCols());
  }

  public RectangleMask getMask() {
    return mask;
  }

  @Override
  protected void prepareCells(final int rows, final int cols) {
    IntStream.range(0, rows).forEach(row -> {
      List<RectangleCell> rowCells = cells.get(row);
      IntStream.range(0, cols).forEach(col -> {
        if (mask.isEnabled(row, col)) {
          rowCells.add(new RectangleCell(row, col));
        } else {
          rowCells.add(null);
        }
      });
    });
  }

  @Override
  public RectangleCell getRandomCell() {
    while (true) {
      RectangleCell randomCell = super.getRandomCell();

      if (randomCell != null && mask.isEnabled(randomCell.getRow(), randomCell.getCol())) {
        return randomCell;
      }
    }
  }

  @Override
  public int size() {
    return (int) mask.enabledSize();
  }
}
