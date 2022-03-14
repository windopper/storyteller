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
    // 실행 할 프로그레스 단계
    // 예를 들어 3을 요소로 선언하면 프로그레스 단계가 3이 되었을 때
    // tick 만큼 대기 후 타겟 메서드 실행
    // 0 이 선언 되었을 경우 모든 프로그레스 단계에 대하여 선언됨
    int tick();
    // 대기 할 시간  20 tick = 1 second
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

