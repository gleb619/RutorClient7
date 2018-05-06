package org.team619.rutor.converter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.team619.rutor.core.Converter;
import org.team619.rutor.core.DefaultEntity;
import org.team619.rutor.core.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Comparator;
import java.util.List;

public class RutorConverter implements Converter<DefaultEntity, InputStream> {

    private final Logger logger;
    private final String baseUri;
    private final List<Converter<? extends DefaultEntity, Document>> converters;

    public RutorConverter(Logger logger, String baseUri, List<Converter<? extends DefaultEntity, Document>> converters) {
        this.logger = logger;
        this.baseUri = baseUri;
        this.converters = converters;
    }

    @Override
    public DefaultEntity convert(InputStream inputStream) {
        DefaultEntity output = null;
        try {
            Document document = Jsoup.parse(inputStream, StandardCharsets.UTF_8.name(), baseUri);
            output = converters.stream()
                    .filter(converter -> converter.support(document))
                    .sorted(Comparator.comparing(Converter::priority))
                    .findFirst()
                    .map(converter -> converter.convert(document))
                    .orElse(null);
        } catch (IOException e) {
            logger.error(e, "Can't read document");
        }

        return output;
    }

}
