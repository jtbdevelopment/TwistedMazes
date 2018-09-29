package com.jtbdevelopment.TwistedMazes.state.maze.twod.model;

/**
 * Date: 8/27/18 Time: 9:48 AM
 */
public class MaskedGrid extends Grid {

  private final Mask mask;

  public MaskedGrid(final MaskedGrid copy) {
    super(copy);
    this.mask = copy.mask;
  }

  public MaskedGrid(final Mask mask) {
    super();
    this.mask = new Mask(mask);
    initializeGrid(mask.getRows(), mask.getCols());
  }

  public Mask getMask() {
    return mask;
  }

  @Override
  protected void prepareCells(final int rows, final int cols) {
    for (int row = 0; row < rows; ++row) {
      for (int col = 0; col < cols; ++col) {
        if (mask.isEnabled(row, col)) {
          cells[row][col] = new Cell(row, col);
        }
      }
    }
  }

  @Override
  public Cell getRandomCell() {
    while (true) {
      Cell randomCell = super.getRandomCell();

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
