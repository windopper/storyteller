package Annotation;

import java.lang.annotation.*;

@StoryTellerAnnotation
@Repeatable(CallStageWhenEnterAreaRectangleContainer.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface CallStageWhenEnterAreaRectangle {
    int[] progressToCall();
    String worldName();
    double x();
    double y();
    double z();
    double dx();
    double dy();
    double dz();
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface CallStageWhenEnterAreaRectangleContainer {
    CallStageWhenEnterAreaRectangle[] value();
}
