import { Component, ViewChild } from '@angular/core';
import { Nav, Platform } from 'ionic-angular';
import { StatusBar } from '@ionic-native/status-bar';
import { SplashScreen } from '@ionic-native/splash-screen';

import { HomePage } from '../pages/home/home';
import { LoginPage } from '../pages/login/login';
import { ListPage } from '../pages/list/list';
import { CadastroPetPage } from '../pages/cadastro-pet/cadastro-pet';
import { PerfilPage } from '../pages/perfil/perfil';
import { AbrigosPage } from '../pages/abrigos/abrigos';
import { CadastroAbrigoPage } from '../pages/cadastro-abrigo/cadastro-abrigo';
import { NativeStorage } from '@ionic-native/native-storage';
import { ImagePicker } from '@ionic-native/image-picker';


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
        env.nav.setRoot(HomePage);
        splashScreen.hide();
      });

      statusBar.styleDefault();
    });
  

    // used for an example of ngFor and navigation
    this.pages = [
      { title: 'Início', component: HomePage, icone: "paw" },
      { title: 'Meu Perfil', component: PerfilPage, icone: "contact" },
      { title: 'Cadastrar um Pet', component: CadastroPetPage, icone: "add-circle" },
      { title: 'Abrigos', component: AbrigosPage, icone: "home" },
      { title: 'Cadastrar um Abrigo', component: CadastroAbrigoPage, icone: "add-circle" },
      { title: 'Configurações', component: ListPage, icone: "settings" }
    ];

  }

  openPage(page) {
    // Reset the content nav to have just this page
    // we wouldn't want the back button to show in this scenario
    this.nav.setRoot(page.component);
  }
}
