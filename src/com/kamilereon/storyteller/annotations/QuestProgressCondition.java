package com.kamilereon.storyteller.annotations;

import java.lang.annotation.*;

/** 퀘스트 시작 조건 메소드에 주입

 */
@StoryTellerAnnotation
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface QuestProgressCondition {

}
