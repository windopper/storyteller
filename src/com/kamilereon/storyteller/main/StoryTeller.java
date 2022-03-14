package com.kamilereon.storyteller.main;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kamilereon.storyteller.core.CoreData;
import com.kamilereon.storyteller.core.Memory;
import com.kamilereon.storyteller.core.StoryTellerQuest;
import com.kamilereon.storyteller.configuration.QuestFilter;
import org.bukkit.entity.Player;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.util.HashMap;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class StoryTeller {
    @SafeVarargs
    public static void registerQuest(Class<? extends StoryTellerQuest> ...storyTellerQuests) {
        Memory.registerQuest(storyTellerQuests);
    }

    public static Set<Class<? extends StoryTellerQuest>> getQuestList() {
        return Memory.getQuests();
    }

    public static Set<? extends StoryTellerQuest> getAllQuestsFromPlayer(Player player) {
        return Memory.getCoreData(player).getQuests();
    }

    public static Set<? extends StoryTellerQuest> getQuestsFromPlayer(Player player, QuestFilter questFilter) {
        Set<? extends StoryTellerQuest> quests = Memory.getCoreData(player).getQuests();
        return quests.stream().filter(questFilter::test).collect(Collectors.toSet());
    }

    public static StoryTellerQuest getQuestFromPlayer(Player player, Class<? extends StoryTellerQuest> targetClass) {
        for(StoryTellerQuest q : Memory.getCoreData(player).getQuests()) {
            if(q.getClass() == targetClass) return q;
        }
        return null;
    }

    public static Optional<String> getQuestsByJson(Player player) {

        HashMap<String, StoryTellerQuest> questSet = new HashMap<>();
        Memory.getCoreData(player).getQuests().forEach(q -> questSet.put(q.getClass().getName(), q));

        ObjectMapper mapper = new ObjectMapper();
        Optional<String> result = Optional.empty();
        try {
            result = Optional.of(mapper.writeValueAsString(questSet));
            return result;
        }
        catch(JsonProcessingException e) {
            e.printStackTrace();
            return result;
        }
    }

    public static boolean uploadQuestData(Player player, String json) {

        Set<? extends StoryTellerQuest> quests = Memory.getCoreData(player).getQuests();

        try {
            JSONParser jsonParser = new JSONParser();
            Object obj = jsonParser.parse(json);
            JSONObject jsonObj = (JSONObject) obj;
            jsonObj.forEach((K, V) -> {
                if(K instanceof String key && V instanceof JSONObject value) {
                    try {
                        Class<?> clazz = Class.forName(key);
                        StoryTellerQuest stq = (StoryTellerQuest) clazz.getConstructor(Player.class).newInstance(player);
                        stq.stage = (int) value.get("stage");
                        stq.progress = (int) value.get("progress");
                        Memory.getCoreData(player).uploadQuest(stq);
                    }
                    catch(Exception e) {
                        e.printStackTrace();
                        System.out.println("cannot upload to "+player.getName()+"'s core data");
                    }
                }
            });
            return true;
        }
        catch(Exception e) {
            e.printStackTrace();
            System.out.println("cannot upload to "+player.getName()+"'s core data");
            return false;
        }
    }
}
