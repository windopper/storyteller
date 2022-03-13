package com.kamilereon.storyteller.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
    StageSequence 의 stage 를 0으로 선언했을 때와 같은 효과

 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface StartSequence {
    int finalProgress();
}
