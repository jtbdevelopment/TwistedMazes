package com.jtbdevelopment.TwistedMazes.factory.mazes.twod;

import com.jtbdevelopment.TwistedMazes.state.maze.twod.model.DirectionalCell;
import com.jtbdevelopment.TwistedMazes.state.maze.twod.model.DirectionalGrid;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.stereotype.Component;

/**
 * Date: 8/27/18 Time: 10:26 AM
 *
 * Doesn't work well with triangles
 */
@Component
public class BinaryTree extends AbstractGenerator2DMaze<DirectionalGrid> {

  @Override
  public void make2DMaze(final DirectionalGrid grid) {
    Stream<DirectionalCell> stream = grid.stream();
    stream.forEach(cell -> {
      List<DirectionalCell> neighbors = Stream.of(cell.getNorth(), cell.getEast())
          .filter(Objects::nonNull)
          .collect(Collectors.toList());
      if (neighbors.size() > 0) {
        if (neighbors.size() > 1) {
          cell.linkCell(getRandomCell(neighbors));
        } else {
          cell.linkCell(neighbors.get(0));
        }
      }
    });
  }
}
