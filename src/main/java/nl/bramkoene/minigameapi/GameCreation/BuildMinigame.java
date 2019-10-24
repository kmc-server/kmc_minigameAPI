package nl.bramkoene.minigameapi.GameCreation;

import nl.bramkoene.minigameapi.GameConnector;
import org.apache.commons.lang3.RandomStringUtils;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Set;

public class BuildMinigame {
    public HashMap<Player, BuildMinigame> isBuildingGame = new HashMap<>();

    public HashMap<Player, GameConnector> gamesBeingBuild;
    String s = RandomStringUtils.randomAlphanumeric(8);
}
