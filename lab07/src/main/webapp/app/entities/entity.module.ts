import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'unit',
        loadChildren: () => import('./unit/unit.module').then(m => m.Lab07UnitModule)
      },
      {
        path: 'image',
        loadChildren: () => import('./image/image.module').then(m => m.Lab07ImageModule)
      },
      {
        path: 'language',
        loadChildren: () => import('./language/language.module').then(m => m.Lab07LanguageModule)
      },
      {
        path: 'expedition',
        loadChildren: () => import('./expedition/expedition.module').then(m => m.Lab07ExpeditionModule)
      }
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ])
  ]
})
export class Lab07EntityModule {}
