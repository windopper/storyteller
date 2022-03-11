package Controller;

import Annotation.StageSequence;
import Quest.StoryTellerQuest;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Optional;

class StageHelper {
    public static Optional<StageSequence> getEndStageSequence(StoryTellerQuest storyTellerQuest) {
        // 최종 스테이지 단계와 스테이지의 최종 진행 단계를 확인
        Method[] methods = storyTellerQuest.getClass().getDeclaredMethods();

        return Arrays.stream(methods)
                .filter(method -> method.getAnnotation(StageSequence.class) != null)
                .map(m -> m.getAnnotation(StageSequence.class))
                .reduce((s1, s2) -> {
                    int s1_stage = s1.stage();
                    int s2_stage = s2.stage();
                    return Math.max(s1_stage, s2_stage) == s1_stage ? s1 : s2;
                });
    }
}
