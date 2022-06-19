package com.example.demo.service;

import com.example.demo.dto.MedicDto;
import com.example.demo.model.Medic;

import java.util.List;

public interface MedicService extends CrudService<MedicDto, Medic, Integer>{
    List<Medic> getByName(String name);
}
