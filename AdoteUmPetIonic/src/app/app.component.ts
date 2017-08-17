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
  username = "";
  pages: Array<{ title: string, component: any, icone: string }>;

  constructor(public platform: Platform, public statusBar: StatusBar, public splashScreen: SplashScreen, public storage: NativeStorage) {
    platform.ready().then(() => {
      let env = this;
      
      storage.getItem('access_token')
        .then(function (data) {
          if(data){
<<<<<<< HEAD
            console.log('usuario local ' + data);
=======
            console.log('INFO inicio do app access_token ' + data);
            storage.getItem('username')
              .then((data)=>{
                console.log('INFO username carregado ' + data);
                env.username = data;
              })
>>>>>>> b54f75895ef3efe4cb3cfc5ab7faf269dc1ae41d
            env.nav.push(HomePage);
            splashScreen.hide();
          }
          
        }, function (error) {
          env.nav.setRoot(HomePage);
          splashScreen.hide();
        });

      statusBar.styleDefault();
    });

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
    this.nav.setRoot(page.component);
  }
}
