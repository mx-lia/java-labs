import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { Lab07SharedModule } from 'app/shared/shared.module';
import { UnitComponent } from './unit.component';
import { UnitDetailComponent } from './unit-detail.component';
import { UnitUpdateComponent } from './unit-update.component';
import { UnitDeleteDialogComponent } from './unit-delete-dialog.component';
import { unitRoute } from './unit.route';

@NgModule({
  imports: [Lab07SharedModule, RouterModule.forChild(unitRoute)],
  declarations: [UnitComponent, UnitDetailComponent, UnitUpdateComponent, UnitDeleteDialogComponent],
  entryComponents: [UnitDeleteDialogComponent]
})
export class Lab07UnitModule {}
