package nl.lolmewn.tenjava.spells;

/**
 *
 * @author Lolmewn
 */
public enum SpellType {

    WEATHER(new Weather()),
    FIREBALL(new Fireball());

    private final Spell spell;

    private SpellType(Spell spell) {
        this.spell = spell;
    }

    public Spell getSpell() {
        return spell;
    }

}
