# @FinalSequence
더 이상 진행할 스테이지가 없을 때 실행됨을 선언

## Definition
```java
@StoryTellerAnnotation
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface FinalSequence {

}
```

## Usage

```java
public class ExampleQuest extends StoryTellerQuest {
    public ExampleQuest(Player player) {
        super(player);
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
        // progress 에 따라 이벤트 발동
    }

    public boolean condition2() {
        return player.isSneaking();
    }
    
    @FinalSequence
    // 더 이상 진행할 스테이지가 없을 때 실행
    public void finalSequence() {
        player.sendTitle("YOU RECEIVED A REWARD!", " ", 0, 40, 10);
        // 플레이어가 모든 스테이지를 완료하였을때
    }
}
```

