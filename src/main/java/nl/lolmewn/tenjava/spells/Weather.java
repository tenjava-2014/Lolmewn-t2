package nl.lolmewn.tenjava.spells;

import nl.lolmewn.tenjava.players.SpellsPlayer;

/**
 *
 * @author Lolmewn
 */
public class Weather extends Spell {

    @Override
    public boolean canCast(SpellsPlayer player) {
        return player.knowsSpell(this);
    }

    @Override
    public int getLearnChance() {
        return 95;
    }

    @Override
    public String getName() {
        return "Weather";
    }

    @Override
    public int getManacost() {
        return 20;
    }

}
