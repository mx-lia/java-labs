import { Moment } from 'moment';
import { IUnit } from 'app/shared/model/unit.model';
import { Complexity } from 'app/shared/model/enumerations/complexity.model';

export interface IExpedition {
  id?: number;
  complexity?: Complexity;
  dispatchTime?: Moment;
  deadLine?: Moment;
  rate?: number;
  unit?: IUnit;
}

export class Expedition implements IExpedition {
  constructor(
    public id?: number,
    public complexity?: Complexity,
    public dispatchTime?: Moment,
    public deadLine?: Moment,
    public rate?: number,
    public unit?: IUnit
  ) {}
}
