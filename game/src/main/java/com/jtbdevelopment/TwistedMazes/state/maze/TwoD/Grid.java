package com.jtbdevelopment.TwistedMazes.state.maze.twod;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Date: 8/27/18 Time: 9:48 AM
 */
public class Grid {

  private static final Random random = new Random();
  private final Cell[][] cells;
  private int rows;
  private int cols;

  public Grid(final Grid copy) {
    this.rows = copy.rows;
    this.cols = copy.cols;
    this.cells = copy.cells;
  }

  public Grid(final int rows, final int cols) {
    this.rows = rows;
    this.cols = cols;
    cells = new Cell[rows][cols];
    for (int row = 0; row < rows; ++row) {
      for (int col = 0; col < cols; ++col) {
        cells[row][col] = new Cell(row, col);
      }
    }
    for (int row = 0; row < rows; ++row) {
      for (int col = 0; col < cols; ++col) {
        cells[row][col].setNorth(getCell(row - 1, col));
        cells[row][col].setSouth(getCell(row + 1, col));
        cells[row][col].setWest(getCell(row, col - 1));
        cells[row][col].setEast(getCell(row, col + 1));
      }
    }
  }

  public int size() {
    return rows * cols;
  }

  public Cell getRandomCell() {
    int row = random.nextInt(rows);
    int col = random.nextInt(cols);
    return getCell(row, col);
  }

  public Cell getCell(final int row, final int col) {
    if (row < 0 || row >= rows || col < 0 || col >= cols) {
      return null;
    }

    return cells[row][col];
  }

  public Stream<Cell> stream() {
    return IntStream.range(0, rows)
        .boxed()
        .flatMap(row -> IntStream.range(0, cols).mapToObj(col -> getCell(row, col)));
  }

  public Stream<List<Cell>> streamRows() {
    return IntStream.range(0, rows).mapToObj(row -> Arrays.asList(cells[row]));
  }

  public int getRows() {
    return rows;
  }

  public int getCols() {
    return cols;
  }

  protected String cellContent(final Cell cell) {
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
        top.append(cell.isLinked(cell.getEast()) ? " " : "|");
        bottom.append(cell.isLinked(cell.getSouth()) ? "   " : "---");
        bottom.append("+");
      });
      builder.append(top).append(System.lineSeparator());
      builder.append(bottom).append(System.lineSeparator());
    });
    return builder.toString();
  }
}
