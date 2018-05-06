package org.team619.rutor.converter;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.team619.rutor.core.Converter;
import org.team619.rutor.core.Logger;
import org.team619.rutor.model.Group;
import org.team619.rutor.model.MainGroupedPage;
import org.team619.rutor.model.Row;
import org.team619.rutor.util.Util;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Created by BORIS on 07.08.2016.
 */
public class MainPageGroupedConverter extends RowConverter implements Converter<MainGroupedPage, Document> {

    public MainPageGroupedConverter(Logger logger) {
        super(logger);
    }

    @Override
    public MainGroupedPage convert(Document input) {
        List<Element> elements = input.select(Selectors.GROUP).first().children().stream()
                .skip(1)
                .collect(Collectors.toList());
        List<List<Element>> rawGroups = Util.batch(elements, 2);
        List<Group> groups = rawGroups.stream()
                .map(group -> convertToGroup(group))
                .collect(Collectors.toList());

        return new MainGroupedPage(groups);
    }

    /* =================== */

    private Group convertToGroup(List<Element> group) {
        int index = 0;
        Element header = group.get(index++);
        Element topics = group.get(index++);
        List<Row> rows = topics.select(Selectors.ROWS).stream()
                .skip(1)
                .map(tr -> parseRow(tr.getElementsByTag(Selectors.TD)))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        return new Group(header.text(), rows);
    }

    public interface Selectors extends Serializable {

        String GROUP = "#index";
        String ROWS = "tbody > tr";
        String TD = "td";

    }

}
