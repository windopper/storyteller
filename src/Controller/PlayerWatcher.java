package Controller;

import Annotation.CallStageWhenEnterAreaRadius;
import Annotation.QuestStartCondition;
import Annotation.StageSequence;
import Core.CoreData;
import Core.Memory;
import Quest.Quest;
import Utils.LocationUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.lang.reflect.Method;
import java.util.Set;
import java.util.stream.Collectors;

public class PlayerWatcher {
    public static void tracePlayer() {
        for(Player player : Bukkit.getOnlinePlayers()) {
            CoreData coreData = Memory.getCoreData(player);

            // 감지된 사용자 지정 퀘스트
            Set<Class<? extends Quest>> quests = Memory.getQuests();

            // 플레이어의 퀘스트
            Set<Quest> playersQuests = coreData.getQuests();

            // 완료되지 않은 퀘스트
            Set<Quest> unfinishedQuests = playersQuests.stream()
                    .filter(q -> q.progress != -1).collect(Collectors.toSet());

            // 퀘스트 시작을 할 수 있는 퀘스트
            Set<Quest> validQuests = unfinishedQuests.stream()
                    .filter(q -> {
                        for(Method method : q.getClass().getDeclaredMethods()) {
                            QuestStartCondition questStartCondition = method.getAnnotation(QuestStartCondition.class);
                            if(questStartCondition == null) continue;
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
            for(Quest quest : validQuests) {

                Method[] methods = quest.getClass().getDeclaredMethods();

                for(Method method : methods) {

                    method.setAccessible(true);
                    StageCaller.callStageWhenEnterAreaRadius(player, quest, method);
                    StageCaller.callStageWhenEnterAreaRectangle(player, quest, method);
                    method.setAccessible(false);
                }
            }
        }
    }
}
