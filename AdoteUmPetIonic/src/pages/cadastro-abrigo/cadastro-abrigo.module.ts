import { NgModule } from '@angular/core';
import { IonicPageModule } from 'ionic-angular';
import { CadastroAbrigoPage } from './cadastro-abrigo';

@NgModule({
  declarations: [
    CadastroAbrigoPage,
  ],
  imports: [
    IonicPageModule.forChild(CadastroAbrigoPage),
  ],
  exports: [
    CadastroAbrigoPage
  ]
})
export class CadastroAbrigoPageModule {}
