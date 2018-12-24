package com.jtbdevelopment.TwistedMazes.factory.mazes.twod;

import com.jtbdevelopment.TwistedMazes.state.maze.twod.model.AbstractCell;
import com.jtbdevelopment.TwistedMazes.state.maze.twod.model.AbstractGrid;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

/**
 * Date: 9/21/18 Time: 6:53 PM
 */
public class RecursiveBacktracker extends AbstractGenerator2DMaze<AbstractGrid> {

  @Override
  public void make2DMaze(final AbstractGrid grid) {
    Stack<AbstractCell> stack = new Stack<>();
    stack.push(grid.getRandomCell());
    while (!stack.isEmpty()) {
      AbstractCell current = stack.peek();
      List<AbstractCell> unvisited = current.getNeighbors().stream()
          .filter(n -> n.getLinkedCells().isEmpty()).collect(Collectors.toList());
      if (unvisited.isEmpty()) {
        stack.pop();
      } else {
        AbstractCell randomCell = getRandomCell(unvisited);
        current.linkCell(randomCell);
        stack.push(randomCell);
      }
    }
  }
}
