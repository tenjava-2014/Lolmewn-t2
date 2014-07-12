package nl.lolmewn.tenjava.players;

import java.util.HashMap;
import java.util.UUID;

/**
 *
 * @author Lolmewn
 */
public class PlayerManager extends HashMap<UUID, SpellsPlayer>{
    
    public void loadPlayer(UUID uuid){
        //TODO
        this.put(uuid, new SpellsPlayer(uuid));
    }
    
    public void savePlayer(UUID uuid){
        //TODO
    }

}
