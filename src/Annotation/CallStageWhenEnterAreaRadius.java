package Annotation;

import java.lang.annotation.*;

/**
    해당 위치 안에 플레이어가 감지되면 발동하는 어노테이션
 */
 @StoryTellerAnnotation
@Repeatable(value = CallStageWhenEnterAreaRadiusContainer.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface CallStageWhenEnterAreaRadius {
    int[] progressToCall() default 0;
    String worldName();
    double x();
    double y();
    double z();
    double radius();
}

