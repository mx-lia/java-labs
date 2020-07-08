package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.Expedition;
import com.mycompany.myapp.repository.ExpeditionRepository;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional; 
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.mycompany.myapp.domain.Expedition}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class ExpeditionResource {

    private final Logger log = LoggerFactory.getLogger(ExpeditionResource.class);

    private static final String ENTITY_NAME = "expedition";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ExpeditionRepository expeditionRepository;

    public ExpeditionResource(ExpeditionRepository expeditionRepository) {
        this.expeditionRepository = expeditionRepository;
    }

    /**
     * {@code POST  /expeditions} : Create a new expedition.
     *
     * @param expedition the expedition to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new expedition, or with status {@code 400 (Bad Request)} if the expedition has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/expeditions")
    public ResponseEntity<Expedition> createExpedition(@Valid @RequestBody Expedition expedition) throws URISyntaxException {
        log.debug("REST request to save Expedition : {}", expedition);
        if (expedition.getId() != null) {
            throw new BadRequestAlertException("A new expedition cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Expedition result = expeditionRepository.save(expedition);
        return ResponseEntity.created(new URI("/api/expeditions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /expeditions} : Updates an existing expedition.
     *
     * @param expedition the expedition to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated expedition,
     * or with status {@code 400 (Bad Request)} if the expedition is not valid,
     * or with status {@code 500 (Internal Server Error)} if the expedition couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/expeditions")
    public ResponseEntity<Expedition> updateExpedition(@Valid @RequestBody Expedition expedition) throws URISyntaxException {
        log.debug("REST request to update Expedition : {}", expedition);
        if (expedition.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Expedition result = expeditionRepository.save(expedition);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, expedition.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /expeditions} : get all the expeditions.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of expeditions in body.
     */
    @GetMapping("/expeditions")
    public List<Expedition> getAllExpeditions() {
        log.debug("REST request to get all Expeditions");
        return expeditionRepository.findAll();
    }

    /**
     * {@code GET  /expeditions/:id} : get the "id" expedition.
     *
     * @param id the id of the expedition to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the expedition, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/expeditions/{id}")
    public ResponseEntity<Expedition> getExpedition(@PathVariable Long id) {
        log.debug("REST request to get Expedition : {}", id);
        Optional<Expedition> expedition = expeditionRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(expedition);
    }

    /**
     * {@code DELETE  /expeditions/:id} : delete the "id" expedition.
     *
     * @param id the id of the expedition to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/expeditions/{id}")
    public ResponseEntity<Void> deleteExpedition(@PathVariable Long id) {
        log.debug("REST request to delete Expedition : {}", id);
        expeditionRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
