import { Component } from '@angular/core';
import { NavController } from 'ionic-angular';

@Component({
  selector: 'page-home',
  templateUrl: 'home.html'
})
export class HomePage {

  pet: string = "adocao";

  constructor(public navCtrl: NavController) {

  }

  abrirPerfilPet(id){
    this.navCtrl.push('PerfilPetPage', {id: id})
  }

}
