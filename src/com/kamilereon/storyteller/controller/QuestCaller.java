package com.kamilereon.storyteller.controller;

import com.kamilereon.storyteller.annotations.FinalSequence;
import com.kamilereon.storyteller.annotations.StageSequence;
import com.kamilereon.storyteller.core.StoryTellerQuest;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class QuestCaller {
    public static void callFinalSequence(StoryTellerQuest storyTellerQuest) {

        // 최종 스테이지 단계와 스테이지의 최종 진행 단계를 확인
        Optional<StageSequence> endStageSequence = StageHelper.getEndStageSequence(storyTellerQuest);
        Method[] methods = storyTellerQuest.getClass().getMethods();

        if(endStageSequence.isEmpty()) return;

        int currentStage = storyTellerQuest.stage;
        int currentDetailProgress = storyTellerQuest.progress;
        StageSequence stageSequence = endStageSequence.get();
        int finalStage = stageSequence.stage();
        int finalProgress = stageSequence.finalProgress();

        // 마지막 progress 가 실행되어 다음 스테이지로 넘어갔다면
        if(currentStage > finalStage) {
            Set<Method> rewardMethods = Arrays.stream(methods)
                    .filter(m -> m.getAnnotation(FinalSequence.class) != null)
                    .collect(Collectors.toSet());

            rewardMethods.forEach(rewardMethod -> {
                try {
                    rewardMethod.setAccessible(true);
                    rewardMethod.invoke(storyTellerQuest);
                    storyTellerQuest.progress = 1;
                    storyTellerQuest.stage = -1;
                }
                catch(Exception e) {
                    e.printStackTrace();
                }
                finally {
                    rewardMethod.setAccessible(false);
                }
            });
        }
    }
}
