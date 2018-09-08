package com.jtbdevelopment.TwistedMazes.state.masking;

import com.jtbdevelopment.TwistedMazes.state.GameFeature;
import com.jtbdevelopment.TwistedMazes.state.TSBGame;
import com.jtbdevelopment.games.mongo.state.masking.AbstractMongoMultiPlayerGameMasker;
import org.springframework.stereotype.Component;

/**
 * Date: 7/13/16 Time: 7:07 PM
 */
@Component
public class GameMasker extends
    AbstractMongoMultiPlayerGameMasker<GameFeature, TSBGame, TSBMaskedGame> {

  protected TSBMaskedGame newMaskedGame() {
    return new TSBMaskedGame();
  }

}
