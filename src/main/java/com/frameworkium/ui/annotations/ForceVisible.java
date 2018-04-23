package com.frameworkium.ui.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ForceVisible {

    /** Default value. */
    String value() default "";

    /**
     * If checking for visibility of a list of elements, setting a value
     * will only check for visibility of the first n elements of the list.
     */
    public int checkAtMost() default -1;
}

