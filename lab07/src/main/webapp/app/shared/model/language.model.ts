import { IUnit } from 'app/shared/model/unit.model';

export interface ILanguage {
  id?: number;
  title?: string;
  units?: IUnit[];
}

export class Language implements ILanguage {
  constructor(public id?: number, public title?: string, public units?: IUnit[]) {}
}
