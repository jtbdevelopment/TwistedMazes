package com.jtbdevelopment.TwistedMazes.state.maze.twod.model;

import java.util.List;
import java.util.stream.Stream;

/**
 * Date: 3/23/19 Time: 11:59 AM
 */
public interface Grid {

  int size();

  <C extends Cell> C getRandomCell();

  <C extends Cell> C getCell(int row, int col);

  <C extends Cell> Stream<C> stream();

  <C extends Cell> Stream<List<C>> streamRows();

  long getDeadEnds();

  int getRows();

  <C extends Cell> List<C> getRow(int row);
}
