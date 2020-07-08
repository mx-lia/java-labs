import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';
import { Language } from 'app/shared/model/language.model';
import { LanguageService } from './language.service';
import { LanguageComponent } from './language.component';
import { LanguageDetailComponent } from './language-detail.component';
import { LanguageUpdateComponent } from './language-update.component';
import { ILanguage } from 'app/shared/model/language.model';

@Injectable({ providedIn: 'root' })
export class LanguageResolve implements Resolve<ILanguage> {
  constructor(private service: LanguageService) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ILanguage> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(map((language: HttpResponse<Language>) => language.body));
    }
    return of(new Language());
  }
}

export const languageRoute: Routes = [
  {
    path: '',
    component: LanguageComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'Languages'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: LanguageDetailComponent,
    resolve: {
      language: LanguageResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'Languages'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: LanguageUpdateComponent,
    resolve: {
      language: LanguageResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'Languages'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: LanguageUpdateComponent,
    resolve: {
      language: LanguageResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'Languages'
    },
    canActivate: [UserRouteAccessService]
  }
];
