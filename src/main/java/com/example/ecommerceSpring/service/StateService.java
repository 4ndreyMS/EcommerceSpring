package com.example.ecommerceSpring.service;

import com.example.ecommerceSpring.dtos.StateDto;
import com.example.ecommerceSpring.entities.StateEntity;
import com.example.ecommerceSpring.repositories.StateRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class StateService {
    @Autowired
    private StateRepository stateRepository;
    @Autowired
    private ModelMapper modelMapper;

    public List<StateDto> getAllStates() {
        List<StateDto> stateDtos = new ArrayList<>();
        stateRepository.findAll().forEach(item -> stateDtos.add(modelMapper.map(item, StateDto.class)));

        return stateDtos;
    }

    public boolean create(List<StateDto> stateDto) {
        List<StateEntity> stateEntities = new ArrayList<>();
        stateDto.forEach(item -> stateEntities.add(modelMapper.map(item, StateEntity.class)));
        return stateRepository.saveAll(stateEntities).size() > 1;
    }
}
