import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IExpedition } from 'app/shared/model/expedition.model';
import { ExpeditionService } from './expedition.service';
import { ExpeditionDeleteDialogComponent } from './expedition-delete-dialog.component';

@Component({
  selector: 'jhi-expedition',
  templateUrl: './expedition.component.html'
})
export class ExpeditionComponent implements OnInit, OnDestroy {
  expeditions: IExpedition[];
  eventSubscriber: Subscription;

  constructor(protected expeditionService: ExpeditionService, protected eventManager: JhiEventManager, protected modalService: NgbModal) {}

  loadAll() {
    this.expeditionService.query().subscribe((res: HttpResponse<IExpedition[]>) => {
      this.expeditions = res.body;
    });
  }

  ngOnInit() {
    this.loadAll();
    this.registerChangeInExpeditions();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: IExpedition) {
    return item.id;
  }

  registerChangeInExpeditions() {
    this.eventSubscriber = this.eventManager.subscribe('expeditionListModification', () => this.loadAll());
  }

  delete(expedition: IExpedition) {
    const modalRef = this.modalService.open(ExpeditionDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.expedition = expedition;
  }
}
