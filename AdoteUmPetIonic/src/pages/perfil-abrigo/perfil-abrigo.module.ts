import { NgModule } from '@angular/core';
import { IonicPageModule } from 'ionic-angular';
import { PerfilAbrigoPage } from './perfil-abrigo';

@NgModule({
  declarations: [
    PerfilAbrigoPage,
  ],
  imports: [
    IonicPageModule.forChild(PerfilAbrigoPage),
  ],
  exports: [
    PerfilAbrigoPage
  ]
})
export class PerfilAbrigoPageModule {}
