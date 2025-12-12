package org.slf4j;

public interface Logger {
    default void info(String msg) {}
    default void info(String msg, Object... args) {}
}
