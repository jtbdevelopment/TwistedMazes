package com.jtbdevelopment.TwistedMazes.factory.mazes.twod;

import com.jtbdevelopment.TwistedMazes.state.maze.twod.model.AbstractCell;
import com.jtbdevelopment.TwistedMazes.state.maze.twod.model.AbstractGrid;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

/**
 * Date: 9/21/18 Time: 6:32 PM
 */
public abstract class AbstractGenerator2DMaze<G extends AbstractGrid> implements
    Generator2DMaze<G> {

  final Random random = new Random();

  protected <C extends AbstractCell> C getRandomCell(final Collection<C> cells) {
    return getRandomCellFromList(new ArrayList<>(cells));
  }

  private <C extends AbstractCell> C getRandomCellFromList(final List<C> cells) {
    return cells.get(random.nextInt(cells.size()));
  }
}
