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

  private final AbstractCell root;
  private final Map<AbstractCell, Integer> distanceFromRoot = new HashMap<>();

  public Distances(AbstractCell root) {
    this.root = root;
    distanceFromRoot.put(root, 0);
  }

  public AbstractCell getRoot() {
    return root;
  }

  public void addDistance(final AbstractCell cell, int distance) {
    if (cell != null) {
      distanceFromRoot.put(cell, distance);
    }
  }

  public int getDistance(final AbstractCell cell) {
    if (cell != null) {
      return distanceFromRoot.get(cell);
    }
    return 0;
  }

  public Set<AbstractCell> getCells() {
    return distanceFromRoot.keySet();
  }

  public boolean hasCell(final AbstractCell cell) {
    return cell != null && distanceFromRoot.containsKey(cell);
  }

  public AbstractCell maxDistanceCell() {
    Optional<Entry<AbstractCell, Integer>> max = distanceFromRoot.entrySet()
        .stream()
        .max(Comparator.comparing(Entry::getValue));
    return max.orElseThrow(() -> new RuntimeException("no max cell?")).getKey();
  }

}
