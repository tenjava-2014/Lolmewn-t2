package nl.lolmewn.tenjava.custom;

import org.bukkit.entity.Player;

/**
 *
 * @author Lolmewn
 */
public class ActivatableBroadcast implements Activatable{
    
    private final String message;

    public ActivatableBroadcast(String message) {
        this.message = message;
    }

    @Override
    public void activate(Player player) {
        player.getServer().broadcastMessage(message);
    }

}
