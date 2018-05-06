package org.team619.rutor.component;

import org.team619.rutor.MainActivity;
import org.team619.rutor.config.AppModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Omar on 18/02/2017.
 */
@Singleton
@Component(modules = {AppModule.class})
public interface BasicComponent {

    void inject(MainActivity activity);

}
