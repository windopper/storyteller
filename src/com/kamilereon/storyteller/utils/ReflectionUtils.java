package com.kamilereon.storyteller.utils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class ReflectionUtils {
    public static Set<Method> getMethodsWhichAnnotationHas(Class<? extends Annotation> annotation, Method[] methods) {
        return Arrays.stream(methods).filter(m -> m.getAnnotation(annotation) != null).collect(Collectors.toSet());
    }
}
