package com.jtbdevelopment.TwistedMazes.state.maze.twod;

import java.util.LinkedList;
import java.util.Queue;
import org.springframework.stereotype.Component;

/**
 * Date: 9/6/18 Time: 6:43 PM
 */
@Component
public class DijkstraDistancesCalculator {

  public Distances computeDistances(final Cell root) {
    Distances distances = new Distances(root);
    Queue<Cell> frontier = new LinkedList<>();
    frontier.add(root);
    while (!frontier.isEmpty()) {
      Cell current = frontier.poll();
      int currentDistance = distances.getDistance(current);
      current.getLinkedCells().forEach(linked -> {
        if (!distances.hasCell(linked)) {
          distances.addDistance(linked, currentDistance + 1);
          frontier.add(linked);
        }
      });


    }
    return distances;
  }
}
