package com.example.demo.service;

import com.example.demo.model.Specialty;

import java.util.List;

public interface SpecialtyService extends CrudService<Specialty, Specialty, Integer> {
    List<Specialty> getByName(String name);
}
