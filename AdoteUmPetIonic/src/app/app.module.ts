import { BrowserModule } from '@angular/platform-browser';
import { ErrorHandler, NgModule } from '@angular/core';
import { IonicApp, IonicErrorHandler, IonicModule } from 'ionic-angular';

import { MyApp } from './app.component';
import { HomePage } from '../pages/home/home';
import { LoginPage } from '../pages/login/login';
import { ListPage } from '../pages/list/list';
import { CadastroPetPage } from '../pages/cadastro-pet/cadastro-pet';
import { PerfilPage } from '../pages/perfil/perfil';
import { AbrigosPage } from '../pages/abrigos/abrigos';
import { CadastroAbrigoPage } from '../pages/cadastro-abrigo/cadastro-abrigo';

import { StatusBar } from '@ionic-native/status-bar';
import { SplashScreen } from '@ionic-native/splash-screen';
import { NativeStorage } from '@ionic-native/native-storage';
import { Facebook } from '@ionic-native/facebook';
import { GooglePlus } from '@ionic-native/google-plus';
import { ImagePicker } from '@ionic-native/image-picker';
import { PetServicoProvider } from '../providers/pet-servico/pet-servico';
import { HttpModule } from '@angular/http';
import { AbrigoServicoProvider } from '../providers/abrigo-servico/abrigo-servico';

@NgModule({
  declarations: [
    MyApp,
    HomePage,
    ListPage,
    LoginPage,
    CadastroPetPage,
    PerfilPage,
    AbrigosPage,
    CadastroAbrigoPage
  ],
  imports: [
    BrowserModule,
    IonicModule.forRoot(MyApp),
    HttpModule
  ],
  bootstrap: [IonicApp],
  entryComponents: [
    MyApp,
    HomePage,
    ListPage,
    LoginPage,
    CadastroPetPage,
    PerfilPage,
    AbrigosPage,
    CadastroAbrigoPage
  ],
  providers: [
    StatusBar,
    SplashScreen,
    NativeStorage,
    Facebook,
    GooglePlus,
    ImagePicker,
    {provide: ErrorHandler, useClass: IonicErrorHandler},
    PetServicoProvider,
    AbrigoServicoProvider
  ]
})
export class AppModule {}
