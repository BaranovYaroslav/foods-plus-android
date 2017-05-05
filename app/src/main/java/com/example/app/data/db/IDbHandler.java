package com.example.app.data.db;

import com.example.app.data.Cafe;

import java.util.List;

/**
 * Created by Ярослав on 05.05.2017.
 */
public interface IDbHandler {
    public void addCafe(Cafe cafe);
    public List<Cafe> getAllCafes();
}
