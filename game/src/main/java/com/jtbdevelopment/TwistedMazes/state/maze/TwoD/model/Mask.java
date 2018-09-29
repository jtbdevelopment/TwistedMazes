package com.jtbdevelopment.TwistedMazes.state.maze.twod.model;

import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Date: 9/22/18 Time: 5:52 AM
 */
public class Mask {

  private static final Random random = new Random();

  private final boolean[][] enabled;
  private final int rows;
  private final int cols;

  public Mask(final Mask source) {
    this.rows = source.rows;
    this.cols = source.cols;
    this.enabled = source.enabled;
  }

  public Mask(final int rows, final int cols) {
    enabled = new boolean[rows][cols];
    for (int row = 0; row < rows; ++row) {
      for (int col = 0; col < cols; ++col) {
        enabled[row][col] = true;
      }
    }
    this.rows = rows;
    this.cols = cols;
  }

  public int getRows() {
    return rows;
  }

  public int getCols() {
    return cols;
  }

  public boolean isEnabled(int row, int col) {
    return enabled[row][col];
  }

  public void enable(int row, int col) {
    enabled[row][col] = true;
  }

  public void disable(int row, int col) {
    enabled[row][col] = false;
  }

  public Stream<Boolean> stream() {
    return IntStream.range(0, rows)
      .boxed()
      .flatMap(row -> IntStream.range(0, cols).mapToObj(col -> isEnabled(row, col)));
  }

  public long enabledSize() {
    return this.stream().filter(b -> b).count();
  }

  public long disabledSize() {
    return this.stream().filter(b -> !b).count();
  }
}
