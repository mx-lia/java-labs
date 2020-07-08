import { Component } from '@angular/core';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IExpedition } from 'app/shared/model/expedition.model';
import { ExpeditionService } from './expedition.service';

@Component({
  templateUrl: './expedition-delete-dialog.component.html'
})
export class ExpeditionDeleteDialogComponent {
  expedition: IExpedition;

  constructor(
    protected expeditionService: ExpeditionService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.expeditionService.delete(id).subscribe(() => {
      this.eventManager.broadcast({
        name: 'expeditionListModification',
        content: 'Deleted an expedition'
      });
      this.activeModal.dismiss(true);
    });
  }
}
