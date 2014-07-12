package nl.lolmewn.tenjava.spells;

import java.util.List;
import nl.lolmewn.tenjava.Main;
import nl.lolmewn.tenjava.players.SpellsPlayer;
import nl.lolmewn.tenjava.spells.req.LearnRequirement;

/**
 *
 * @author Lolmewn
 */
public interface Spell {

    /**
     * The name of this spell
     *
     * @return name of the spell
     */
    public String getName();
    
    /**
     * The lore the book of this spell has
     * 
     * @return a list of strings making a description of this spell
     */
    public List<String> getLore();

    /**
     * Amount of mana it costs to cast this spell. Mana comes from XP in your XP
     * bar.
     *
     * @return manacost for this spell
     */
    public int getManacost();

    /**
     * Checks whether or not a player can cast this spell
     *
     * @param player SpellsPlayer to check for
     * @return true if the player can, otherwise false
     */
    public boolean canCast(SpellsPlayer player);
    
    /**
     * Casts this spell
     * 
     * @param main Main instance of this plugin
     * @param player Player that casts
     */
    public void cast(Main main, SpellsPlayer player);

    /**
     * The chance you have to learn this spell without messing it up
     *
     * @return chance in percent, from 1-100
     */
    public int getLearnChance();
    
    public List<LearnRequirement> getLearnRequirements();

}
