package com.devsuperior.bds04.services.city;


import com.devsuperior.bds04.dto.CityDTO;

import java.util.List;

public interface CityServiceIT {

    List<CityDTO> findAllCities();

    CityDTO insert(CityDTO dto);
}
