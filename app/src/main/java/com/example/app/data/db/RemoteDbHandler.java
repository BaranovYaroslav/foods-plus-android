package com.example.app.data.db;

import com.example.app.data.Cafe;
import com.example.app.data.Coordinates;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ярослав on 27.05.2017.
 */
public class RemoteDbHandler implements IRemoteDbHandler{

    @Override
    public ArrayList<Cafe> getAll() {
        ArrayList<Cafe> result = new ArrayList<>();

        MongoClientURI uri = new MongoClientURI("mongodb://admin:admin@ds155631.mlab.com:55631/course_work");
        MongoClient mongoClient = new MongoClient(uri);
        MongoDatabase database = mongoClient.getDatabase("course_work");
        MongoCollection<Document> cafes = database.getCollection("cafes");

        for (Document cafeDocument : cafes.find()) {
            Cafe cafe = new Cafe();

            cafe.setId(Long.parseLong(cafeDocument.getString("id")));
            cafe.setName(cafeDocument.getString("name"));
            cafe.setDescription(cafeDocument.getString("description"));
            cafe.setAddress(cafeDocument.getString("address"));
            cafe.setMiddleCost(Double.parseDouble(cafeDocument.getString("middleCost")));
            cafe.setType(cafeDocument.getString("type"));
            cafe.setCoordinates(new Coordinates(Double.parseDouble(cafeDocument.getString("x")),
                    Double.parseDouble(cafeDocument.getString("y"))));

            result.add(cafe);
        }
        return result;
    }


}
