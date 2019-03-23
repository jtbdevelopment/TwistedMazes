package com.jtbdevelopment.TwistedMazes.state.maze.twod.model;

import java.util.Set;

/**
 * Date: 2/24/19 Time: 5:58 PM
 */
public interface Cell {

  int getCol();

  int getRow();

  boolean isLinked(final Cell cell);

  <C extends Cell> Set<C> getLinkedCells();

  <C extends Cell> Set<C> getNeighbors();

  void linkCell(final Cell cell);

  void linkCell(final Cell cell, boolean bidirectional);

  void unlinkCell(Cell cell);

  void unlinkCell(Cell cell, boolean bidirectional);

}
