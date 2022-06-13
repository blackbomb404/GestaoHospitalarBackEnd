package com.example.demo.service;

import com.example.demo.dao.SpecialtyDao;
import com.example.demo.model.Specialty;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SpecialtyServiceImpl implements SpecialtyService {
    private final SpecialtyDao specialtyDao;

    public SpecialtyServiceImpl(SpecialtyDao specialtyDao) {
        this.specialtyDao = specialtyDao;
    }

    @Override
    public int add(Specialty specialty) {
        return 0;
    }

    @Override
    public List<Specialty> getAll() {
        return specialtyDao.read();
    }

    @Override
    public Optional<Specialty> get(Integer integer) {
        return Optional.empty();
    }

    @Override
    public int update(Specialty specialty, Integer integer) {
        return 0;
    }

    @Override
    public int delete(Integer integer) {
        return 0;
    }
}
