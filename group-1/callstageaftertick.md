# @CallStageAfterTick
해당 progress에 도달하였을때 tick만큼 대기 후 실행함을 선언<br/>
progressToCall이 0이라면 모든 progress에서 실행

## Definition
```java
@Repeatable(CallStageAfterTickContainer.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface CallStageAfterTick {
    int[] progressToCall() default 0;
    int tick();
}
```

## Usage
```java
public class ExampleQuest extends StoryTellerQuest {
    public ExampleQuest(Player player) {
        super(player);
    }
    
    @CallStageAfterTick(progressToCall = {1, 2, 3, 4, 5}, tick = 60)
    @StartSequence(finalProgress = 5)
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
            case 5 -> {
                // 5
            }
        }
    }
}

```

