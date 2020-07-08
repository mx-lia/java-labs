import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IUnit } from 'app/shared/model/unit.model';

@Component({
  selector: 'jhi-unit-detail',
  templateUrl: './unit-detail.component.html'
})
export class UnitDetailComponent implements OnInit {
  unit: IUnit;

  constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ unit }) => {
      this.unit = unit;
    });
  }

  byteSize(field) {
    return this.dataUtils.byteSize(field);
  }

  openFile(contentType, field) {
    return this.dataUtils.openFile(contentType, field);
  }
  previousState() {
    window.history.back();
  }
}
