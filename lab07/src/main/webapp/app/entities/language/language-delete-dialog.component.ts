import { Component } from '@angular/core';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ILanguage } from 'app/shared/model/language.model';
import { LanguageService } from './language.service';

@Component({
  templateUrl: './language-delete-dialog.component.html'
})
export class LanguageDeleteDialogComponent {
  language: ILanguage;

  constructor(protected languageService: LanguageService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.languageService.delete(id).subscribe(() => {
      this.eventManager.broadcast({
        name: 'languageListModification',
        content: 'Deleted an language'
      });
      this.activeModal.dismiss(true);
    });
  }
}
