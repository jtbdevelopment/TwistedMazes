package com.jtbdevelopment.TwistedMazes.factory.mazes.twod;

import com.jtbdevelopment.TwistedMazes.state.maze.twod.model.Cell;
import com.jtbdevelopment.TwistedMazes.state.maze.twod.model.Grid;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

/**
 * Date: 9/21/18 Time: 6:32 PM
 */
public abstract class AbstractGenerator2DMaze<G extends Grid> implements
    Generator2DMaze<G> {

  final Random random = new Random();

  protected <C extends Cell> C getRandomCell(final Collection<C> cells) {
    return getRandomCellFromList(new ArrayList<>(cells));
  }

  private <C extends Cell> C getRandomCellFromList(final List<C> cells) {
    return cells.get(random.nextInt(cells.size()));
  }
}
