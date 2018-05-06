package org.team619.rutor.http.impl;

import org.team619.rutor.http.ExternalResource;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.inject.Singleton;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import timber.log.Timber;

/**
 * Created by boris on 06.05.2018.
 */
@Singleton
public class RutorExternalResource implements ExternalResource {

    @Override
    public InputStream exchange(String name, String uri) {
        OkHttpClient client = new OkHttpClient.Builder()
                .build();
        Request request = new Request.Builder()
                .url(uri)
                .build();

        InputStream inputStream;

        try (Response response = client.newCall(request).execute()) {
            inputStream = new ByteArrayInputStream(response.body().bytes());
        } catch (IOException e) {
            inputStream = new ByteArrayInputStream(new byte[0]);
            Timber.e(e);
        }

        return inputStream;
    }

}
