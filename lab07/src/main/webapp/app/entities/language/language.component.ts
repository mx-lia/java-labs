import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ILanguage } from 'app/shared/model/language.model';
import { LanguageService } from './language.service';
import { LanguageDeleteDialogComponent } from './language-delete-dialog.component';

@Component({
  selector: 'jhi-language',
  templateUrl: './language.component.html'
})
export class LanguageComponent implements OnInit, OnDestroy {
  languages: ILanguage[];
  eventSubscriber: Subscription;

  constructor(protected languageService: LanguageService, protected eventManager: JhiEventManager, protected modalService: NgbModal) {}

  loadAll() {
    this.languageService.query().subscribe((res: HttpResponse<ILanguage[]>) => {
      this.languages = res.body;
    });
  }

  ngOnInit() {
    this.loadAll();
    this.registerChangeInLanguages();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: ILanguage) {
    return item.id;
  }

  registerChangeInLanguages() {
    this.eventSubscriber = this.eventManager.subscribe('languageListModification', () => this.loadAll());
  }

  delete(language: ILanguage) {
    const modalRef = this.modalService.open(LanguageDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.language = language;
  }
}
