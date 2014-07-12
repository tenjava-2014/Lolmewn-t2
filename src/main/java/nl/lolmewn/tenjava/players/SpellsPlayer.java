package nl.lolmewn.tenjava.players;

import java.util.HashSet;
import java.util.UUID;
import nl.lolmewn.tenjava.spells.Spell;

/**
 *
 * @author Lolmewn
 */
public class SpellsPlayer {
    
    private final UUID uuid;
    private final HashSet<Spell> learnt = new HashSet<>();
    
    public SpellsPlayer(UUID uuid) {
        this.uuid = uuid;
    }
    
    public void learnSpell(Spell spell){
        this.learnt.add(spell);
    }
    
    public boolean knowsSpell(Spell spell){
        return this.learnt.contains(spell);
    }

}
