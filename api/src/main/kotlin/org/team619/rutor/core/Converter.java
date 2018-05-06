package org.team619.rutor.core;

import org.jsoup.nodes.Element;

/**
 * Created by BORIS on 07.08.2016.
 */
public interface Converter<OUTPUT, INPUT> {

    OUTPUT convert(INPUT input);

    default boolean support(Element element) {
        return Boolean.FALSE;
    }

    default int priority() {
        return 1;
    }

}
