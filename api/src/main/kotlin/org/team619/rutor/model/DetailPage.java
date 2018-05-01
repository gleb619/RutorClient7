package org.team619.rutor.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.team619.rutor.model.core.DefaultEntity;

import java.util.List;

/**
 * Created by BORIS on 07.08.2016.
 */
@Getter
@Setter
@Builder
public class DetailPage implements DefaultEntity {

    private final String id;
    private final String name;
    private final String fileName;
    private final String link;
    private final String body;
    private final String details;
    private final List<Row> bounded;
    private final List<Comment> comments;

}
