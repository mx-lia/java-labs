import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';
import { Expedition } from 'app/shared/model/expedition.model';
import { ExpeditionService } from './expedition.service';
import { ExpeditionComponent } from './expedition.component';
import { ExpeditionDetailComponent } from './expedition-detail.component';
import { ExpeditionUpdateComponent } from './expedition-update.component';
import { IExpedition } from 'app/shared/model/expedition.model';

@Injectable({ providedIn: 'root' })
export class ExpeditionResolve implements Resolve<IExpedition> {
  constructor(private service: ExpeditionService) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IExpedition> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(map((expedition: HttpResponse<Expedition>) => expedition.body));
    }
    return of(new Expedition());
  }
}

export const expeditionRoute: Routes = [
  {
    path: '',
    component: ExpeditionComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'Expeditions'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: ExpeditionDetailComponent,
    resolve: {
      expedition: ExpeditionResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'Expeditions'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: ExpeditionUpdateComponent,
    resolve: {
      expedition: ExpeditionResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'Expeditions'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: ExpeditionUpdateComponent,
    resolve: {
      expedition: ExpeditionResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'Expeditions'
    },
    canActivate: [UserRouteAccessService]
  }
];
