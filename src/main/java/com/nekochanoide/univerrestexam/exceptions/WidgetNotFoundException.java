package com.nekochanoide.univerrestexam.exceptions;

public class WidgetNotFoundException extends RuntimeException {
    public WidgetNotFoundException(String name) {
        super("Could not find widget " + name);
    }
}
