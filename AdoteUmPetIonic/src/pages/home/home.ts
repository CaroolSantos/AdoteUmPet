import { Component } from '@angular/core';
import { NavController, LoadingController } from 'ionic-angular';
import { PetServicoProvider } from '../../providers/pet-servico/pet-servico';


@Component({
  selector: 'page-home',
  templateUrl: 'home.html'
})
export class HomePage {

  pet: string = "adocao";
  loader: any;
  pets: any;

  constructor(public navCtrl: NavController, public loadingCtrl: LoadingController, public petServico: PetServicoProvider) {

  }

  ionViewDidLoad(){
    this.presentLoading();
    this.petServico.carregarPets()
      .subscribe(
      data => {
        this.pets = data;
        console.log('pets ' + JSON.stringify(this.pets));
        this.loader.dismiss();
      },
      err => {
        console.log('[ERROR] ' + err);
        this.loader.dismiss();
      },
      () => console.log("lista de pets carregada")
      );
  }

  abrirPerfilPet(id){
    this.navCtrl.push('PerfilPetPage', {id: id})
  }

  presentLoading() {

    this.loader = this.loadingCtrl.create({
      content: "Carregando lista de pets..."
    });

    this.loader.present();

  }

}
