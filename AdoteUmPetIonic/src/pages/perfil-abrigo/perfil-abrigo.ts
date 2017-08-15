import { Component } from '@angular/core';
import { IonicPage, NavController, NavParams, LoadingController } from 'ionic-angular';
import { AbrigoServicoProvider } from '../../providers/abrigo-servico/abrigo-servico';


/**
 * Generated class for the PerfilAbrigoPage page.
 *
 * See http://ionicframework.com/docs/components/#navigation for more info
 * on Ionic pages and navigation.
 */
@IonicPage()
@Component({
  selector: 'page-perfil-abrigo',
  templateUrl: 'perfil-abrigo.html',
})
export class PerfilAbrigoPage {
  id_Abrigo: any;
  abrigo={};
  loader: any;

  constructor(public navCtrl: NavController, public navParams: NavParams, public loadingCtrl: LoadingController, public abrigoServico: AbrigoServicoProvider) {
    
  }

  ionViewDidLoad() {
    this.id_Abrigo = this.navParams.get("Id");
    console.log('ionViewDidLoad PerfilAbrigoPage');
    this.abrigoServico.abrirAbrigo(this.id_Abrigo)
    .subscribe(
      data => {
        this.abrigo = data;
        console.log('abrigo ' + JSON.stringify(this.abrigo));
        this.loader.dismiss();
      },
      err => {
        console.log('[ERROR] ' + err);
        this.loader.dismiss();
      },
      () => console.log("lista de abrigos carregada")
      );
  }
    presentLoading() {

    this.loader = this.loadingCtrl.create({
      content: "Carregando perfil do abrigo..."
    });

    this.loader.present();

  }

}
