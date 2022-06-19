package com.example.demo.service;

import com.example.demo.dao.MedicDao;
import com.example.demo.dto.MedicDto;
import com.example.demo.model.Medic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MedicServiceImpl implements MedicService{
    @Autowired
    private MedicDao medicDao;

    @Override
    public int add(MedicDto medicDto) {
        Medic medicModel = medicDto.toModel();
        return medicDao.create(medicModel);
    }

    @Override
    public List<Medic> getAll() {
        return medicDao.read();
    }

    @Override
    public Optional<Medic> get(Integer id) {
        return medicDao.read(id);
    }

    @Override
    public List<Medic> getByName(String name) {
        return medicDao.getByName(name);
    }

    @Override
    public int update(MedicDto medicDto, Integer id) {
        Medic medicModel = medicDto.toModel();
        return medicDao.update(medicModel, id);
    }

    @Override
    public int delete(Integer id) {
        return medicDao.delete(id);
    }
}
