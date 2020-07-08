import { Moment } from 'moment';
import { IUnit } from 'app/shared/model/unit.model';

export interface IImage {
  id?: number;
  imageContentType?: string;
  image?: any;
  setUpDate?: Moment;
  units?: IUnit[];
}

export class Image implements IImage {
  constructor(
    public id?: number,
    public imageContentType?: string,
    public image?: any,
    public setUpDate?: Moment,
    public units?: IUnit[]
  ) {}
}
