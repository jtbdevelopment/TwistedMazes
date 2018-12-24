package com.jtbdevelopment.TwistedMazes.state.maze.twod.model.rectangle;

import com.jtbdevelopment.TwistedMazes.state.maze.twod.model.AbstractGrid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * Date: 8/27/18 Time: 9:48 AM
 */
public class RectangleGrid extends AbstractGrid<RectangleCell> {

  private int cols;

  protected RectangleGrid() {

  }

  public RectangleGrid(final RectangleGrid copy) {
    this.rows = copy.getRows();
    this.cols = copy.cols;
    this.cells = copy.cells;
  }

  public RectangleGrid(final int rows, final int cols) {
    initializeGrid(rows, cols);
  }

  @Override
  protected void initializeGrid(final int rows, final int cols) {
    this.rows = rows;
    this.cols = cols;
    cells = new ArrayList<>(rows);
    IntStream.range(0, rows).forEach(row -> cells.add(new ArrayList<>(cols)));
    prepareCells(rows, cols);
    assignNeighbors(rows, cols);
  }

  protected void assignNeighbors(final int rows, final int cols) {
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
      IntStream.range(0, cols).forEach(col -> {
        rowCells.add(new RectangleCell(row, col));
      });
    });
  }

  public int getCols() {
    return cols;
  }

  protected String cellContent(final RectangleCell cell) {
    return "   ";
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder("+");
    for (int col = 0; col < cols; ++col) {
      builder.append("---+");
    }
    builder.append(System.lineSeparator());

    streamRows().forEach(row -> {
      StringBuilder top = new StringBuilder("|");
      StringBuilder bottom = new StringBuilder("+");
      row.forEach(cell -> {
        top.append(cellContent(cell));
        top.append((cell != null && cell.isLinked(cell.getEast())) ? " " : "|");
        bottom.append((cell != null && cell.isLinked(cell.getSouth())) ? "   " : "---");
        bottom.append("+");
      });
      builder.append(top).append(System.lineSeparator());
      builder.append(bottom).append(System.lineSeparator());
    });
    return builder.toString();
  }
}
