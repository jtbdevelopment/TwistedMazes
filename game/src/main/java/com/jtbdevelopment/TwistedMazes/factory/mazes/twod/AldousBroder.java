package com.jtbdevelopment.TwistedMazes.factory.mazes.twod;

import com.jtbdevelopment.TwistedMazes.state.maze.twod.model.AbstractCell;
import com.jtbdevelopment.TwistedMazes.state.maze.twod.model.AbstractGrid;
import java.util.ArrayList;
import java.util.List;

/**
 * Date: 9/20/18 Time: 7:49 PM
 */
public class AldousBroder extends
    AbstractGenerator2DMaze<AbstractGrid> {

  public void make2DMaze(final AbstractGrid grid) {
    AbstractCell randomCell = grid.getRandomCell();
    int unvisited = grid.size() - 1;
    while (unvisited > 0) {
      List<AbstractCell> neighbors = new ArrayList<>(randomCell.getNeighbors());
      AbstractCell randomNeighbor = getRandomCell(neighbors);
      if (randomNeighbor.getLinkedCells().isEmpty()) {
        randomCell.linkCell(randomNeighbor);
        --unvisited;
      }
      randomCell = randomNeighbor;
    }
  }
}
