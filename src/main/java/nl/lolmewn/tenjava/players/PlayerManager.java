package nl.lolmewn.tenjava.players;

import java.util.HashMap;
import java.util.UUID;
import nl.lolmewn.tenjava.Main;

/**
 *
 * @author Lolmewn
 */
public class PlayerManager extends HashMap<UUID, SpellsPlayer>{
    
    private final Main plugin;

    public PlayerManager(Main plugin) {
        this.plugin = plugin;
    }
    
    public void loadPlayer(UUID uuid){
        
        this.put(uuid, new SpellsPlayer(plugin, uuid));
    }
    
    public void savePlayer(UUID uuid){
    }

}
