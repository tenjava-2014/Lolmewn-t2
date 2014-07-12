package nl.lolmewn.tenjava.api;

import nl.lolmewn.tenjava.Main;
import nl.lolmewn.tenjava.spells.Spell;

/**
 *
 * @author Lolmewn
 */
public class SpellsAPI {
    
    private final Main plugin;

    public SpellsAPI(Main main) {
        this.plugin = main;
    }
    
    public void registerSpell(Spell spell){
        plugin.getSpellManager().put(spell.getName(), spell);
    }

}
