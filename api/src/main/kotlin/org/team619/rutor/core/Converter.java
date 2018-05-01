package org.team619.rutor.core;

/**
 * Created by BORIS on 07.08.2016.
 */
public interface Converter<PAGE, INPUT> {

    PAGE convert(INPUT input);

}
