package com.devsuperior.bds04.controllers.city;

import com.devsuperior.bds04.dto.CityDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@RequestMapping(value = "/cities")
public interface CityIT {

    @GetMapping
    ResponseEntity<List<CityDTO>> findAllCity();

    @PostMapping
    ResponseEntity<CityDTO> insert(@Valid @RequestBody CityDTO dto);
}
