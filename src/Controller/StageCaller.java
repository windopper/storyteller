package Controller;

import Annotation.CallStageWhenEnterAreaRadius;
import Annotation.CallStageWhenEnterAreaRectangle;
import Annotation.StageSequence;
import Quest.Quest;
import Utils.LocationUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import java.lang.reflect.Method;

public class StageCaller {
    public static void callStageWhenEnterAreaRadius(Player player, Quest quest, Method method) {

        int progress = quest.progress;
        int detailProgress = quest.detailProgress;

        StageSequence stageSequence = method.getAnnotation(StageSequence.class);
        if(stageSequence == null) return;

        CallStageWhenEnterAreaRadius[] l_cs = method.getAnnotationsByType(CallStageWhenEnterAreaRadius.class);

        for(CallStageWhenEnterAreaRadius l_c : l_cs) {

            int[] targetDetailProgress = l_c.stageToCall();
            String world = l_c.worldName();
            double radius = l_c.radius();
            double x = l_c.x();
            double y = l_c.y();
            double z = l_c.z();
            Location location = new Location(Bukkit.getWorld(world), x, y, z);

            for(int i : targetDetailProgress) {
                if(detailProgress + 1 != i) continue;
                if(LocationUtils.isPlayerInArea(player, location, radius)) {
                    try {
                        method.invoke(quest);
                        if(stageSequence.finalProgress() == detailProgress) {
                            quest.detailProgress = 0;
                            quest.progress++;
                        }
                        else quest.detailProgress ++;
                        return;
                    }
                    catch(Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public static void callStageWhenEnterAreaRectangle(Player player, Quest quest, Method method) {

        int progress = quest.progress;
        int detailProgress = quest.detailProgress;

        StageSequence stageSequence = method.getAnnotation(StageSequence.class);
        if(stageSequence == null) return;

        CallStageWhenEnterAreaRectangle[] l_cs = method.getAnnotationsByType(CallStageWhenEnterAreaRectangle.class);

        for(CallStageWhenEnterAreaRectangle l_c : l_cs) {

            int[] targetDetailProgress = l_c.stageToCall();
            String world = l_c.worldName();
            double x = l_c.x();
            double y = l_c.y();
            double z = l_c.z();
            double dx = l_c.dx();
            double dy = l_c.dy();
            double dz = l_c.dz();

            Location location = new Location(Bukkit.getWorld(world), x, y, z);
            Vector vector = new Vector(dx, dy, dz);

            for(int i : targetDetailProgress) {
                if(detailProgress + 1 != i) continue;
                if(LocationUtils.isPlayerInArea(player, location, vector)) {
                    try {
                        method.invoke(quest);
                        if(stageSequence.finalProgress() == detailProgress) {
                            quest.detailProgress = 0;
                            quest.progress++;
                        }
                        else quest.detailProgress ++;
                        return;
                    }
                    catch(Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
