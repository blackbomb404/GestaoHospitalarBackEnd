package com.example.demo.service;

import java.util.List;
import java.util.Optional;

public interface CrudService<DTO, T, ID> {
    int add(DTO t);

    List<T> getAll();
    Optional<T> get(ID id);

    int update(DTO t, ID id);

    int delete(ID id);
}
