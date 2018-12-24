package com.jtbdevelopment.TwistedMazes.factory.mazes.twod;

import com.jtbdevelopment.TwistedMazes.state.maze.twod.model.rectangle.RectangleCell;
import com.jtbdevelopment.TwistedMazes.state.maze.twod.model.rectangle.RectangleGrid;
import java.util.LinkedList;
import java.util.List;

/**
 * Date: 8/28/18 Time: 6:59 PM
 */
public class Sidewinder extends AbstractGenerator2DMaze<RectangleGrid> {

  public void make2DMaze(final RectangleGrid grid) {
    grid.streamRows().forEach(row -> {
      List<RectangleCell> run = new LinkedList<>();
      row.forEach(cell -> {
        run.add(cell);
        boolean atEastern = cell.getEast() == null;
        boolean atNorthern = cell.getNorth() == null;

        boolean shouldClose = atEastern || (!atNorthern && random.nextBoolean());
        if (shouldClose) {
          RectangleCell join = getRandomCell(run);
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
