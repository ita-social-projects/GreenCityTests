package com.softserve.edu.greencity.ui.tests.runner;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * An annotation that skips tests when running remotely.
 * Implementation: RemoteSkipTestAnalyzer.java
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface LocalOnly {

}
