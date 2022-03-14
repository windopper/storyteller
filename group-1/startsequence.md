# @StartSequence
해당 메서드가 첫 스테이지임을 선언<br>
플레이어가 퀘스트 조건에 모두 만족하였을 때 타겟 메서드가 실행됨

## Definition
```java
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface StartSequence {
    int finalProgress(); 
    // 해당 스테이지의 마지막 프로그레스 값
    // 프로그레스 값은 매 스테이지마다 1부터 시작
}
```

## Usage

```java
import com.kamilereon.storyteller.annotations.StageSequence;
import com.kamilereon.storyteller.annotations.StartSequence;
import com.kamilereon.storyteller.core.StoryTellerQuest;

public class ExampleQuest extends StoryTellerQuest {

    public ExampleQuest(Player player) {
        super(player);
    }

    @StartSequence(finalProgress = 3)
    // 해당 스테이지의 프로그레스가 3단계로 이루어져 있음을 선언
    // @StartSequence 는 다음과 같은 어노테이션으로도 선언 가능
    // @StageSequence(stage = 0, finalProgress = 3)
    public void preStage() {
        switch (progress) {
            case 1 -> {

            }
            case 2 -> {

            }
            case 3 -> {

            }
        }
    }
}
```

