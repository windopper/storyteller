package com.kamilereon.storyteller.annotations;

import java.lang.annotation.*;

@StoryTellerAnnotation
@Repeatable(CallStageWhenEnterAreaRectangleContainer.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface CallStageWhenEnterAreaRectangle {
    int[] progressToCall() default 0;
    String worldName();
    double x();
    double y();
    double z();
    double dx();
    double dy();
    double dz();
}

