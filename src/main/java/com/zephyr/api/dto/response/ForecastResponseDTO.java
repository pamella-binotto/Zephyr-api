package com.zephyr.api.dto.response;

import com.zephyr.api.dto.ForecastItemDTO;

import java.util.List;

public class ForecastResponseDTO {

    private List<ForecastItemDTO> list;

    public ForecastResponseDTO(List<ForecastItemDTO> list) {
        this.list = list;
    }

    public List<ForecastItemDTO> getList() {
        return list;
    }

    public void setList(List<ForecastItemDTO> list) {
        this.list = list;
    }
}
