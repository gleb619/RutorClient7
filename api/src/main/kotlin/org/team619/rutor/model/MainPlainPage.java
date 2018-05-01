package org.team619.rutor.model;

import org.team619.rutor.model.core.DefaultEntity;

import java.util.List;

/**
 * Created by BORIS on 07.08.2016.
 */
public class MainPlainPage implements DefaultEntity {

    private final List<Row> rows;

    public MainPlainPage(List<Row> rows) {
        super();
        this.rows = rows;
    }

    public List<Row> getRows() {
        return rows;
    }

}
