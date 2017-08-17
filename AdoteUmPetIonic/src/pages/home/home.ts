import { Component } from '@angular/core';
import { NavController, LoadingController } from 'ionic-angular';
import { PetServicoProvider } from '../../providers/pet-servico/pet-servico';


@Component({
  selector: 'page-home',
  templateUrl: 'home.html'
})
export class HomePage {

  petfiltro: string = "adocao";
  loader: any;
  pets: any;
  pets_perdidos: any;
  pets_adocao:any;

  constructor(public navCtrl: NavController, public loadingCtrl: LoadingController, public petServico: PetServicoProvider) {

  }

  ionViewDidLoad(){
    this.presentLoading();
    this.petServico.carregarPets()
      .subscribe(
      data => {
        this.pets = data;
        this.pets_adocao = this.pets.filter(x=> x.status=="Adoção");
        console.log('INFO pets adoção ' + JSON.stringify(this.pets_adocao));
        this.pets_perdidos = this.pets.filter(x=> x.status=="Perdido");
        console.log('INFO pets perdidos ' + JSON.stringify(this.pets_perdidos));
        console.log('pets ' + JSON.stringify(this.pets));
        this.loader.dismiss();
        this.petfiltro = "adocao";
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
