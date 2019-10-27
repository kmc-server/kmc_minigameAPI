package nl.bramkoene.minigameapi;

import nl.bramkoene.minigameapi.GameCreation.GameConfigBean;
import nl.bramkoene.minigameapi.messages.GameMessages;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.io.IOException;
import java.util.List;

@SuppressWarnings({"ResultOfMethodCallIgnored", "unchecked"})
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

    /**
     * Saves the given bean to the config files.
     * @param game a {@link GameConfigBean} that needs to be saved after creation
     * @return a succes message. This is not something important
     */
    public String saveGameBean(GameConfigBean game){
        String path = path(game.getGameName(), game.getUniqueName());
        getCollectors().set(path + ".kit", game.getKit());
        getCollectors().set(path + ".spawns", game.getGameLocations());
        getCollectors().set(path + ".lobby", game.getLobbyLocation());
        getCollectors().set(path + ".sign", game.getSignLocation());
        getCollectors().set(path + ".minPlayers", game.getMinPlayers());
        getCollectors().set(path + ".teamPlayers", game.getTeamPlayers());
        getCollectors().set(path + ".respawn", game.isAllowRespawn());
        saveCollectors();
        return "Succesfully saved the config";
    }

    /**
     * Returns a path to the gamebean config
     * @param gameName the game name registered from other addons to the api
     * @param uniqueName the unique name also known as the arena
     * @return the path to the game bean
     */
    private String path(String gameName, String uniqueName){
        return "Games." + gameName + "." + uniqueName;
    }

    /**
     * Takes a gameName and a uniqueName for the config and then returns a gamebean for that specific game
     * @param gameName the game name registered from other addons to the api
     * @param uniqueName the unique name also known as the arena
     * @return a {@link GameConfigBean} that contains all config options
     * @throws Exception Throws exception if something is missing in the config file
     */
    public GameConfigBean getGameConfigbean(String gameName, String uniqueName) throws Exception{
        if(!getCollectors().contains(path(gameName, uniqueName))){
            throw new Exception("This game does not seem to exists");
        }

        GameConfigBean game = new GameConfigBean();
        // First we set the kit. This is a very diffelcult process if no understand message @minestarnl#6969
        List<ItemStack> kit = (List<ItemStack>) getCollectors().getList(path(gameName, uniqueName) + ".kit");
        if (kit == null) throw new Exception("Kit is null please contact an IT guy");
        ItemStack[] items = (ItemStack[]) kit.toArray();
        game.setKit(items);

        // Then we get the game locations
        List<Location> locs = (List<Location>) getCollectors().getList(path(gameName, uniqueName) + ".spawns");
        game.setGameLocations(locs);

        // Now we get the other 'easy' things
        game.setGameName(gameName);
        game.setUniqueName(uniqueName);
        game.setLobbyLocation(getCollectors().getLocation(path(gameName, uniqueName) + ".lobby"));
        game.setTeamPlayers(getCollectors().getInt(path(gameName, uniqueName) + ".teamPlayers"));
        game.setMinPlayers(getCollectors().getInt(path(gameName, uniqueName) + ".minPlayers"));
        game.setAllowRespawn(getCollectors().getBoolean(path(gameName, uniqueName) + ".respawn"));
        game.setSignLocation(getCollectors().getLocation(path(gameName, uniqueName) + ".sign"));

        return game;
    }
}
