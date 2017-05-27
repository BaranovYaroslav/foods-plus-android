package com.example.app.data.db;

import com.example.app.data.Cafe;

import java.util.List;

/**
 * Created by Ярослав on 05.05.2017.
 */
public interface IDbHandler {
    void add(Cafe cafe);

    List<Cafe> getAll();

    List<Cafe> getAllWithCheckFromRemoteDb();
}
