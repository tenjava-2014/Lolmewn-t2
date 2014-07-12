package nl.lolmewn.tenjava.custom;

import org.bukkit.entity.Player;

/**
 *
 * @author Lolmewn
 */
public class ActivatableConsoleCommand implements Activatable {
    
    private final String command;
    
    public ActivatableConsoleCommand(String command){
        this.command = command;
    }

    @Override
    public void activate(Player player) {
        player.performCommand(command);
    }

}
