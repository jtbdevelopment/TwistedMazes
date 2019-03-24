package com.jtbdevelopment.TwistedMazes.factory.mazes.twod;

import com.jtbdevelopment.TwistedMazes.state.maze.twod.model.DirectionalCell;
import com.jtbdevelopment.TwistedMazes.state.maze.twod.model.DirectionalGrid;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

/**
 * Date: 8/28/18 Time: 6:59 PM
 */
public class Sidewinder extends AbstractGenerator2DMaze<DirectionalGrid> {

  public void make2DMaze(final DirectionalGrid grid) {
    Stream<List<DirectionalCell>> listStream = grid.streamRows();
    listStream.forEach(row -> {
      List<DirectionalCell> run = new LinkedList<>();
      row.forEach(cell -> {
        run.add(cell);
        boolean atEastern = cell.getEast() == null;
        boolean atNorthern = cell.getNorth() == null;

        boolean shouldClose = atEastern || (!atNorthern && random.nextBoolean());
        if (shouldClose) {
          DirectionalCell join = getRandomCell(run);
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
