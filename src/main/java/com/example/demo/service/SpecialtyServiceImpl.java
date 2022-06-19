package com.example.demo.service;

import com.example.demo.dao.SpecialtyDao;
import com.example.demo.model.Specialty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SpecialtyServiceImpl implements SpecialtyService {
    @Autowired
    private SpecialtyDao specialtyDao;

    @Override
    public int add(Specialty specialty) {
        return specialtyDao.create(specialty);
    }

    @Override
    public List<Specialty> getAll() {
        return specialtyDao.read();
    }

    @Override
    public Optional<Specialty> get(Integer id) {
        return specialtyDao.read(id);
    }

    @Override
    public List<Specialty> getByName(String name) {
        return specialtyDao.getByName(name);
    }

    @Override
    public int update(Specialty specialty, Integer id) {
        return specialtyDao.update(specialty, id);
    }

    @Override
    public int delete(Integer id) {
        return specialtyDao.delete(id);
    }
}
