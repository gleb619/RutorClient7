package org.team619.rutor.converter;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.team619.rutor.core.Converter;
import org.team619.rutor.core.Logger;
import org.team619.rutor.model.MainPlainPage;
import org.team619.rutor.model.Row;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by BORIS on 07.08.2016.
 */
public class MainPagePlainConverter extends RowConverter implements Converter<MainPlainPage, Document> {

    public MainPagePlainConverter(Logger logger) {
        super(logger);
    }

    @Override
    public MainPlainPage convert(Document input) {
        List<Row> rows = Stream.of(input.select(Selectors.ROWS))
                .flatMap(elements -> elements.stream())
                .skip(1)
                .map(element -> toRow(element))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        MainPlainPage page = new MainPlainPage(rows);

        return page;
    }

    private Row toRow(Element element) {
        Elements tds = element.getElementsByTag(Selectors.TD);
        Row output = parseRow(tds);

        return output;
    }

    /* =================== */

    public interface Selectors extends Serializable {

        String ROWS = "#index tr";
        String TD = "td";

    }

}
