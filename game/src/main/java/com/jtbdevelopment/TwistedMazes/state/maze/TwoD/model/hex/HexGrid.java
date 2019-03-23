package com.jtbdevelopment.TwistedMazes.state.maze.twod.model.hex;

import com.jtbdevelopment.TwistedMazes.state.maze.twod.model.AbstractGrid;
import com.jtbdevelopment.TwistedMazes.state.maze.twod.model.DirectionalGrid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

/**
 * Date: 1/6/19 Time: 4:15 PM
 */
public class HexGrid extends AbstractGrid<HexCell> implements DirectionalGrid {

  private int cols;

  public HexGrid(final int rows, final int cols) {
    initializeGrid(rows, cols);
  }

  protected void initializeGrid(final int rows, final int cols) {
    this.rows = rows;
    this.cols = cols;
    cells = new ArrayList<>(rows);
    IntStream.range(0, rows).forEach(row -> cells.add(new ArrayList<>(cols)));
    prepareCells(rows, cols);
    assignNeighbors(rows, cols);
  }

  protected void assignNeighbors(final int rows, final int cols) {
    stream().filter(Objects::nonNull).forEach(cell -> {
      int row = cell.getRow();
      int col = cell.getCol();
      int northDiag, southDiag;
      if (cell.getCol() % 2 == 0) {
        northDiag = row - 1;
        southDiag = row;
      } else {
        northDiag = row;
        southDiag = row + 1;
      }
      cell.setNorth(getCell(row - 1, col));
      cell.setSouth(getCell(row + 1, col));
      cell.setNortheast(getCell(northDiag, col + 1));
      cell.setNorthwest(getCell(northDiag, col - 1));
      cell.setSoutheast(getCell(southDiag, col + 1));
      cell.setSouthwest(getCell(southDiag, col - 1));
    });
  }

  private void prepareCells(final int rows, final int cols) {
    IntStream.range(0, rows).forEach(row -> {
      List<HexCell> rowCells = this.cells.get(row);
      IntStream.range(0, cols).forEach(col -> rowCells.add(col, new HexCell(row, col, this)));
    });
  }

  public int getCols() {
    return cols;
  }
}
