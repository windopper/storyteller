package Annotation;

import java.lang.annotation.*;

@Repeatable(value = CallStageWhenEnterAreaRadiusContainer.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface CallStageWhenEnterAreaRadius {
    int[] stageToCall();
    String worldName();
    double x();
    double y();
    double z();
    double radius();
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface CallStageWhenEnterAreaRadiusContainer {
    CallStageWhenEnterAreaRadius[] value();
}
