package com.jtbdevelopment.TwistedMazes.dao;

import com.jtbdevelopment.TwistedMazes.state.GameFeature;
import com.jtbdevelopment.TwistedMazes.state.TSBGame;
import com.jtbdevelopment.games.mongo.dao.AbstractMongoMultiPlayerGameRepository;
import org.springframework.stereotype.Repository;

/**
 * Date: 7/13/16 Time: 7:13 PM
 */
@Repository
public interface GameRepository extends
        AbstractMongoMultiPlayerGameRepository<GameFeature, TSBGame> {

}
