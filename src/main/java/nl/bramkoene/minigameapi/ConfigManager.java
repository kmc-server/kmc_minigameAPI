package nl.bramkoene.minigameapi;

import nl.bramkoene.minigameapi.GameCreation.GameConfigBean;
import nl.bramkoene.minigameapi.messages.GameMessages;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

@SuppressWarnings("ResultOfMethodCallIgnored")
public class ConfigManager {
    private final MiniGameAPI plugin;

    public ConfigManager(MiniGameAPI pl){
        this.plugin = pl;
        setup();
    }

    private GameMessages gameMessages;

    private FileConfiguration playerscfg;
    private File playersfile;

    private FileConfiguration collectorscfg;
    private File collectorsfile;

    private FileConfiguration langcfg;
    private File langfile;

    private void setup(){
        if(!plugin.getDataFolder().exists()){
            plugin.getDataFolder().mkdir();
            gameMessages = new GameMessages(this);
        }

        playersfile = new File(plugin.getDataFolder(), "players.yml");
        collectorsfile = new File(plugin.getDataFolder(), "collectors.yml");
        langfile = new File(plugin.getDataFolder(), "lang.yml");

        if(!playersfile.exists()){
            try{
                playersfile.createNewFile();
            }catch (IOException e){
                plugin.getLogger().warning(ChatColor.RED + "Could not create players config file");
            }
        }

        if(!collectorsfile.exists()){
            try{
                collectorsfile.createNewFile();
            }catch (IOException e){
                plugin.getLogger().warning(ChatColor.RED + "Could not create collectors config file");
            }
        }

        if(!langfile.exists()){
            try{
                langfile.createNewFile();
            }catch(IOException e){
                plugin.getLogger().warning(ChatColor.RED + "Could not create lang file");
            }
        }

        collectorscfg = YamlConfiguration.loadConfiguration(collectorsfile);
        playerscfg = YamlConfiguration.loadConfiguration(playersfile);
        langcfg = YamlConfiguration.loadConfiguration(langfile);
        plugin.getLogger().info(ChatColor.GREEN + "Config Created");
    }

    public FileConfiguration getPlayers(){
        return playerscfg;
    }

    public void savePlayers(){
        try{
            playerscfg.save(playersfile);
        }catch (IOException e){
            plugin.getLogger().warning(ChatColor.RED + "PlayersFile Could not be saved!!!");
        }
    }

    public void reloadPlayers(){
        playerscfg = YamlConfiguration.loadConfiguration(playersfile);
    }

    public FileConfiguration getCollectors(){
        return collectorscfg;
    }

    public void saveCollectors(){
        try{
            collectorscfg.save(collectorsfile);
        }catch (IOException e){
            plugin.getLogger().warning(ChatColor.RED + "CollectorsFile coud not be saved!!!");
        }
    }

    public void reloadCollectors(){
        collectorscfg = YamlConfiguration.loadConfiguration(collectorsfile);
    }

    public FileConfiguration getLang(){
        return langcfg;
    }

    public void saveLang(){
        try{
            langcfg.save(langfile);
        }catch (IOException e){
            plugin.getLogger().warning(ChatColor.RED + "LangFile Could not be saved!!!");
        }
    }

    public void reloadLang(){
        langcfg = YamlConfiguration.loadConfiguration(langfile);
    }

    public GameMessages getGameMessages() {
        return gameMessages;
    }

    public String saveGameBean(GameConfigBean game){
        String path = "Games." + game.getGameName() + "." + game.getUniqueName();
        getCollectors().set(path + ".kit", game.getKit());
        getCollectors().set(path + ".spawns", game.getGameLocations());
        getCollectors().set(path + ".lobby", game.getLobbyLocation());
        saveCollectors();
        return "Succesfully saved the config";
    }
}
