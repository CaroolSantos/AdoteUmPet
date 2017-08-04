import { NgModule } from '@angular/core';
import { IonicPageModule } from 'ionic-angular';
import { AbrigosPage } from './abrigos';

@NgModule({
  declarations: [
    AbrigosPage,
  ],
  imports: [
    IonicPageModule.forChild(AbrigosPage),
  ],
  exports: [
    AbrigosPage
  ]
})
export class AbrigosPageModule {}
