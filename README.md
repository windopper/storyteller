---
description: Spigot library for implementing quest system
---

# StoryTeller

Spigot library for implementing quest system

```java
public class ExampleQuest extends StoryTellerQuest {

    private int levelReq = 5;

    public ExampleQuest(Player player) {
        super(player);
    }
    @QuestProgressCondition
    public boolean condition() {
        return levelReq <= player.getLevel();
    }
    
    @CallStageWhenEnterAreaRadius(progressToCall = {1, 2, 3}, worldName = "world", x = 136, y = 74, z = 19, radius = 3)
    @CallStageAfterTick(progressToCall = 4, tick = 60)
    @StartSequence(finalProgress = 4)
    public void preStage() {
        switch (progress) {
            case 1 -> {
                // 1
            }
            case 2 -> {
                // 2
            }
            case 3 -> {
                // 3
            }
            case 4 -> {
                // 4
            }
        }
    }
    
    @CallStageWhenConditionSatisfied(targetMethodName = "condition2")
    @StageSequence(stage = 1, finalProgress = 10)
    public void stage1() {
        // separate detailProgress to case by case
    }

    public boolean condition2() {
        return player.isSneaking();
    }

    // call when
    @FinalSequence
    public void finalSequence() {
        player.sendTitle("YOU RECEIVED A REWARD!", " ", 0, 40, 10);
        // Some Rewards to Player
    }
    
}
```

