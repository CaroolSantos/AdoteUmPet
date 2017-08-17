import { Component } from '@angular/core';
import { IonicPage, NavController, NavParams, LoadingController } from 'ionic-angular';
import { PetServicoProvider } from '../../providers/pet-servico/pet-servico';


@IonicPage()
@Component({
  selector: 'page-perfil-pet',
  templateUrl: 'perfil-pet.html',
})
export class PerfilPetPage {
  id_Pet: any;
  pet={};
  loader: any;

  constructor(public navCtrl: NavController, public navParams: NavParams, public loadingCtrl: LoadingController, public petServico: PetServicoProvider) {
  }

  ionViewDidLoad() {
    this.presentLoading();
    this.id_Pet = this.navParams.get("id");
    console.log('ionViewDidLoad PerfilPetPage');
 
    this.petServico.abrirPet(this.id_Pet)
    .subscribe(
      data => {
        this.pet = data;
        console.log('pet ' + JSON.stringify(this.pet));
        this.loader.dismiss();
      },
      err => {
        console.log('[ERROR] ' + err);
        this.loader.dismiss();
      },
      () => console.log("perfil pet carregado")
      );
  }

  presentLoading() {

    this.loader = this.loadingCtrl.create({
      content: "Carregando perfil do pet..."
    });

    this.loader.present();

  }

}
