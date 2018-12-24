package com.jtbdevelopment.TwistedMazes.state.maze.twod.model.polar;

import com.jtbdevelopment.TwistedMazes.state.maze.twod.model.AbstractCell;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Date: 8/27/18 Time: 9:38 AM
 */
public class PolarCell extends AbstractCell {

  private PolarCell cw, ccw, inward;

  private Set<PolarCell> outward = new HashSet<>();

  PolarCell(int row, int col) {
    super(row, col);
  }

  @Override
  public Set<AbstractCell> getNeighbors() {
    Set<AbstractCell> collected = Stream.of(ccw, cw, inward).filter(Objects::nonNull)
        .collect(Collectors.toSet());
    collected.addAll(outward);
    return collected;
  }

  public PolarCell getCw() {
    return cw;
  }

  void setCw(final PolarCell cw) {
    this.cw = cw;
  }

  public PolarCell getCcw() {
    return ccw;
  }

  void setCcw(final PolarCell ccw) {
    this.ccw = ccw;
  }

  public PolarCell getInward() {
    return inward;
  }

  void setInward(final PolarCell inward) {
    this.inward = inward;
  }

  Set<PolarCell> getOutward() {
    return outward;
  }

  public void setOutward(final Set<PolarCell> outward) {
    this.outward = outward;
  }
}
