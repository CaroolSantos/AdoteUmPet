import { NgModule } from '@angular/core';
import { IonicPageModule } from 'ionic-angular';
import { CadastroUsuarioPage } from './cadastro-usuario';

@NgModule({
  declarations: [
    CadastroUsuarioPage,
  ],
  imports: [
    IonicPageModule.forChild(CadastroUsuarioPage),
  ],
  exports: [
    CadastroUsuarioPage
  ]
})
export class CadastroUsuarioPageModule {}
