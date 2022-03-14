package com.kamilereon.storyteller.annotations;

import java.lang.annotation.*;

/**
 * when targetMethod return true, invoke method attached to
 *
 *
 * */

@Repeatable(CallStageWhenConditionSatisfiedContainer.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface CallStageWhenConditionSatisfied {
    int[] progressToCall() default 0;
    String targetMethodName();
}
