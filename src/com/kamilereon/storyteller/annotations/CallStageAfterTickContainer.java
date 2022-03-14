package com.kamilereon.storyteller.annotations;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface CallStageAfterTickContainer {
    CallStageAfterTick[] value();
}
