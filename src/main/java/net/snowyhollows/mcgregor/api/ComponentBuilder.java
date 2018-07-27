package net.snowyhollows.mcgregor.api;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
public @interface ComponentBuilder {
    String value() default "##same_as_simple_name";
}
