package com.kamilereon.storyteller.quest;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.bukkit.entity.Player;

import java.io.Serializable;

public abstract class StoryTellerQuest implements Serializable {

    /**
        stage 가 -1이면 퀘스트 완료
        stage 가 0이면 퀘스트 시작 전
        stage 가 1이면 퀘스트 시작

        detailProgress 가 finalSequence 에 도달하면 stage 업그레이드


     */

    public int stage = 0;
    public int detailProgress = 1;

    @JsonIgnore
    transient public final Player player;

    public StoryTellerQuest(Player player) {
        this.player = player;
    }

    @JsonIgnore
    public boolean isFinished() { return stage == -1; }

    @JsonIgnore
    public boolean isProgressing() { return stage >= 1; }

    @JsonIgnore
    public boolean isNotStarted() { return stage == 0; }
}
