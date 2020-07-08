import { Moment } from 'moment';
import { IUser } from 'app/core/user/user.model';
import { IExpedition } from 'app/shared/model/expedition.model';
import { IImage } from 'app/shared/model/image.model';
import { ILanguage } from 'app/shared/model/language.model';

export interface IUnit {
  id?: number;
  hireDate?: Moment;
  biography?: any;
  numberOfTeeth?: number;
  user?: IUser;
  expeditions?: IExpedition[];
  image?: IImage;
  languages?: ILanguage[];
}

export class Unit implements IUnit {
  constructor(
    public id?: number,
    public hireDate?: Moment,
    public biography?: any,
    public numberOfTeeth?: number,
    public user?: IUser,
    public expeditions?: IExpedition[],
    public image?: IImage,
    public languages?: ILanguage[]
  ) {}
}
