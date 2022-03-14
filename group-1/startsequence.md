# @StartSequence
첫 스테이지임을 선언

## Definition
```java

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface StartSequence {
    int finalProgress();
}

```

## Usage
```java
public class ExampleQuest extends StoryTellerQuest {
    
    public ExampleQuest(Player player) {
        super(player);
    }
    
}
```

