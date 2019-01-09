package com.jtbdevelopment.TwistedMazes.state.maze.twod.model;

import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.stream.Stream;

/**
 * Date: 12/3/18 Time: 6:50 PM
 */
public abstract class AbstractGrid<C extends AbstractCell> {

  private static final Random random = new Random();
  protected List<List<C>> cells;
  protected int rows;

  public int size() {
    return cells.stream().mapToInt(List::size).sum();
  }

  public C getRandomCell() {
    int row = random.nextInt(rows);

    int col = random.nextInt(cells.get(0).size());
    return getCell(row, col);
  }

  public C getCell(final int row, final int col) {
    if (row < 0 || row >= rows || col < 0 || col >= cells.get(row).size()) {
      return null;
    }

    return cells.get(row).get(col);
  }

  public Stream<C> stream() {
    return cells.stream()
        .filter(Objects::nonNull)
        .flatMap(row ->
            row.stream().filter(Objects::nonNull)
        );
  }

  public Stream<List<C>> streamRows() {
    return cells.stream();
  }

  public long getDeadEnds() {
    return stream().filter(c -> c.getLinkedCells().size() == 1).count();
  }

  public int getRows() {
    return rows;
  }

  public List<C> getRow(final int row) {
    return cells.get(row);
  }
}
