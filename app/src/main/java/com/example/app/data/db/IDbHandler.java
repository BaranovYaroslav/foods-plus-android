package com.example.app.data.db;

import com.example.app.data.Cafe;

import java.util.List;

/**
 * Created by Ярослав on 05.05.2017.
 */
public interface IDbHandler {
    void add(Cafe cafe);

    void deleteCafe(Cafe cafe);

    void update(Cafe cafe);

    Cafe find(long id);

    List<Cafe> getAll();

    void initializeDB();
}
