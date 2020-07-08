import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { Lab07SharedModule } from 'app/shared/shared.module';
import { ExpeditionComponent } from './expedition.component';
import { ExpeditionDetailComponent } from './expedition-detail.component';
import { ExpeditionUpdateComponent } from './expedition-update.component';
import { ExpeditionDeleteDialogComponent } from './expedition-delete-dialog.component';
import { expeditionRoute } from './expedition.route';

@NgModule({
  imports: [Lab07SharedModule, RouterModule.forChild(expeditionRoute)],
  declarations: [ExpeditionComponent, ExpeditionDetailComponent, ExpeditionUpdateComponent, ExpeditionDeleteDialogComponent],
  entryComponents: [ExpeditionDeleteDialogComponent]
})
export class Lab07ExpeditionModule {}
