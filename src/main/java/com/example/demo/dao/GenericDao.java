package com.example.demo.dao;

import java.util.List;
import java.util.Optional;

public interface GenericDao<DTO, T, ID> {
    int create(DTO t);

    Optional<T> read(ID id);
    List<T> read();

    int update(DTO t, ID id);

    int delete(ID id);
}
