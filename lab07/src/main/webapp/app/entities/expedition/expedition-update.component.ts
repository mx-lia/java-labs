import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';
import { IExpedition, Expedition } from 'app/shared/model/expedition.model';
import { ExpeditionService } from './expedition.service';
import { IUnit } from 'app/shared/model/unit.model';
import { UnitService } from 'app/entities/unit/unit.service';

@Component({
  selector: 'jhi-expedition-update',
  templateUrl: './expedition-update.component.html'
})
export class ExpeditionUpdateComponent implements OnInit {
  isSaving: boolean;

  units: IUnit[];
  deadLineDp: any;

  editForm = this.fb.group({
    id: [],
    complexity: [],
    dispatchTime: [],
    deadLine: [],
    rate: [null, [Validators.min(0), Validators.max(1)]],
    unit: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected expeditionService: ExpeditionService,
    protected unitService: UnitService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ expedition }) => {
      this.updateForm(expedition);
    });
    this.unitService
      .query()
      .subscribe((res: HttpResponse<IUnit[]>) => (this.units = res.body), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(expedition: IExpedition) {
    this.editForm.patchValue({
      id: expedition.id,
      complexity: expedition.complexity,
      dispatchTime: expedition.dispatchTime != null ? expedition.dispatchTime.format(DATE_TIME_FORMAT) : null,
      deadLine: expedition.deadLine,
      rate: expedition.rate,
      unit: expedition.unit
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const expedition = this.createFromForm();
    if (expedition.id !== undefined) {
      this.subscribeToSaveResponse(this.expeditionService.update(expedition));
    } else {
      this.subscribeToSaveResponse(this.expeditionService.create(expedition));
    }
  }

  private createFromForm(): IExpedition {
    return {
      ...new Expedition(),
      id: this.editForm.get(['id']).value,
      complexity: this.editForm.get(['complexity']).value,
      dispatchTime:
        this.editForm.get(['dispatchTime']).value != null ? moment(this.editForm.get(['dispatchTime']).value, DATE_TIME_FORMAT) : undefined,
      deadLine: this.editForm.get(['deadLine']).value,
      rate: this.editForm.get(['rate']).value,
      unit: this.editForm.get(['unit']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IExpedition>>) {
    result.subscribe(() => this.onSaveSuccess(), () => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }

  trackUnitById(index: number, item: IUnit) {
    return item.id;
  }
}
