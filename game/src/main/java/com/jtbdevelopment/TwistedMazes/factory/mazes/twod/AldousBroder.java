package com.jtbdevelopment.TwistedMazes.factory.mazes.twod;

import com.jtbdevelopment.TwistedMazes.state.maze.twod.model.Cell;
import com.jtbdevelopment.TwistedMazes.state.maze.twod.model.Grid;
import java.util.ArrayList;
import java.util.List;

/**
 * Date: 9/20/18 Time: 7:49 PM
 */
public class AldousBroder extends
    AbstractGenerator2DMaze<Grid> {

  public void make2DMaze(final Grid grid) {
    Cell randomCell = grid.getRandomCell();
    int unvisited = grid.size() - 1;
    while (unvisited > 0) {
      List<Cell> neighbors = new ArrayList<>(randomCell.getNeighbors());
      Cell randomNeighbor = getRandomCell(neighbors);
      if (randomNeighbor.getLinkedCells().isEmpty()) {
        randomCell.linkCell(randomNeighbor);
        --unvisited;
      }
      randomCell = randomNeighbor;
    }
  }
}
