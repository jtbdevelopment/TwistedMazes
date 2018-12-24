package com.jtbdevelopment.TwistedMazes.state.maze.twod.masking;

import com.jtbdevelopment.TwistedMazes.state.maze.twod.model.rectangle.MaskedRectangleGrid;
import org.springframework.core.io.Resource;

/**
 * Date: 11/3/18 Time: 4:41 PM
 */
public interface MaskedGridResourceReader {

  MaskedRectangleGrid readMaskedGrid(final Resource resource);
}
