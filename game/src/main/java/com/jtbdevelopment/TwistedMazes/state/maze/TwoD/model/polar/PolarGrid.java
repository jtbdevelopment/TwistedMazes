package com.jtbdevelopment.TwistedMazes.state.maze.twod.model.polar;

import com.jtbdevelopment.TwistedMazes.state.maze.twod.model.AbstractGrid;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

/**
 * Date: 8/27/18 Time: 9:48 AM
 */
public class PolarGrid extends AbstractGrid<PolarCell> {

  protected PolarGrid() {

  }

  public PolarGrid(final PolarGrid copy) {
    this.rows = copy.getRows();
    this.cells = copy.cells;
  }

  public PolarGrid(final int rows) {
    initializeGrid(rows, 1);
  }

  @Override
  protected void initializeGrid(final int rows, final int cols) {
    this.rows = rows;
    cells = new ArrayList<>(rows);

    prepareCells();
    assignNeighbors();
  }

  private void assignNeighbors() {
    stream().forEach(cell -> {
      int row = cell.getRow();
      int col = cell.getCol();
      if (row > 0) {
        List<PolarCell> rowCells = cells.get(row);
        if (col == rowCells.size() - 1) {
          cell.setCw(rowCells.get(0));
        } else {
          cell.setCw(rowCells.get(col + 1));
        }
        if (col == 0) {
          cell.setCcw(rowCells.get(rowCells.size() - 1));
        } else {
          cell.setCcw(rowCells.get(col - 1));
        }

        double ratio = ((double) cells.get(row).size()) / ((double) cells.get(row - 1).size());
        PolarCell parent = cells.get(row - 1).get((int) (((double) col) / ratio));
        parent.getOutward().add(cell);
        cell.setInward(parent);
      }
    });
  }

  private void prepareCells() {
    cells.add(Collections.singletonList(new PolarCell(0, 0)));
    double row_height = 1.0 / ((double) rows);
    IntStream.range(1, rows).forEach(row -> {
      double radius = ((double) row) / ((double) rows);
      double circumference = 2 * Math.PI * radius;

      int previous_count = cells.get(row - 1).size();
      double estimated_width = circumference / ((double) previous_count);
      int ratio = (int) Math.round(estimated_width / row_height);
      int colCount = previous_count * ratio;
      ArrayList<PolarCell> newRow = new ArrayList<>(colCount);
      IntStream.range(0, colCount).forEach(col -> newRow.add(new PolarCell(row, col)));
      cells.add(newRow);
    });
  }

//  protected String cellContent(final PolarCell cell) {
//    return "   ";
//  }
//
//  @Override
//  public String toString() {
//    StringBuilder builder = new StringBuilder("+");
//    for (int col = 0; col < cols; ++col) {
//      builder.append("---+");
//    }
//    builder.append(System.lineSeparator());
//
//    streamRows().forEach(row -> {
//      StringBuilder top = new StringBuilder("|");
//      StringBuilder bottom = new StringBuilder("+");
//      row.forEach(cell -> {
//        top.append(cellContent(cell));
//        top.append((cell != null && cell.isLinked(cell.getEast())) ? " " : "|");
//        bottom.append((cell != null && cell.isLinked(cell.getSouth())) ? "   " : "---");
//        bottom.append("+");
//      });
//      builder.append(top).append(System.lineSeparator());
//      builder.append(bottom).append(System.lineSeparator());
//    });
//    return builder.toString();
//  }
}
