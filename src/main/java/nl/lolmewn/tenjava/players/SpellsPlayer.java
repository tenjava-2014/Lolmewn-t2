package nl.lolmewn.tenjava.players;

import java.util.HashSet;
import java.util.UUID;
import nl.lolmewn.tenjava.Main;
import nl.lolmewn.tenjava.SpellInventory;
import nl.lolmewn.tenjava.spells.Spell;

/**
 *
 * @author Lolmewn
 */
public class SpellsPlayer {
    
    private final UUID uuid;
    private final HashSet<Spell> learnt = new HashSet<>();
    private final SpellInventory spellInventory;
    
    public SpellsPlayer(Main plugin, UUID uuid) {
        this.uuid = uuid;
        this.spellInventory = new SpellInventory(plugin, plugin.getServer().getPlayer(uuid));
    }
    
    public void learnSpell(Spell spell){
        this.learnt.add(spell);
    }
    
    public boolean knowsSpell(Spell spell){
        return this.learnt.contains(spell);
    }

    public UUID getUuid() {
        return uuid;
    }

    public SpellInventory getSpellInventory() {
        return spellInventory;
    }

}
