package Annotation;

import java.lang.annotation.*;

/**
    storyteller 의 어노테이션임을 알려주는 어노테이션
 */
@StoryTellerAnnotation
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.ANNOTATION_TYPE)
public @interface StoryTellerAnnotation {

}
