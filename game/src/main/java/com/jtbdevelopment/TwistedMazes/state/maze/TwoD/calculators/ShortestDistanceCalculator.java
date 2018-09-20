package com.jtbdevelopment.TwistedMazes.state.maze.twod.calculators;

import com.jtbdevelopment.TwistedMazes.state.maze.twod.model.Cell;
import com.jtbdevelopment.TwistedMazes.state.maze.twod.model.Distances;

/**
 * Date: 9/6/18 Time: 6:43 PM
 */
public class ShortestDistanceCalculator {

  public Distances pathTo(final Distances rootDistances, final Cell goal) {
    Cell current = goal;
    Cell root = rootDistances.getRoot();
    Distances breadcrumbs = new Distances(root);
    breadcrumbs.addDistance(current, rootDistances.getDistance(current));
    while (!current.equals(root)) {
      for (Cell neighbor : current.getLinkedCells()) {
        if (rootDistances.getDistance(neighbor) < rootDistances.getDistance(current)) {
          breadcrumbs.addDistance(neighbor, rootDistances.getDistance(neighbor));
          current = neighbor;
        }
      }
    }
    return breadcrumbs;
  }
}
