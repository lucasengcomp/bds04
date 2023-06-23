package com.devsuperior.bds04.controllers.event;

import com.devsuperior.bds04.dto.EventDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@RequestMapping(value = "/events")
public interface EventControllerIT {

    @GetMapping
    ResponseEntity<Page<EventDTO>> findAll(Pageable pageable);

    @PostMapping
    ResponseEntity<EventDTO> insert(@Valid @RequestBody EventDTO dto);
}
