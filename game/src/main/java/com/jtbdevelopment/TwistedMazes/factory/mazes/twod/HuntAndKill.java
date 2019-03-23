package com.jtbdevelopment.TwistedMazes.factory.mazes.twod;

import com.jtbdevelopment.TwistedMazes.state.maze.twod.model.AbstractGrid;
import com.jtbdevelopment.TwistedMazes.state.maze.twod.model.Cell;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Date: 9/21/18 Time: 6:27 PM
 */
public class HuntAndKill extends AbstractGenerator2DMaze<AbstractGrid<Cell>> {

  @Override
  public void make2DMaze(final AbstractGrid<Cell> grid) {

    Cell currentCell = grid.getRandomCell();
    while (currentCell != null) {
      List<Cell> unvisitedNeighbors = currentCell.getNeighbors()
          .stream()
          .filter(c -> c.getLinkedCells().isEmpty())
          .collect(Collectors.toList());

      if (unvisitedNeighbors.isEmpty()) {
        currentCell = null;
        Optional<Cell> optionalNewStart = grid.stream()
            .filter(c -> c.getLinkedCells().isEmpty())
            .filter(c -> c.getNeighbors().stream().anyMatch(n -> !n.getLinkedCells().isEmpty()))
            .findFirst();
        if (optionalNewStart.isPresent()) {
          currentCell = optionalNewStart.get();
          Cell randomVisitedNeighbor = getRandomCell(
              currentCell.getNeighbors().stream().filter(n -> !n.getLinkedCells().isEmpty())
                  .collect(Collectors.toList()));
          currentCell.linkCell(randomVisitedNeighbor);
        }
      } else {
        Cell neighbor = getRandomCell(unvisitedNeighbors);
        currentCell.linkCell(neighbor);
        currentCell = neighbor;
      }
    }
  }
}
