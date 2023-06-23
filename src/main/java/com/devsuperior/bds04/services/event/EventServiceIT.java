package com.devsuperior.bds04.services.event;

import com.devsuperior.bds04.dto.EventDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public interface EventServiceIT {

    EventDTO insert(EventDTO dto);

    Page<EventDTO> findAll(Pageable pageRequest);
}
