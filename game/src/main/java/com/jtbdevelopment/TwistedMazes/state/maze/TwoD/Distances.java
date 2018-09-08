package com.jtbdevelopment.TwistedMazes.state.maze.twod;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Date: 9/6/18 Time: 6:43 PM
 */
public class Distances {

  private final Cell root;
  private final Map<Cell, Integer> distanceFromRoot = new HashMap<>();

  public Distances(Cell root) {
    this.root = root;
    distanceFromRoot.put(root, 0);
  }

  public void addDistance(final Cell cell, int distance) {
    distanceFromRoot.put(cell, distance);
  }

  public int getDistance(final Cell cell) {
    return distanceFromRoot.get(cell);
  }

  public Set<Cell> getCells() {
    return distanceFromRoot.keySet();
  }

  public boolean hasCell(final Cell cell) {
    return distanceFromRoot.containsKey(cell);
  }

  public Distances pathTo(final Cell goal) {
    Cell current = goal;
    Distances breadcrumbs = new Distances(root);
    breadcrumbs.addDistance(current, this.getDistance(current));
    while (!current.equals(goal)) {
      for (Cell neighbor : current.getLinkedCells()) {
        if (getDistance(neighbor) < getDistance(current)) {
          breadcrumbs.addDistance(neighbor, getDistance(neighbor));
          current = neighbor;
        }
      }
    }
    return breadcrumbs;
  }

}
