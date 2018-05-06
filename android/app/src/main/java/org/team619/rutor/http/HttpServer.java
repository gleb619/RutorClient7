package org.team619.rutor.http;

import org.team619.rutor.http.impl.RutorExternalResource;

import java.io.InputStream;
import java.util.Date;

import javax.inject.Inject;
import javax.inject.Singleton;

import fi.iki.elonen.NanoHTTPD;

/**
 * Created by boris on 06.05.2018.
 */
@Singleton
public class HttpServer extends NanoHTTPD {

    ExternalResource externalResource;

    public HttpServer() {
        super(8080);
        externalResource = new RutorExternalResource();
    }

    @Inject
    public HttpServer(ExternalResource externalResource) {
        super(8080);
        this.externalResource = externalResource;
    }

    @Override
    public Response serve(IHTTPSession session) {
        if (1 == 1) {
            InputStream stream = externalResource.exchange(session.getMethod().name(), "http://rutor.info/new");
            return newChunkedResponse(Response.Status.OK, NanoHTTPD.MIME_HTML, stream);
        }
        return newFixedLengthResponse("TEST12" + new Date());
    }
}
