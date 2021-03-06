import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiDataUtils } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IImage } from 'app/shared/model/image.model';
import { ImageService } from './image.service';
import { ImageDeleteDialogComponent } from './image-delete-dialog.component';

@Component({
  selector: 'jhi-image',
  templateUrl: './image.component.html'
})
export class ImageComponent implements OnInit, OnDestroy {
  images: IImage[];
  eventSubscriber: Subscription;

  constructor(
    protected imageService: ImageService,
    protected dataUtils: JhiDataUtils,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll() {
    this.imageService.query().subscribe((res: HttpResponse<IImage[]>) => {
      this.images = res.body;
    });
  }

  ngOnInit() {
    this.loadAll();
    this.registerChangeInImages();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: IImage) {
    return item.id;
  }

  byteSize(field) {
    return this.dataUtils.byteSize(field);
  }

  openFile(contentType, field) {
    return this.dataUtils.openFile(contentType, field);
  }

  registerChangeInImages() {
    this.eventSubscriber = this.eventManager.subscribe('imageListModification', () => this.loadAll());
  }

  delete(image: IImage) {
    const modalRef = this.modalService.open(ImageDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.image = image;
  }
}
