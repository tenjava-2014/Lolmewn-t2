package nl.lolmewn.tenjava.api;

import java.util.UUID;
import nl.lolmewn.tenjava.Main;
import nl.lolmewn.tenjava.players.SpellsPlayer;
import nl.lolmewn.tenjava.spells.Spell;
import org.bukkit.entity.Player;

/**
 *
 * @author Lolmewn
 */
public class SpellsAPI {

    private final Main plugin;

    public SpellsAPI(Main main) {
        this.plugin = main;
    }

    /**
     * Register a new spell
     * @param spell Spell to register
     */
    public void registerSpell(Spell spell) {
        plugin.getSpellManager().put(spell.getName(), spell);
    }

    /**
     * Find a spell with the given name
     * @param name Name of the spell to look for
     * @return The spell associated with the given name, null if no such spell exists
     */
    public Spell getSpell(String name) {
        for (Spell spell : this.plugin.getSpellManager().values()) {
            if (spell.getName().equals(name)) {
                return spell;
            }
        }
        return null;
    }
    
    /**
     * Get the SpellsPlayer associated with the given Player
     * @param player Player object to find the SpellsPlayer object for
     * @return SpellsPlayer object
     */
    public SpellsPlayer getSpellsPlayer(Player player){
        return plugin.getPlayerManager().get(player.getUniqueId());
    }
    
    /**
     * Get the SpellsPlayer associated with the given UUID
     * @param uuid UUID to find the SpellsPlayer object for
     * @return SpellsPlayer object
     */
    public SpellsPlayer getSpellsPlayer(UUID uuid){
        return plugin.getPlayerManager().get(uuid);
    }

}
