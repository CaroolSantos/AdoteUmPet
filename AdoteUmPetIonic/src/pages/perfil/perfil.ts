import { Component } from '@angular/core';
import { IonicPage, NavController, NavParams, LoadingController } from 'ionic-angular';
import { UsuarioProvider } from '../../providers/usuario/usuario';

/**
 * Generated class for the PerfilPage page.
 *
 * See http://ionicframework.com/docs/components/#navigation for more info
 * on Ionic pages and navigation.
 */
@IonicPage()
@Component({
  selector: 'page-perfil',
  templateUrl: 'perfil.html',
})
export class PerfilPage {
  loader:any;
  pets: any;
  abrigos:any;

  constructor(public navCtrl: NavController, public navParams: NavParams, public loadingCtrl: LoadingController, public usuarioServico: UsuarioProvider) {
  }

  ionViewDidLoad() {
    console.log('ionViewDidLoad PerfilPage');
   // this.presentLoading();
    
  }

  presentLoading() {

    this.loader = this.loadingCtrl.create({
      content: "Carregando perfil..."
    });

    this.loader.present();

  }

}
