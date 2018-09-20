package com.jtbdevelopment.TwistedMazes.state.maze.twod.model;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
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

  public Cell getRoot() {
    return root;
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

  public Cell maxDistanceCell() {
    Optional<Entry<Cell, Integer>> max = distanceFromRoot.entrySet()
      .stream()
      .max(Comparator.comparing(Entry::getValue));
    return max.orElseThrow(() -> new RuntimeException("no max cell?")).getKey();
  }

}
