package com.jtbdevelopment.TwistedMazes.state.maze.twod.model.triangle;

import com.jtbdevelopment.TwistedMazes.state.maze.twod.model.AbstractGrid;
import com.jtbdevelopment.TwistedMazes.state.maze.twod.model.DirectionalGrid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * Date: 3/24/19 Time: 10:22 AM
 */
public class TriangleGrid extends AbstractGrid<TriangleCell> implements DirectionalGrid {

  private int cols;

  public TriangleGrid(int rows, int cols) {
    initializeGrid(rows, cols);
  }

  void initializeGrid(final int rows, final int cols) {
    this.rows = rows;
    this.cols = cols;
    cells = new ArrayList<>(rows);
    IntStream.range(0, rows).forEach(row -> cells.add(new ArrayList<>(cols)));
    prepareCells(rows, cols);
    assignNeighbors(rows, cols);
  }

  private void assignNeighbors(final int rows, final int cols) {
    IntStream.range(0, rows).forEach(row -> {
      List<TriangleCell> rowCells = this.cells.get(row);
      IntStream.range(0, cols).forEach(col -> {
        TriangleCell cell = rowCells.get(col);
        if (cell != null) {
          cell.setWest(getCell(row, col - 1));
          cell.setEast(getCell(row, col + 1));
          if (cell.isUpright()) {
            cell.setSouth(getCell(row + 1, col));
          } else {
            cell.setNorth(getCell(row - 1, col));
          }
        }
      });
    });
  }

  private void prepareCells(final int rows, final int cols) {
    IntStream.range(0, rows).forEach(row -> {
      List<TriangleCell> rowCells = this.cells.get(row);
      IntStream.range(0, cols).forEach(col -> rowCells.add(new TriangleCell(row, col)));
    });
  }

  public int getCols() {
    return cols;
  }
}
