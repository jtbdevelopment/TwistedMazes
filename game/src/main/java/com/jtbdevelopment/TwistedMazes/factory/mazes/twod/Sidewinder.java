package com.jtbdevelopment.TwistedMazes.factory.mazes.twod;

import com.jtbdevelopment.TwistedMazes.state.maze.twod.Cell;
import com.jtbdevelopment.TwistedMazes.state.maze.twod.Grid;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * Date: 8/28/18 Time: 6:59 PM
 */
public class Sidewinder {

  private final Random random = new Random();

  public void make2DMaze(Grid grid) {
    grid.streamRows().forEach(row -> {
      List<Cell> run = new LinkedList<>();
      row.forEach(cell -> {
        run.add(cell);
        boolean atEastern = cell.getEast() == null;
        boolean atNorthern = cell.getNorth() == null;

        boolean shouldClose = atEastern || (!atNorthern && random.nextBoolean());
        if (shouldClose) {
          Cell join = run.get(random.nextInt(run.size()));
          if (join.getNorth() != null) {
            join.linkCell(join.getNorth());
          }
          run.clear();
        } else {
          cell.linkCell(cell.getEast());
        }
      });
    });
  }
}
