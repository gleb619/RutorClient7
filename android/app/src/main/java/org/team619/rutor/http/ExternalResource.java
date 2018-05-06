package org.team619.rutor.http;

import java.io.InputStream;

/**
 * Created by boris on 06.05.2018.
 */
public interface ExternalResource {

    InputStream exchange(String name, String uri);

}
