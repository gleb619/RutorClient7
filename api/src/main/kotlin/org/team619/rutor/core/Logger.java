package org.team619.rutor.core;

public interface Logger {

    void error(String message);

    void error(Exception e, String message);

    void error(Exception e, String message, String... args);

}
