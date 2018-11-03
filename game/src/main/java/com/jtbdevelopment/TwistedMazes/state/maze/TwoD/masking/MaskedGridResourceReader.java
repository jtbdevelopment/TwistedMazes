package com.jtbdevelopment.TwistedMazes.state.maze.twod.masking;

import com.jtbdevelopment.TwistedMazes.state.maze.twod.model.MaskedGrid;
import org.springframework.core.io.Resource;

/**
 * Date: 11/3/18 Time: 4:41 PM
 */
public interface MaskedGridResourceReader {

  MaskedGrid readMaskedGrid(final Resource resource);
}
