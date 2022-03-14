package com.kamilereon.storyteller.configuration;

import com.kamilereon.storyteller.annotations.QuestProgressCondition;
import com.kamilereon.storyteller.core.StoryTellerQuest;

import java.lang.reflect.Method;
import java.util.function.Predicate;

public enum Filters {
    CAN_START(q -> {
        for(Method method : q.getClass().getDeclaredMethods()) {
            QuestProgressCondition questProgressCondition = method.getAnnotation(QuestProgressCondition.class);
            if(questProgressCondition == null) continue;
            try {
                method.setAccessible(true);
                return (boolean) method.invoke(q);
            }
            catch(Exception e) {
                e.printStackTrace();
            }
        }
        return true;
    }),
    CANNOT_START(q -> !Filters.CAN_START.predicate.test(q)),
    NOT_STARTED(q -> q.stage == 0),
    FINISHED(q -> q.stage == -1),
    PROGRESSING(q -> q.stage >= 1),
    STARTED(q -> q.stage != 0),
    NOT_FINISHED(q -> q.stage != -1),
    NOT_PROGRESSING(q -> q.stage < 1),
    All(q -> true)
    ;

    final Predicate<StoryTellerQuest> predicate;

    Filters(Predicate<StoryTellerQuest> predicate) {
        this.predicate = predicate;
    }
}
