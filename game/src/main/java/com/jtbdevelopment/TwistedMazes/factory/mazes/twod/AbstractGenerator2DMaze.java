package com.jtbdevelopment.TwistedMazes.factory.mazes.twod;

import com.jtbdevelopment.TwistedMazes.state.maze.twod.model.Cell;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

/**
 * Date: 9/21/18 Time: 6:32 PM
 */
public class AbstractGenerator2DMaze {

  protected final Random random = new Random();

  protected Cell getRandomCell(final Collection<Cell> cells) {
    return getRandomCell(new ArrayList<>(cells));
  }

  protected Cell getRandomCell(final List<Cell> cells) {
    return cells.get(random.nextInt(cells.size()));
  }
}
