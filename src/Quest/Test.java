package Quest;


import Annotation.*;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.lang.annotation.Repeatable;

public class Test extends Quest {

    public Test(Player player) {
        super(player);
    }

    @QuestStartCondition
    public boolean condition() {
        return true;
    }


    @StageSequence(stage = 1, finalProgress = 5)
    public void stage1() {
        switch(detailProgress) {
            case 1 -> {

            }
        }

        Bukkit.broadcastMessage("hi");
    }


    @CallStageWhenEnterAreaRadius(stageToCall = 3, radius = 3, worldName = "world", x = 1, y = 1, z = 1)
    @CallStageWhenEnterAreaRadius(stageToCall = 1, radius = 3, worldName = "world", x = 1, y = 1, z = 1)
    @StageSequence(stage = 2, finalProgress = 5)
    public void stage2() {

    }

    @Reward
    public void reward() {}

}
