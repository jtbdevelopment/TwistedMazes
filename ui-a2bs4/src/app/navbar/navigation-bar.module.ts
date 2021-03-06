import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {NavigationBarGameMenuToggleComponent} from './navigation-bar-game-menu-toggle.component';
import {NavigationBarNewGameComponent} from './navigation-bar-new-game.component';
import {NavigationBarRightMenuComponent} from './navigation-bar-right-menu.component';
import {NavigationBarComponent} from './navigation-bar.component';
import {GameMenuModule} from '../game-menu/game-menu.module';
import {RouterModule} from '@angular/router';
import {HelpModule} from '../help/help.module';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import {JTBCoreGamesUI} from 'jtb-core-games-ui';

@NgModule({
  imports: [
    BrowserModule,
    JTBCoreGamesUI,
    GameMenuModule,
    RouterModule,
    HelpModule,
    NgbModule
  ],
  exports: [
    NavigationBarComponent,
  ],
  declarations: [
    NavigationBarComponent,
    NavigationBarGameMenuToggleComponent,
    NavigationBarNewGameComponent,
    NavigationBarRightMenuComponent
  ]
})
export class NavigationBarModule {
}

