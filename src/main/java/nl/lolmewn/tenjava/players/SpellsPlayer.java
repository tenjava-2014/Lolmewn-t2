package nl.lolmewn.tenjava.players;

import java.util.HashMap;
import java.util.HashSet;
import java.util.UUID;
import nl.lolmewn.tenjava.Main;
import nl.lolmewn.tenjava.spells.Spell;

/**
 *
 * @author Lolmewn
 */
public class SpellsPlayer {
    
    private final UUID uuid;
    private final HashSet<Spell> learnt = new HashSet<>();
    private final HashMap<Spell, Long> cooldown = new HashMap<>();
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
    
    public boolean canCast(Spell spell){
        return knowsSpell(spell) && !this.isCoolingDown(spell);
    }

    public UUID getUuid() {
        return uuid;
    }

    public SpellInventory getSpellInventory() {
        return spellInventory;
    }

    public HashSet<Spell> getKnownSpells() {
        return this.learnt;
    }
    
    public int getSecondsRemaining(Spell spell){
        if(!cooldown.containsKey(spell)){
            return 0;
        }
        return (int)((cooldown.get(spell) + spell.getCooldown() * 1000 - System.currentTimeMillis()) / 1000) + 1;
    }
    
    public boolean isCoolingDown(Spell spell){
        if(!cooldown.containsKey(spell)){
            return false;
        }
        return cooldown.get(spell) + spell.getCooldown() * 1000 - System.currentTimeMillis() >= 0;
    }
    
    public void cooldown(Spell spell){
        this.cooldown.put(spell, System.currentTimeMillis());
    }

}
