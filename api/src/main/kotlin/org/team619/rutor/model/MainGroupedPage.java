package org.team619.rutor.model;

import org.team619.rutor.core.DefaultEntity;

import java.util.List;

/**
 * Created by BORIS on 07.08.2016.
 */
public class MainGroupedPage implements DefaultEntity {

    private final List<Group> groups;

    public MainGroupedPage(List<Group> groups) {
        this.groups = groups;
    }

    public List<Group> getGroups() {
        return groups;
    }
}
