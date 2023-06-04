package org.example.annotation;

public @interface Degrade {
    String businessKey();

    String sceneKey();

    Class methodClass();

    String methodName();
}
