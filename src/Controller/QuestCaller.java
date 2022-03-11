package Controller;

import Annotation.FinalSequence;
import Annotation.StageSequence;
import Quest.StoryTellerQuest;
import Utils.StoryTellerUtils;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class QuestCaller {
    public static void getRewardIfSatisfied(StoryTellerQuest storyTellerQuest) {

        // 최종 스테이지 단계와 스테이지의 최종 진행 단계를 확인
        Optional<StageSequence> endStageSequence = StageHelper.getEndStageSequence(storyTellerQuest);
        Method[] methods = storyTellerQuest.getClass().getMethods();

        if(endStageSequence.isEmpty()) return;

        int currentStage = storyTellerQuest.stage;
        int currentDetailProgress = storyTellerQuest.detailProgress;
        StageSequence stageSequence = endStageSequence.get();
        int finalStage = stageSequence.stage();
        int finalProgress = stageSequence.finalProgress();

        // 마지막 스테이지와 현재 스테이지가 같고
        // 현재 마지막 진행일때
        if(currentStage == finalStage && currentDetailProgress == finalProgress) {
            Set<Method> rewardMethods = Arrays.stream(methods).filter(m -> m.getAnnotation(FinalSequence.class) != null).collect(Collectors.toSet());
            rewardMethods.forEach(rewardMethod -> {
                try {
                    rewardMethod.setAccessible(true);
                    rewardMethod.invoke(storyTellerQuest);
                    storyTellerQuest.detailProgress = 0;
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
