import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import './vendor';
import { Lab07SharedModule } from 'app/shared/shared.module';
import { Lab07CoreModule } from 'app/core/core.module';
import { Lab07AppRoutingModule } from './app-routing.module';
import { Lab07HomeModule } from './home/home.module';
import { Lab07EntityModule } from './entities/entity.module';
// jhipster-needle-angular-add-module-import JHipster will add new module here
import { JhiMainComponent } from './layouts/main/main.component';
import { NavbarComponent } from './layouts/navbar/navbar.component';
import { FooterComponent } from './layouts/footer/footer.component';
import { PageRibbonComponent } from './layouts/profiles/page-ribbon.component';
import { ErrorComponent } from './layouts/error/error.component';

@NgModule({
  imports: [
    BrowserModule,
    Lab07SharedModule,
    Lab07CoreModule,
    Lab07HomeModule,
    // jhipster-needle-angular-add-module JHipster will add new module here
    Lab07EntityModule,
    Lab07AppRoutingModule
  ],
  declarations: [JhiMainComponent, NavbarComponent, ErrorComponent, PageRibbonComponent, FooterComponent],
  bootstrap: [JhiMainComponent]
})
export class Lab07AppModule {}
