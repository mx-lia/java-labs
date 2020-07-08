import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { Lab07SharedModule } from 'app/shared/shared.module';
import { HOME_ROUTE } from './home.route';
import { HomeComponent } from './home.component';

@NgModule({
  imports: [Lab07SharedModule, RouterModule.forChild([HOME_ROUTE])],
  declarations: [HomeComponent]
})
export class Lab07HomeModule {}
