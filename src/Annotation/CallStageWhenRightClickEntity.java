package Annotation;

import java.lang.annotation.*;

/**
    특정 엔티티를 우클릭할 때 발동되는 것을 설정하는 어노테이션

 */
@StoryTellerAnnotation
@Repeatable(CallStageWhenRightClickEntityContainer.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface CallStageWhenRightClickEntity {
    int[] progressToCall();
    String entityName();
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface CallStageWhenRightClickEntityContainer {
    CallStageWhenRightClickEntity[] value();
}
