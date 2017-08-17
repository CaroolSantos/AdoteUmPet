import { Component } from '@angular/core';
import { IonicPage, NavController, NavParams, AlertController } from 'ionic-angular';
import { ImagePicker } from '@ionic-native/image-picker';
import { PetServicoProvider } from '../../providers/pet-servico/pet-servico';
import { HomePage } from '../home/home';





@Component({
  selector: 'page-cadastro-pet',
  templateUrl: 'cadastro-pet.html',
})
export class CadastroPetPage {

  pet={};
  retornoApi: any;

  constructor(public navCtrl: NavController, public navParams: NavParams, private imagePicker: ImagePicker
    , public petServico: PetServicoProvider, public alertCtrl: AlertController) {
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
    console.log('ionViewDidLoad CadastroPetPage');
  }

  salvarPet() {

    console.log('INFO - info pets ' + JSON.stringify(this.pet));
    // this.petServico.salvarPet(this.pet)
    //   .subscribe(
    //   data => {
    //     console.log("INFO - sucesso ao salvar pet " + JSON.stringify(data));
    //     this.retornoApi = data;
    //     this.exibirAlert("Sucesso!", "Pet cadastrado com sucesso.");

        
    //     this.navCtrl.setRoot(HomePage, { id: this.retornoApi.Id });

    //   },
    //   err => {
    //     this.exibirAlert("Erro!", "Ocorreu um erro ao tentar salvar o pet, tente novamente.");
    //   },
    //   () => console.log("serviço finalizado")
    //   );

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
