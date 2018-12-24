package com.jtbdevelopment.TwistedMazes.state.maze.twod.calculators;

import com.jtbdevelopment.TwistedMazes.state.maze.twod.model.AbstractCell;
import com.jtbdevelopment.TwistedMazes.state.maze.twod.model.Distances;

/**
 * Date: 9/6/18 Time: 6:43 PM
 */
public class ShortestDistanceCalculator {

  public Distances pathTo(final Distances rootDistances, final AbstractCell goal) {
    AbstractCell current = goal;
    AbstractCell root = rootDistances.getRoot();
    Distances breadcrumbs = new Distances(root);
    breadcrumbs.addDistance(current, rootDistances.getDistance(current));
    while (!current.equals(root)) {
      for (AbstractCell neighbor : current.getLinkedCells()) {
        if (rootDistances.getDistance(neighbor) < rootDistances.getDistance(current)) {
          breadcrumbs.addDistance(neighbor, rootDistances.getDistance(neighbor));
          current = neighbor;
        }
      }
    }
    return breadcrumbs;
  }
}
