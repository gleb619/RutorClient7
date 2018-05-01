package org.team619.rutor.model;

import org.team619.rutor.model.core.DefaultEntity;

import java.io.File;

/**
 * Created by BORIS on 07.08.2016.
 */
public final class Caption implements DefaultEntity {

    private final String adaptedName;
    private final String name;
    private final String subtitle;
    private final String year;

    public Caption(String title, String year, String subtitle) {
        this.adaptedName = parse(title, 0);
        this.name = parse(title, 1);
        this.subtitle = subtitle;
        this.year = year;
    }

    private String parse(String title, int i) {
        return (title != null && title.contains(File.separator)) ? title.split(File.separator)[i] : title;
    }

    public Caption(String title) {
        this.adaptedName = null;
        this.name = title;
        this.subtitle = null;
        this.year = null;
    }

    public String getAdaptedName() {
        return adaptedName;
    }

    public String getName() {
        return name;
    }

    public String getTitle() {
        if (adaptedName == null) {
            return name;
        } else {
            return adaptedName + File.separator + name;
        }
    }

    public String getSubtitle() {
        return subtitle;
    }

    public String getYear() {
        return year;
    }

}
