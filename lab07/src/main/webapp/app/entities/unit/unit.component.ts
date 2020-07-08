import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiDataUtils } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IUnit } from 'app/shared/model/unit.model';
import { UnitService } from './unit.service';
import { UnitDeleteDialogComponent } from './unit-delete-dialog.component';

@Component({
  selector: 'jhi-unit',
  templateUrl: './unit.component.html'
})
export class UnitComponent implements OnInit, OnDestroy {
  units: IUnit[];
  eventSubscriber: Subscription;

  constructor(
    protected unitService: UnitService,
    protected dataUtils: JhiDataUtils,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll() {
    this.unitService.query().subscribe((res: HttpResponse<IUnit[]>) => {
      this.units = res.body;
    });
  }

  ngOnInit() {
    this.loadAll();
    this.registerChangeInUnits();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: IUnit) {
    return item.id;
  }

  byteSize(field) {
    return this.dataUtils.byteSize(field);
  }

  openFile(contentType, field) {
    return this.dataUtils.openFile(contentType, field);
  }

  registerChangeInUnits() {
    this.eventSubscriber = this.eventManager.subscribe('unitListModification', () => this.loadAll());
  }

  delete(unit: IUnit) {
    const modalRef = this.modalService.open(UnitDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.unit = unit;
  }
}
