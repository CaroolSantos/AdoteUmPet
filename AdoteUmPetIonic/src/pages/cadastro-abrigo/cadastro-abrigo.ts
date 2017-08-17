import { Component } from '@angular/core';
import { IonicPage, NavController, NavParams, AlertController } from 'ionic-angular';
import { ImagePicker } from '@ionic-native/image-picker';
import { AbrigoServicoProvider } from '../../providers/abrigo-servico/abrigo-servico';
import { ContasServicoProvider } from '../../providers/contas-servico/contas-servico';
import { NativeStorage } from '@ionic-native/native-storage';
import { AbrigosPage } from '../abrigos/abrigos';
import { LoginPage } from '../login/login';

@IonicPage()
@Component({
  selector: 'page-cadastro-abrigo',
  templateUrl: 'cadastro-abrigo.html',
})
export class CadastroAbrigoPage {

  abrigo={};

  constructor(public navCtrl: NavController, public navParams: NavParams, private imagePicker: ImagePicker, public abrigoServico: AbrigoServicoProvider, 
    public alertCtrl: AlertController, public storage: NativeStorage, public accountService: ContasServicoProvider) {
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
    console.log('INFO - info abrigos ' + JSON.stringify(this.abrigo));
    this.storage.getItem('access_token')
      .then(data => {
        //chama serviço para salvar abrigo enviando access_token
        console.log('INFO - salvar abrigo access_token ' + data);
        this.abrigoServico.salvarAbrigo(this.abrigo, data)
          .subscribe(
          data => {
            console.log("INFO - sucesso ao salvar abrigo " + JSON.stringify(data));
            this.exibirAlert("Sucesso!", "Abrigo cadastrado com sucesso.");

            this.navCtrl.setRoot(AbrigosPage, { id: data.id });

      },
      err => {
        console.log('ERROR - problema ao salvar abrigo ' + err);

            if (err === 'Unauthorized') {
              //access_token expirou, pegar novos tokens com refresh_token
              this.storage.getItem('refresh_token')
                .then(
                data => {
                  this.accountService.getToken(data)
                    .subscribe(data => {
                      //sobrescreve access e refresh token
                      this.storage.setItem('access_token', data.access_token);
                      this.storage.setItem('refresh_token', data.refresh_token);
                      this.storage.setItem('username', data.userName)

                      this.salvarAbrigo();

                    }, err => {
                      //precisa autenticar novamente para atualizar tokens
                      this.exibirAlert('Usuário não autenticado', 'Suas credenciais expiraram, por favor realize o login novamente');
                      this.navCtrl.setRoot(LoginPage);
                    })
                },
                err => {
                  //caso não possua mais refresh_token usuário precisa logar novamente
                  this.exibirAlert('Usuário não autenticado', 'Suas credenciais expiraram, por favor realize o login novamente');
                  this.navCtrl.setRoot(LoginPage);
                })

            }else{
              this.exibirAlert("Erro!", "Ocorreu um erro ao tentar salvar o abrigo, tente novamente.");
            }
      },
      () => console.log("serviço finalizado")
      );

    })
  }

  exibirAlert(titulo, subtitulo) {
    let alert = this.alertCtrl.create({
      title: titulo,
      subTitle: subtitulo,
      buttons: ['OK']
    });
    alert.present();

  }

}
