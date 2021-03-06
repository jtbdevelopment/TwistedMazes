package com.jtbdevelopment.TwistedMazes.state.maze.twod.model.rectangle;

import com.jtbdevelopment.TwistedMazes.state.maze.twod.model.AbstractGrid;
import com.jtbdevelopment.TwistedMazes.state.maze.twod.model.DirectionalGrid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * Date: 8/27/18 Time: 9:48 AM
 */
public class RectangleGrid extends AbstractGrid<RectangleCell> implements DirectionalGrid {

  private int cols;

  RectangleGrid() {

  }

  public RectangleGrid(final int rows, final int cols) {
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
      List<RectangleCell> rowCells = this.cells.get(row);
      IntStream.range(0, cols).forEach(col -> {
        RectangleCell cell = rowCells.get(col);
        if (cell != null) {
          cell.setNorth(getCell(row - 1, col));
          cell.setSouth(getCell(row + 1, col));
          cell.setWest(getCell(row, col - 1));
          cell.setEast(getCell(row, col + 1));
        }
      });
    });
  }

  protected void prepareCells(final int rows, final int cols) {
    IntStream.range(0, rows).forEach(row -> {
      List<RectangleCell> rowCells = this.cells.get(row);
      IntStream.range(0, cols).forEach(col -> rowCells.add(new RectangleCell(row, col)));
    });
  }

  public int getCols() {
    return cols;
  }

}
