import { BrowserModule } from '@angular/platform-browser';
import { ErrorHandler, NgModule } from '@angular/core';
import { IonicApp, IonicErrorHandler, IonicModule } from 'ionic-angular';
import { AngularFireModule } from "angularfire2";
import { SocialSharing } from '@ionic-native/social-sharing';


import { MyApp } from './app.component';
import { HomePage } from '../pages/home/home';
import { LoginPage } from '../pages/login/login';
import { ListPage } from '../pages/list/list';
import { CadastroPetPage } from '../pages/cadastro-pet/cadastro-pet';
import { PerfilPage } from '../pages/perfil/perfil';
import { AbrigosPage } from '../pages/abrigos/abrigos';
import { CadastroAbrigoPage } from '../pages/cadastro-abrigo/cadastro-abrigo';
import { ChatPage } from '../pages/chat/chat';

import { StatusBar } from '@ionic-native/status-bar';
import { SplashScreen } from '@ionic-native/splash-screen';
import { NativeStorage } from '@ionic-native/native-storage';
import { Facebook } from '@ionic-native/facebook';
import { GooglePlus } from '@ionic-native/google-plus';
import { ImagePicker } from '@ionic-native/image-picker';
import { PetServicoProvider } from '../providers/pet-servico/pet-servico';
import { HttpModule } from '@angular/http';
import { AbrigoServicoProvider } from '../providers/abrigo-servico/abrigo-servico';
import { UsuarioProvider } from '../providers/usuario/usuario';
import { ContasServicoProvider } from '../providers/contas-servico/contas-servico';
import { EmailComposer } from '@ionic-native/email-composer';
import { CallNumber } from '@ionic-native/call-number';

export const firebaseConfig = {
  apiKey: "AIzaSyBBFmk1qzJB1odXi3VgGfOhFvY7RY1Hm1A",
    authDomain: "adote-um-pet-381be.firebaseapp.com",
    databaseURL: "https://adote-um-pet-381be.firebaseio.com",
    projectId: "adote-um-pet-381be",
    storageBucket: "",
    messagingSenderId: "138464449951"
}

@NgModule({
  declarations: [
    MyApp,
    HomePage,
    ListPage,
    LoginPage,
    CadastroPetPage,
    PerfilPage,
    AbrigosPage,
    CadastroAbrigoPage,
    ChatPage
  ],
  imports: [
    BrowserModule,
    IonicModule.forRoot(MyApp),
    HttpModule,
    AngularFireModule.initializeApp(firebaseConfig)
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
    CadastroAbrigoPage,
    ChatPage
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
    AbrigoServicoProvider,
    UsuarioProvider,
    AngularFireModule,
    ContasServicoProvider,
    CallNumber,
    EmailComposer,
    SocialSharing    
  ]
})
export class AppModule {}
