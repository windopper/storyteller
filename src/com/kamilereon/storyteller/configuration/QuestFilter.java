package com.kamilereon.storyteller.configuration;

import com.kamilereon.storyteller.core.StoryTellerQuest;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class QuestFilter {

    private final Set<Predicate<StoryTellerQuest>> filters = new HashSet<>();
    private FilterMode filterMode = FilterMode.OR;

    public QuestFilter() {

    }

    public QuestFilter setFilters(Filters...filters) {
        this.filters.addAll(Arrays.stream(filters).map(f -> f.predicate).collect(Collectors.toSet()));
        return this;
    }

    public QuestFilter setFilters(Predicate<StoryTellerQuest>... filters) {
        this.filters.addAll(Arrays.stream(filters).collect(Collectors.toSet()));
        return this;
    }

    public QuestFilter setMode(FilterMode mode) {
        filterMode = mode;
        return this;
    }

    public boolean test(StoryTellerQuest storyTellerQuest) {
        for(Predicate<StoryTellerQuest> f : filters) {
            boolean value = f.test(storyTellerQuest);
            if(value && filterMode == FilterMode.OR) { return true; }
            else if(!value && filterMode == FilterMode.AND) return false;
        }
        return filterMode == FilterMode.AND;
    }
}
