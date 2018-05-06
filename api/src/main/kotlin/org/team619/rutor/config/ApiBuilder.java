package org.team619.rutor.config;

import org.jsoup.nodes.Document;
import org.team619.rutor.converter.*;
import org.team619.rutor.core.Converter;
import org.team619.rutor.core.DefaultEntity;
import org.team619.rutor.core.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ApiBuilder {

    private String baseUri;
    private Logger logger;

    private ApiBuilder(String uri) {
        this.baseUri = uri;
    }

    public static ApiBuilder from(String uri) {
        return new ApiBuilder(uri);
    }

    public ApiBuilder withLogger(Logger logger) {
        this.logger = logger;
        return this;
    }

    public RutorConverter build() {
        Objects.requireNonNull(logger, "Logger can't be null");
        List<Converter<? extends DefaultEntity, Document>> converters = new ArrayList<>();
        converters.add(new MainPagePlainConverter(logger));
        converters.add(new MainPageGroupedConverter(logger));
        converters.add(new DetailPageConverter(logger, new CardConverter(logger), new CommentConverter(logger)));
        RutorConverter rutorConverter = new RutorConverter(logger, baseUri, converters);
        return rutorConverter;
    }

}
