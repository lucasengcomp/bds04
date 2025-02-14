package com.devsuperior.bds04.controllers.event;

import com.devsuperior.bds04.dto.EventDTO;
import com.devsuperior.bds04.services.event.EventServiceIT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
public class EventControllerImpl implements EventControllerIT {
    @Autowired
    private EventServiceIT service;

    public ResponseEntity<Page<EventDTO>> findAll(Pageable pageable) {
        PageRequest pageRequest = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by("name"));
        Page<EventDTO> list = service.findAll(pageRequest);
        return ResponseEntity.ok().body(list);
    }

    public ResponseEntity<EventDTO> insert(EventDTO dto) {
        dto = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }
}
