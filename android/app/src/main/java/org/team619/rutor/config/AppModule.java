package org.team619.rutor.config;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import org.team619.rutor.http.ExternalResource;
import org.team619.rutor.http.impl.RutorExternalResource;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Omar on 18/02/2017.
 */
@Module
public class AppModule {

    private Context context;

    public AppModule(Context context) {
        this.context = context;
    }

    @Singleton
    @Provides
    public Context provideContext() {
        return context;
    }

    @Singleton
    @Provides
    public SharedPreferences provideSharedPreferences(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    @Singleton
    @Provides
    public ExternalResource provideExternalResource(Context context) {
        return new RutorExternalResource();
    }

}
