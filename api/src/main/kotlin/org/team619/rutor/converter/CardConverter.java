package org.team619.rutor.converter;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.team619.rutor.core.Logger;
import org.team619.rutor.core.Converter;
import org.team619.rutor.model.Row;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Created by BORIS on 27.08.2016.
 */
public class CardConverter extends RowConverter implements Converter<List<Row>, Elements> {

    public CardConverter(Logger logger) {
        super(logger);
    }

    @Override
    public List<Row> convert(Elements rows) {
        return rows.stream()
                .skip(1)
                .map(tr -> convertTr(tr))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    public Row convertTr(Element tr) {
        Elements tds = tr.getElementsByTag("td");
        Row row = parseRow(tds);

        return row;
    }

}
