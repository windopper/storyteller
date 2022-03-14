package com.kamilereon.storyteller.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kamilereon.storyteller.core.CoreData;
import com.kamilereon.storyteller.core.StoryTellerQuest;
import org.bukkit.entity.Player;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.util.HashMap;
import java.util.Optional;
import java.util.Set;

public class StoryTellerUtils {

    public static boolean uploadQuestData(Player player, String json) {
        CoreData coreData = CoreData.getOrCreateCoreData(player);
        Set<? extends StoryTellerQuest> quests = coreData.getQuests();

        try {
            JSONParser jsonParser = new JSONParser();
            Object obj = jsonParser.parse(json);
            JSONObject jsonObj = (JSONObject) obj;
            return true;
        }
        catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static Optional<String> getQuestDatasByJson(Player player) {
        CoreData coreData = CoreData.getOrCreateCoreData(player);

        HashMap<String, StoryTellerQuest> questSet = new HashMap<>();
        coreData.getQuests().forEach(q -> questSet.put(q.getClass().getName(), q));

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
}
