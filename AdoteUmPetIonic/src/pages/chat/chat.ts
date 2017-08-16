import { Component } from '@angular/core';
import { IonicPage, NavController, NavParams } from 'ionic-angular';
import { AngularFireDatabase, FirebaseListObservable } from 'angularfire2/database';

/**
 * Generated class for the ChatPage page.
 *
 * See http://ionicframework.com/docs/components/#navigation for more info
 * on Ionic pages and navigation.
 */
@IonicPage()
@Component({
  selector: 'page-chat',
  templateUrl: 'chat.html',
})
export class ChatPage {
  lista: FirebaseListObservable<any>;
  mensagem: string;


  constructor(public navCtrl: NavController, public navParams: NavParams, public afDatabase: AngularFireDatabase) {
    this.lista = afDatabase.list('');
  }

  ionViewDidLoad() {
    console.log('ionViewDidLoad ChatPage');
  }

  enviar(){
    let m = {
      texto: this.mensagem,
      data: new Date()
    };
    this.lista.push(m).then(() => {
      this.mensagem = "";
    });
  }

}
