package Controller;

import Annotation.QuestProgressCondition;
import Core.CoreData;
import Core.Memory;
import Quest.StoryTellerQuest;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.lang.reflect.Method;
import java.util.Set;
import java.util.stream.Collectors;

public class PlayerWatcher {
    public static void tracePlayer() {
        for(Player player : Bukkit.getOnlinePlayers()) {
            CoreData coreData = Memory.getCoreData(player);

            // 감지된 사용자 지정 퀘스트
            Set<Class<? extends StoryTellerQuest>> quests = Memory.getQuests();

            // 플레이어의 퀘스트
            Set<? extends StoryTellerQuest> playersStoryTellerQuests = coreData.getQuests();

            // 완료되지 않은 퀘스트
            Set<? extends StoryTellerQuest> unfinishedStoryTellerQuests = playersStoryTellerQuests.stream()
                    .filter(q -> q.stage != -1).collect(Collectors.toSet());

            // 퀘스트 시작을 할 수 있는 퀘스트
            Set<? extends StoryTellerQuest> validStoryTellerQuests = unfinishedStoryTellerQuests.stream()
                    .filter(q -> {
                        for(Method method : q.getClass().getDeclaredMethods()) {
                            QuestProgressCondition questProgressCondition = method.getAnnotation(QuestProgressCondition.class);
                            if(questProgressCondition == null) continue;
                            try {
                                method.setAccessible(true);
                                return (boolean) method.invoke(q);
                            }
                            catch(Exception e) {
                                e.printStackTrace();
                                return false;
                            }
                        }
                        return false;
                    }).collect(Collectors.toSet());

            // 퀘스트 진행을 하기 위한 조건 탐색
            for(StoryTellerQuest storyTellerQuest : validStoryTellerQuests) {

                Method[] methods = storyTellerQuest.getClass().getDeclaredMethods();

                QuestCaller.getRewardIfSatisfied(storyTellerQuest);

                for(Method method : methods) {
                    method.setAccessible(true);
                    StageCaller.callStageWhenEnterAreaRadius(player, storyTellerQuest, method);
                    StageCaller.callStageWhenEnterAreaRectangle(player, storyTellerQuest, method);
                    method.setAccessible(false);
                }
            }
        }
    }
}
