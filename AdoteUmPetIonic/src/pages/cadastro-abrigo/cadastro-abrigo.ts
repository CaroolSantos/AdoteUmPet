import { Component } from '@angular/core';
import { IonicPage, NavController, NavParams, AlertController } from 'ionic-angular';
import { ImagePicker } from '@ionic-native/image-picker';
import { AbrigoServicoProvider } from '../../providers/abrigo-servico/abrigo-servico';
import { AbrigosPage } from '../abrigos/abrigos';


@IonicPage()
@Component({
  selector: 'page-cadastro-abrigo',
  templateUrl: 'cadastro-abrigo.html',
})
export class CadastroAbrigoPage {

  abrigo: any;
  retornoApi: any;

  constructor(public navCtrl: NavController, public navParams: NavParams, private imagePicker: ImagePicker, public abrigoServico: AbrigoServicoProvider, public alertCtrl: AlertController) {
  }

  uploadFoto() {
    var options = {
      // max images to be selected, defaults to 15. If this is set to 1, upon
      // selection of a single image, the plugin will return it.
      maximumImagesCount: 1,

      // max width and height to allow the images to be.  Will keep aspect
      // ratio no matter what.  So if both are 800, the returned image
      // will be at most 800 pixels wide and 800 pixels tall.  If the width is
      // 800 and height 0 the image will be 800 pixels wide if the source
      // is at least that wide.

      // quality of resized image, defaults to 100
    };

    this.imagePicker.getPictures(options).then((results) => {
      console.log(JSON.stringify(results))
      for (var i = 0; i < results.length; i++) {
        console.log('Image URI: ' + results[i]);
      }
    }, (err) => { console.log("INFO alguma coisa" + err) });
  }

  ionViewDidLoad() {
    console.log('ionViewDidLoad CadastroAbrigoPage');
  }

  salvarAbrigo() {
    this.abrigoServico.salvarAbrigo(this.abrigo)
      .subscribe(
      data => {
        this.retornoApi = data;
        this.exibirAlert("Sucesso!", "Abrigo cadastrado com sucesso.");

        console.log("INFO - sucesso ao salvar abrigo" + data);
        this.navCtrl.setRoot(AbrigosPage, { id: this.retornoApi.abrigo.id });

      },
      err => {
        this.exibirAlert("Erro!", "Ocorreu um erro ao tentar salvar o abrigo, tente novamente.");
      },
      () => console.log("serviço finalizado")
      );

  }

  exibirAlert(titulo, subtitulo){
    let alert = this.alertCtrl.create({
          title: titulo,
          subTitle: subtitulo,
          buttons: ['OK']
        });
        alert.present();

  }

}
