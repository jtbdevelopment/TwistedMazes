import {TestBed} from '@angular/core/testing';
import {GameMenuCategoryListComponent} from './game-menu-category-list.component';
import {Component, Input} from '@angular/core';
import {NgbCollapseModule} from '@ng-bootstrap/ng-bootstrap';
import {Game, GameCacheService, MultiPlayerGame, SinglePlayerGame} from 'jtb-core-games-ui';
import {BehaviorSubject} from 'rxjs/BehaviorSubject';
import {Observable} from 'rxjs/Observable';

class MockGameCache {
  public static games: Map<string, BehaviorSubject<any[]>> = new Map<string, BehaviorSubject<any[]>>();

  public getGamesForCategory(category: string): Observable<any[]> {
    return MockGameCache.games.get(category);
  }
}

@Component({
  selector: 'app-game-menu-game-item',
  template: '<p>{{style}}</p><span>{{game.id}}</span>'
})
class MockGameItemComponent {
  @Input() public game: Game;
  @Input() public style: string;
}

describe('Component:  game menu category list component', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [
        NgbCollapseModule
      ],
      declarations: [
        GameMenuCategoryListComponent,
        MockGameItemComponent
      ],
      providers: [
        {provide: GameCacheService, useClass: MockGameCache}
      ]
    });
    TestBed.compileComponents();
  });

  it('initializes and subscribes to category from game cache', () => {
    const fixture = TestBed.createComponent(GameMenuCategoryListComponent);
    const expectedGames = [
      new Game({id: '1', lastUpdate: 10}),
      new MultiPlayerGame({id: '2', lastUpdate: 1}),
      new SinglePlayerGame({id: '3', lastUpdate: 5})
    ];
    const category = 'cat';
    const style = 's';
    MockGameCache.games.set(category, new BehaviorSubject([]));
    fixture.componentInstance.category = category;
    fixture.componentInstance.style = style;
    fixture.detectChanges();
    expect(fixture.componentInstance.games).toEqual([]);
    expect(fixture.componentInstance.isCollapsed).toBeFalsy();
    MockGameCache.games.get(category).next(expectedGames);
    expect(fixture.componentInstance.games).toEqual([expectedGames[1], expectedGames[2], expectedGames[0]]);
    fixture.detectChanges();

    expect(fixture).toMatchSnapshot();
  });

  it('test toggle collapse', () => {
    const fixture = TestBed.createComponent(GameMenuCategoryListComponent);
    expect(fixture.componentInstance.isCollapsed).toBeFalsy();
    fixture.componentInstance.toggleCollapse();
    expect(fixture.componentInstance.isCollapsed).toBeTruthy();
    fixture.componentInstance.toggleCollapse();
    expect(fixture.componentInstance.isCollapsed).toBeFalsy();
  });
});
