package com.vinayak.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD) //on top of method I want to apply
@Retention(RetentionPolicy.RUNTIME) //implimenting this annotation at run time
public @interface LogRequestAndResponse {
}
