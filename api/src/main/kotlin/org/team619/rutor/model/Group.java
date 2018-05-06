package org.team619.rutor.model;

import org.team619.rutor.core.DefaultEntity;

import java.util.List;

/**
 * Created by BORIS on 04.09.2016.
 */
public class Group implements DefaultEntity {

    private final String name;
    private final List<Row> rows;

    public Group(String name, List<Row> rows) {
        this.name = name;
        this.rows = rows;
    }

    public String getName() {
        return name;
    }

    public List<Row> getRows() {
        return rows;
    }
}
