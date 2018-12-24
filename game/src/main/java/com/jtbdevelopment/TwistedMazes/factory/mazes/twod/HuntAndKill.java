package com.jtbdevelopment.TwistedMazes.factory.mazes.twod;

import com.jtbdevelopment.TwistedMazes.state.maze.twod.model.AbstractCell;
import com.jtbdevelopment.TwistedMazes.state.maze.twod.model.AbstractGrid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Date: 9/21/18 Time: 6:27 PM
 */
public class HuntAndKill extends AbstractGenerator2DMaze<AbstractGrid<AbstractCell>> {

  @Override
  public void make2DMaze(final AbstractGrid<AbstractCell> grid) {

    AbstractCell currentCell = grid.getRandomCell();
    while (currentCell != null) {
      List<AbstractCell> unvisitedNeighbors = currentCell.getNeighbors()
          .stream()
          .filter(c -> c.getLinkedCells().isEmpty())
          .collect(Collectors.toList());

      if (unvisitedNeighbors.isEmpty()) {
        currentCell = null;
        Optional<AbstractCell> optionalNewStart = grid.stream()
            .filter(c -> c.getLinkedCells().isEmpty())
            .filter(c -> c.getNeighbors().stream().anyMatch(n -> !n.getLinkedCells().isEmpty()))
            .findFirst();
        if (optionalNewStart.isPresent()) {
          currentCell = optionalNewStart.get();
          AbstractCell randomVisitedNeighbor = getRandomCell(
              currentCell.getNeighbors().stream().filter(n -> !n.getLinkedCells().isEmpty())
                  .collect(Collectors.toList()));
          currentCell.linkCell(randomVisitedNeighbor);
        }
      } else {
        AbstractCell neighbor = getRandomCell(unvisitedNeighbors);
        currentCell.linkCell(neighbor);
        currentCell = neighbor;
      }
    }
  }
}
