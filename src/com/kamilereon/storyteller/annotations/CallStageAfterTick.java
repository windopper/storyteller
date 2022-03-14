package com.kamilereon.storyteller.annotations;

import java.lang.annotation.*;

/**
 * call attached method after tick if possible
 *
 */

@Repeatable(CallStageAfterTickContainer.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface CallStageAfterTick {
    int[] progressToCall() default 0;
    int tick();
}
