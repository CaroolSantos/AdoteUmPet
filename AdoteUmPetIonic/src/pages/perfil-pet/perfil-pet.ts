import { Component } from '@angular/core';
import { IonicPage, NavController, NavParams, AlertController, LoadingController,ActionSheetController  } from 'ionic-angular';
import { PetServicoProvider } from '../../providers/pet-servico/pet-servico';
import { CallNumber } from '@ionic-native/call-number';
import { EmailComposer } from '@ionic-native/email-composer';
import { SocialSharing } from '@ionic-native/social-sharing';



@IonicPage()
@Component({
  selector: 'page-perfil-pet',
  templateUrl: 'perfil-pet.html',
})
export class PerfilPetPage {
  id_Pet: any;
  pet={};
  loader: any;
  userPhone:any;
  userEmail: any;
  petName: any;

  constructor(public navCtrl: NavController, public navParams: NavParams, public loadingCtrl: LoadingController, 
    public petServico: PetServicoProvider, public actionSheetCtrl: ActionSheetController, private callNumber: CallNumber,
    public alertCtrl: AlertController, private emailComposer: EmailComposer, private sharingVar: SocialSharing) {
  }

  ionViewDidLoad() {
    this.presentLoading();
    this.id_Pet = this.navParams.get("id");
    console.log('ionViewDidLoad PerfilPetPage');
 
    this.petServico.abrirPet(this.id_Pet)
    .subscribe(
      data => {
        this.pet = data;
        this.userPhone = data.userPhone;
        this.userEmail = data.userEmail;
        this.petName = data.name;
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

  enviarEmail(){
    console.log(' INFO - enviar email');
      
      
        //Now we know we can send
        let email = {
          to: this.userEmail,
          cc: '',
          bcc: [''],
          attachments: [ ],
          subject: 'Adote Um Pet - Quero adotar ' + this.petName,
          body: '',
          isHtml: true
        };

        // Send a text message using default options
        this.emailComposer.open(email);

  }

  ligar(){
    if(this.userPhone){
      this.callNumber.callNumber(this.userPhone, true)
      .then(() => console.log('Launched dialer!'))
      .catch(() => console.log('Error launching dialer'));
    } else {
      let alert = this.alertCtrl.create({
          title: 'Telefone não disponível',
          subTitle: 'Não há telefone disponível, por favor entre em contato por email.',
          buttons: ['OK']
        });
        alert.present();
    }
  }

   presentActionSheet() {
    let actionSheet = this.actionSheetCtrl.create({
      title: 'Entrar  em contato',
      buttons: [
        {
          text: 'Enviar email',
          handler: () => {
            this.enviarEmail();
          }
        },
        {
          text: 'Ligar',
          handler: () => {
            this.ligar();
          }
        }

      ]
    });
    actionSheet.present();
  }

  share(){
    this.sharingVar.shareViaFacebook("Mensagem de teste", "https://drive.google.com/file/d/0B0uHzB9w3CQuMEZ1Q1lyWUVXNG8/view?usp=sharing",null)
    .then(()=>{
        alert("Successo ao compartilhar!");
      },
      ()=>{
         alert("Falha! Tente novamente.")
      })
  }


}
