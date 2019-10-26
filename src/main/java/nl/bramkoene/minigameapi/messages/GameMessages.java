package nl.bramkoene.minigameapi.messages;

import nl.bramkoene.minigameapi.ConfigManager;

public class GameMessages {
    private final ConfigManager configManager;

    public GameMessages(ConfigManager configManager){
        this.configManager = configManager;
        configManager.getLang().options().copyDefaults(true);
    }

    public String getLobbyLocationMessage(){
        String message = configManager.getLang().getString("buildmessages.setLobbyLocation");
        configManager.getLang().set("buildmessages.setLobbyLocation", message);
        configManager.saveLang();
        return message;
    }

    public String getGameLocationMessage(){
        String message = configManager.getLang().getString("buildmessages.setGameLocation");
        configManager.getLang().set("buildmessages.setGameLocation", message);
        configManager.saveLang();
        return message;
    }

    public String getGameUniqueNameMessage(){
        String message = configManager.getLang().getString("buildmessages.setGameName");
        configManager.getLang().set("buildmessages.setGameName", message);
        configManager.saveLang();
        return message;
    }
}
