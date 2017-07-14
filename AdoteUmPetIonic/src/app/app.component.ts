import { Component, ViewChild } from '@angular/core';
import { Nav, Platform } from 'ionic-angular';
import { StatusBar } from '@ionic-native/status-bar';
import { SplashScreen } from '@ionic-native/splash-screen';

import { HomePage } from '../pages/home/home';
import { LoginPage } from '../pages/login/login';
import { ListPage } from '../pages/list/list';
import { NativeStorage } from '@ionic-native/native-storage';


@Component({
  templateUrl: 'app.html'
})
export class MyApp {
  @ViewChild(Nav) nav: Nav;

  rootPage: any;

  pages: Array<{title: string, component: any, icone: string}>;

  constructor(public platform: Platform, public statusBar: StatusBar, public splashScreen: SplashScreen, public storage: NativeStorage) {
    platform.ready().then(() => {
      // Here we will check if the user is already logged in
      // because we don't want to ask users to log in each time they open the app
      let env = this;
      
      storage.getItem('user')
      .then( function (data) {
        // user is previously logged and we have his data
        // we will let him access the app
        env.nav.push(HomePage);
        splashScreen.hide();
      }, function (error) {
        //we don't have the user data so we will ask him to log in
        env.nav.setRoot(LoginPage);
        splashScreen.hide();
      });

      statusBar.styleDefault();
    });
  

    // used for an example of ngFor and navigation
    this.pages = [
      { title: 'Início', component: HomePage, icone: "paw" },
      { title: 'Perfil', component: HomePage, icone: "contact" },
      { title: 'Abrigos', component: ListPage, icone: "home" },
      { title: 'Configurações', component: ListPage, icone: "settings" }
    ];

  }

  openPage(page) {
    // Reset the content nav to have just this page
    // we wouldn't want the back button to show in this scenario
    this.nav.setRoot(page.component);
  }
}
