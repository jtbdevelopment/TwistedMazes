package com.jtbdevelopment.TwistedMazes.state.maze.twod.calculators;

import com.jtbdevelopment.TwistedMazes.state.maze.twod.model.AbstractGrid;
import com.jtbdevelopment.TwistedMazes.state.maze.twod.model.Cell;
import com.jtbdevelopment.TwistedMazes.state.maze.twod.model.Distances;
import org.springframework.stereotype.Component;

@Component
public class LongestPathCalculator {

  private DijkstraDistancesCalculator distancesCalculator;

  public LongestPathCalculator(
    final DijkstraDistancesCalculator distancesCalculator) {
    this.distancesCalculator = distancesCalculator;
  }

  public Distances calcLongestPath(final AbstractGrid grid) {
    Distances initialDistances = distancesCalculator.computeDistances(grid.getRandomCell());
    Cell start = initialDistances.maxDistanceCell();
    return distancesCalculator.computeDistances(start);
  }
}
