import { NgModule } from '@angular/core';
import { IonicPageModule } from 'ionic-angular';
import { PerfilPetPage } from './perfil-pet';

@NgModule({
  declarations: [
    PerfilPetPage,
  ],
  imports: [
    IonicPageModule.forChild(PerfilPetPage),
  ],
  exports: [
    PerfilPetPage
  ]
})
export class PerfilPetPageModule {}
