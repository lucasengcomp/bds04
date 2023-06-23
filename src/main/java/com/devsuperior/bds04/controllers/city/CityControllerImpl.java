package com.devsuperior.bds04.controllers.city;

import com.devsuperior.bds04.dto.CityDTO;
import com.devsuperior.bds04.services.city.CityServiceIT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class CityControllerImpl implements CityIT {

    @Autowired
    private CityServiceIT service;

    public ResponseEntity<List<CityDTO>> findAllCity() {
        List<CityDTO> list = service.findAllCities();
        return ResponseEntity.ok().body(list);
    }

    public ResponseEntity<CityDTO> insert(CityDTO dto) {
        dto = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }
}
