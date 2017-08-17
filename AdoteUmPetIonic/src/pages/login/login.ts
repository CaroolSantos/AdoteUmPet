import { Component } from '@angular/core';
import { IonicPage, NavController, NavParams, LoadingController } from 'ionic-angular';
import { Facebook } from '@ionic-native/facebook';
import { NativeStorage } from '@ionic-native/native-storage';
import { HomePage } from '../home/home';
import { GooglePlus } from '@ionic-native/google-plus';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';


@IonicPage()
@Component({
  selector: 'page-login',
  templateUrl: 'login.html',
})
export class LoginPage {
 FB_APP_ID: number = 1685597175088590;
  credentials = {};
  loginForm:FormGroup;

  constructor(
    public navCtrl: NavController,
    public loadingCtrl: LoadingController,
    public fb: Facebook,
    public nativeStorage: NativeStorage,
    public googlePlus: GooglePlus,
    public formBuilder: FormBuilder
    ) {
    // this.fb.browserInit(this.FB_APP_ID, "v2.8");
    this.loginForm = formBuilder.group({
      Email: ['', Validators.compose([Validators.required, Validators.email])],
      Password: ['', Validators.compose([Validators.required])]
    });
  }

  doFbLogin(){
    let permissions = new Array<string>();
    let nav = this.navCtrl;
    let env = this;
    //the permissions your facebook app needs from the user
    permissions = ["public_profile"];

    this.fb.login(permissions)
    .then(function(response){
      let userId = response.authResponse.userID;
      let params = new Array<string>();
      console.log('entrou no fb login sucess');
      //Getting name and gender properties
      env.fb.api("/me?fields=name,gender", params)
      .then(function(user) {
        console.log('já possui usuario' + JSON.stringify(user));
        user.picture = "https://graph.facebook.com/" + userId + "/picture?type=large";
        //now we have the users info, let's save it in the NativeStorage
        env.nativeStorage.setItem('user',
        {
          Id: userId,
          Name: user.name,
          Gender: user.gender,
          Picture: user.picture
        })
        .then(function(){
          nav.setRoot(HomePage);
        }, function (error) {

          console.log('erro ao salvar no local storage' + JSON.stringify(error));
        })
      })
    }, function(error){
      console.log('erro no login do fb' + JSON.stringify(error));
    });
  }

  doGoogleLogin(){
    let nav = this.navCtrl;
    let env = this;
    let loading = this.loadingCtrl.create({
      content: 'Aguarde...'
    });
    loading.present();
    this.googlePlus.login({
      'scopes': '', // optional, space-separated list of scopes, If not included or empty, defaults to `profile` and `email`.
      'webClientId': '976173937768-9693kcadsdhdl5cp6f101dki7cooa53r.apps.googleusercontent.com', // optional clientId of your Web application from Credentials settings of your project - On Android, this MUST be included to get an idToken. On iOS, it is not required.
      'offline': true
    })
    .then(function (user) {
      loading.dismiss();

      env.nativeStorage.setItem('user', {
        name: user.displayName,
        email: user.email,
        picture: user.imageUrl
      })
      .then(function(){
        nav.setRoot(HomePage);
      }, function (error) {
        console.log(error);
      })
    }, function (error) {
      loading.dismiss();
    });
  }

  doGoogleLogout(){
    let nav = this.navCtrl;
    this.googlePlus.logout()
    .then(function (response) {
      this.storage.remove('user');
      nav.push(LoginPage);
    },function (error) {
      console.log(error);
    })
  }

  doFbLogout(){
		var nav = this.navCtrl;
		this.fb.logout()
		.then(function(response) {
			//user logged out so we will remove him from the NativeStorage
			this.storage.remove('user');
			nav.push(LoginPage);
		}, function(error){
			console.log(error);
		});
  }
  
  abrirCadastroUsuario(){
    this.navCtrl.push('CadastroUsuarioPage');
  }

  login(){
    if(this.loginForm.valid){
      console.log('INFO - form válido ' + JSON.stringify(this.credentials));
    }
  }

}
