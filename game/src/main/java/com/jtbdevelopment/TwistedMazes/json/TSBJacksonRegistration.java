package com.jtbdevelopment.TwistedMazes.json;

import com.fasterxml.jackson.databind.module.SimpleModule;
import com.jtbdevelopment.TwistedMazes.player.TSBPlayerAttributes;
import com.jtbdevelopment.TwistedMazes.state.masking.TSBMaskedGame;
import com.jtbdevelopment.core.spring.jackson.JacksonModuleCustomization;
import com.jtbdevelopment.games.players.GameSpecificPlayerAttributes;
import com.jtbdevelopment.games.state.masking.MaskedMultiPlayerGame;
import org.springframework.stereotype.Component;

/**
 * Date: 2/8/15 Time: 4:08 PM
 */
@Component
public class TSBJacksonRegistration implements JacksonModuleCustomization {

    @Override
    public void customizeModule(final SimpleModule module) {
        module.addAbstractTypeMapping(GameSpecificPlayerAttributes.class, TSBPlayerAttributes.class);
        module.addAbstractTypeMapping(MaskedMultiPlayerGame.class, TSBMaskedGame.class);
        module.registerSubtypes(TSBMaskedGame.class);
    }

}
