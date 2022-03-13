# storyteller 
#### simple library / plugin for implementing quest system

support mc version for plugin
- 1.17.1

-----
## Examples
```java
// you must extend StoryTellerQuest.class to use storyteller
public class ExampleQuest extends StoryTellerQuest {

    // Example variable
    final int levelReq = 5;

    public ExampleQuest(Player player) {
        super(player);
    }

    // The only players who passed condition can call stage
    @QuestProgressCondition
    public boolean condition() {
        return levelReq < player.getLevel();
    }

    // call method if player enter given area
    @CallStageWhenEnterAreaRadius(progressToCall = {1, 2, 3, 4, 5}, worldName = "world", x = 136, y = 74, z = 19, radius = 3)
    // notify finalProgress is 5 to update stage when detailProgress reaches 5
    @StartSequence(finalProgress = 5)
    public void preStage() {
        switch(detailProgress) {
            case 1 -> {
                //1
            }
            case 2 -> {
                //2
            }
            case 3 -> {
                // 3
            }
            case 4 -> {
                // 4
            }
            case 5 -> {
                // 5
            }
            // If detailProgress reaches to 5, stage will update
        }
    }

    @CallStageWhenEnterAreaRadius(worldName = "world", x = 136, y = 74, z = 19, radius = 3)
    @StageSequence(stage = 1, finalProgress = 10)
    public void stage1() {
        // separate detailProgress case by case
    }

    // call when all stage sequences are passed
    @FinalSequence
    public void finalSequence() {
        player.sendTitle("YOU RECEIVED A REWARD!", " ", 0, 40, 10);
        // Some Rewards to Player
    }

}

```

## Annotations
on construction

## Major Functions
on construction