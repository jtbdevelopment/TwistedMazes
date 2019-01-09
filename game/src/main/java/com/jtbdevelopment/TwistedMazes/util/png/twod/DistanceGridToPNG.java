package com.jtbdevelopment.TwistedMazes.util.png.twod;

import com.jtbdevelopment.TwistedMazes.state.maze.twod.model.Distances;
import com.jtbdevelopment.TwistedMazes.state.maze.twod.model.rectangle.RectangleCell;
import com.jtbdevelopment.TwistedMazes.state.maze.twod.model.rectangle.RectangleGrid;
import java.awt.Color;
import org.springframework.stereotype.Component;

@Component
public class DistanceGridToPNG {

  public byte[] convert(final RectangleGrid grid, final Distances distances) {
    double farthestDistance = distances.getDistance(distances.maxDistanceCell());
    return new RectangleGridToPNG() {
      @Override
      protected Color backgroundForCell(final RectangleCell cell) {
        double distance = distances.getDistance(cell);
        double intensity = (farthestDistance - distance) / farthestDistance;
        int dark = (int) Math.round(255 * intensity);
        int light = (int) Math.round(127 * intensity) + 127;
        return new Color(dark, light, dark);
      }
    }.convert(grid);
  }

}
