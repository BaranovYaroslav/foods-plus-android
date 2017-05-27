package com.example.app.data.db;

import com.example.app.data.Cafe;

import java.util.List;

/**
 * Created by Ярослав on 27.05.2017.
 */
public interface IRemoteDbHandler {
    List<Cafe> getAll();
}
