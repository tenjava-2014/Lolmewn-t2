package nl.lolmewn.tenjava.spells;

import nl.lolmewn.tenjava.players.SpellsPlayer;

/**
 *
 * @author Lolmewn
 */
public abstract class Spell {

    /**
     * The name of this spell
     * @return name of the spell
     */
    public abstract String getName();

    /**
     * Amount of mana it costs to cast this spell. Mana
     * comes from XP in your XP bar.
     * @return manacost for this spell
     */
    public abstract int getManacost();

    public abstract boolean canCast(SpellsPlayer player);

    /**
     * The chance you have to learn this spell without messing it up
     *
     * @return chance in percent, from 1-100
     */
    public abstract int getLearnChance();

}
