import {TSBGameClassifier} from './tsb-game-classifier.service';
import {TSBGame} from './tsb-game.model';
import {Player, PlayerService} from 'jtb-core-games-ui';
import {BehaviorSubject} from 'rxjs/BehaviorSubject';
import {TestBed} from '@angular/core/testing';

class MockPlayerService {
  public player: BehaviorSubject<Player> = new BehaviorSubject<Player>(null);
}

describe('Service: tsb game clasifier service', () => {
  let playerService: MockPlayerService;
  let classifier: TSBGameClassifier;
  let classifications: string[];

  beforeEach(() => {
    classifications = null;
    TestBed.configureTestingModule({
      providers: [
        {provide: PlayerService, useClass: MockPlayerService},
        TSBGameClassifier
      ]
    });
    classifier = TestBed.get(TSBGameClassifier);
    playerService = TestBed.get(PlayerService) as MockPlayerService;
    classifier.getClassifications().subscribe(c => classifications = c);
  });

  it('classifications are initialized', () => {
    expect(classifications).toEqual(['Your Turn', 'Their Turn', 'Older Games']);
  });

  it('can get icons', () => {
    let icons: Map<string, string>;
    classifier.getIcons().subscribe(x => icons = x);
    expect(icons.get('Your Turn')).toEqual('play');
    expect(icons.get('Their Turn')).toEqual('pause');
    expect(icons.get('Older Games')).toEqual('stop');
  });

  it('Playing comes out your turn', () => {
    const g = new TSBGame({gamePhase: 'Playing'});
    expect(classifier.classifyGame(g)).toEqual('Your Turn');
  });

  it('Round over comes out your turn', () => {
    const g = new TSBGame({gamePhase: 'RoundOver'});
    expect(classifier.classifyGame(g)).toEqual('Your Turn');
  });

  it('Challenged when player md5 is defined and playerState is pending comes out your turn', () => {
    const md5 = 'mymd5';
    const p = new Player({md5: md5});
    playerService.player.next(p);
    const g = new TSBGame({gamePhase: 'Challenged', playerStates: {mymd5: 'Pending'}});
    expect(classifier.classifyGame(g)).toEqual('Your Turn');
  });

  it('Challenged when player md5 is defined and playerState is not pending comes out their turn', () => {
    const md5 = 'mymd5';
    const p = new Player({md5: md5});
    playerService.player.next(p);
    let g = new TSBGame({gamePhase: 'Challenged', playerStates: {mymd5: 'Accepted'}});
    expect(classifier.classifyGame(g)).toEqual('Their Turn');
    g = new TSBGame({gamePhase: 'Challenged', playerStates: {mymd5: 'Quit'}});
    expect(classifier.classifyGame(g)).toEqual('Their Turn');
    g = new TSBGame({gamePhase: 'Challenged', playerStates: {mymd5: 'Rejected'}});
    expect(classifier.classifyGame(g)).toEqual('Their Turn');
  });

  it('Challenged when player md5 is defined and playerState is not available comes out their turn', () => {
    const md5 = 'mymd5';
    const p = new Player({md5: md5});
    playerService.player.next(p);
    let g = new TSBGame({gamePhase: 'Challenged', playerStates: {mymd5other: 'Pending'}});
    expect(classifier.classifyGame(g)).toEqual('Their Turn');
    g = new TSBGame({gamePhase: 'Challenged'});
    expect(classifier.classifyGame(g)).toEqual('Their Turn');
  });

  it('Challenged when player md5 is not defined comes out their turn', () => {
    const p = new Player();
    playerService.player.next(p);
    const g = new TSBGame({gamePhase: 'Challenged', playerStates: {mymd5other: 'Pending'}});
    expect(classifier.classifyGame(g)).toEqual('Their Turn');
  });

  it('Challenged when player is not defined comes out their turn', () => {
    const g = new TSBGame({gamePhase: 'Challenged', playerStates: {mymd5other: 'Pending'}});
    expect(classifier.classifyGame(g)).toEqual('Their Turn');
  });

  it('Quit over comes out older games', () => {
    const g = new TSBGame({gamePhase: 'Quit'});
    expect(classifier.classifyGame(g)).toEqual('Older Games');
  });

  it('NextRoundStarted over comes out older games', () => {
    const g = new TSBGame({gamePhase: 'NextRoundStarted'});
    expect(classifier.classifyGame(g)).toEqual('Older Games');
  });

  it('Declined over comes out older games', () => {
    const g = new TSBGame({gamePhase: 'Declined'});
    expect(classifier.classifyGame(g)).toEqual('Older Games');
  });

  it('Everything else comes out their turn', () => {
    let g = new TSBGame({gamePhase: 'Other'});
    expect(classifier.classifyGame(g)).toEqual('Their Turn');
    g = new TSBGame();
    expect(classifier.classifyGame(g)).toEqual('Their Turn');
  });
});
