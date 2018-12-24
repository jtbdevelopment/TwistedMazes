package com.jtbdevelopment.TwistedMazes.factory.mazes.twod;

import com.jtbdevelopment.TwistedMazes.state.maze.twod.model.rectangle.RectangleCell;
import com.jtbdevelopment.TwistedMazes.state.maze.twod.model.rectangle.RectangleGrid;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.stereotype.Component;

/**
 * Date: 8/27/18 Time: 10:26 AM
 */
@Component
public class BinaryTree extends AbstractGenerator2DMaze<RectangleGrid> {

  @Override
  public void make2DMaze(final RectangleGrid grid) {
    grid.stream().forEach(cell -> {
      List<RectangleCell> neighbors = Stream.of(cell.getNorth(), cell.getEast())
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
