package com.jtbdevelopment.TwistedMazes.factory.mazes.twod;

import com.jtbdevelopment.TwistedMazes.state.maze.twod.model.Cell;
import com.jtbdevelopment.TwistedMazes.state.maze.twod.model.Grid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Date: 9/20/18 Time: 7:49 PM
 */
public class Wilsons extends AbstractGenerator2DMaze implements Generator2DMaze {

  public void make2DMaze(final Grid grid) {
    List<Cell> unvisited = grid.stream().collect(Collectors.toList());
    unvisited.remove(getRandomCell(unvisited));
    while (!unvisited.isEmpty()) {
      Cell randomCell = getRandomCell(unvisited);
      List<Cell> path = new ArrayList<>();
      path.add(randomCell);
      while (unvisited.contains(randomCell)) {
        randomCell = getRandomCell(randomCell.getNeighbors());
        int existingPosition = path.indexOf(randomCell);
        if (existingPosition < 0) {
          path.add(randomCell);
        } else {
          path = new ArrayList<>(path.subList(0, existingPosition + 1));
        }
      }

      for (int i = 0; i < path.size() - 1; ++i) {
        path.get(i).linkCell(path.get(i + 1));
        unvisited.remove(path.get(i));
      }
    }
  }

}
