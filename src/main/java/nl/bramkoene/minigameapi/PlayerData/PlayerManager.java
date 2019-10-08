package nl.bramkoene.minigameapi.PlayerData;

import org.bukkit.event.Listener;

import java.util.UUID;

public class PlayerManager implements Listener {

    private UUID uuid;
    private boolean inGame;
    private int coinsearned;
    private boolean isDead;

    public PlayerManager(UUID uuid, Boolean inGame, int coinsearned, boolean isDead){
        this.uuid = uuid;
        this.inGame = inGame;
        this.coinsearned = coinsearned;
        this.isDead = isDead;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public boolean isInGame() {
        return inGame;
    }

    public void setInGame(boolean inGame) {
        this.inGame = inGame;
    }

    public int getCoinsearned() {
        return coinsearned;
    }

    public void setCoinsearned(int coinsearned) {
        this.coinsearned = coinsearned;
    }

    public boolean isDead() {
        return isDead;
    }

    public void setDead(boolean dead) {
        isDead = dead;
    }

}
