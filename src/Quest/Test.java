package Quest;

import Annotation.Quest;
import Annotation.CallStageWhenRightClickEntity;
import Annotation.StageSequence;

@Quest(questName = "Test")
public class Test {

    @CallStageWhenRightClickEntity(entityName = "hi")
    @StageSequence(stage = 1)
    public void stage1() {

    }
}
