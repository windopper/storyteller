package com.kamilereon.storyteller.utils;

import com.kamilereon.storyteller.core.CoreData;
import com.kamilereon.storyteller.quest.StoryTellerQuest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.bukkit.entity.Player;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.util.Set;
import java.util.stream.Collectors;

public class StoryTellerUtils {

    public static Set<? extends StoryTellerQuest> getPlayerQuestDatas(Player player) {
        return CoreData.getOrCreateCoreData(player).getQuests();
    }

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

    public static String getQuestDatasByJson(Player player) {
        CoreData coreData = CoreData.getOrCreateCoreData(player);
        Set<StoryTellerQuest> quests = coreData.getQuests()
                .stream()
                .map(q -> (StoryTellerQuest) q)
                .collect(Collectors.toSet());

        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(quests);
        }
        catch(JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }
}
