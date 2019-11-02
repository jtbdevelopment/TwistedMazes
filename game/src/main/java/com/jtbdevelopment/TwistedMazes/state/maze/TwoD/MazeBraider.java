package com.jtbdevelopment.TwistedMazes.state.maze.twod;

import com.jtbdevelopment.TwistedMazes.state.maze.twod.model.Cell;
import com.jtbdevelopment.TwistedMazes.state.maze.twod.model.Grid;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import org.springframework.stereotype.Component;

@Component
public class MazeBraider {

  private final Random random = new Random();

  public void braidMaze(final Grid grid) {
    braidMaze(grid, 1.0);
  }

  public void braidMaze(final Grid grid, double braidProbability) {
    grid.getDeadEnds().forEach(deadend -> {
      //  might have gotten linked
      Set<Cell> linkedCells = deadend.getLinkedCells();
      if (linkedCells.size() == 1) {
        if (random.nextDouble() < braidProbability) {
          List<Cell> neighbors = new ArrayList<>(deadend.getNeighbors());
          neighbors.removeAll(linkedCells);
          deadend.linkCell(neighbors.get(random.nextInt(neighbors.size())));
        }
      }
    });
  }
}
