import { Component } from '@angular/core';
import { IonicPage, NavController, NavParams, LoadingController } from 'ionic-angular';
import { AbrigoServicoProvider } from '../../providers/abrigo-servico/abrigo-servico';


@IonicPage()
@Component({
  selector: 'page-abrigos',
  templateUrl: 'abrigos.html',
})
export class AbrigosPage {
  abrigos: any;
  loader: any;

  constructor(public navCtrl: NavController, public navParams: NavParams, public loadingCtrl: LoadingController, public abrigoServico: AbrigoServicoProvider) {
  }

  ionViewDidLoad() {
    this.presentLoading();
    this.abrigoServico.carregarAbrigos()
      .subscribe(
      data => {
        this.abrigos = data;
        console.log('abrigos ' + JSON.stringify(this.abrigos));
        this.loader.dismiss();
      },
      err => {
        console.log('[ERROR] ' + err);
        this.loader.dismiss();
      },
      () => console.log("lista de abrigos carregada")
      );
  }

  abrirPerfilAbrigo(id){
    this.navCtrl.push('PerfilAbrigoPage', {id: id})
  }

  presentLoading() {

    this.loader = this.loadingCtrl.create({
      content: "Carregando lista de abrigos..."
    });

    this.loader.present();

  }


}
