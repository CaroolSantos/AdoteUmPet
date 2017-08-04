import { Component } from '@angular/core';
import { IonicPage, NavController, NavParams } from 'ionic-angular';

/**
 * Generated class for the AbrigosPage page.
 *
 * See http://ionicframework.com/docs/components/#navigation for more info
 * on Ionic pages and navigation.
 */
@IonicPage()
@Component({
  selector: 'page-abrigos',
  templateUrl: 'abrigos.html',
})
export class AbrigosPage {

  constructor(public navCtrl: NavController, public navParams: NavParams) {
  }

  ionViewDidLoad() {
    console.log('ionViewDidLoad AbrigosPage');
  }

  abrirPerfilAbrigo(id){
    this.navCtrl.push('PerfilAbrigoPage', {id: id})
  }

}
