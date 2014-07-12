package nl.lolmewn.tenjava.spells;

import java.util.ArrayList;
import java.util.List;
import nl.lolmewn.tenjava.Main;
import nl.lolmewn.tenjava.players.SpellsPlayer;
import nl.lolmewn.tenjava.spells.req.LearnRequirement;
import nl.lolmewn.tenjava.spells.req.RequirementType;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

/**
 *
 * @author Lolmewn
 */
public class Heal implements Spell {

    @Override
    public String getName() {
        return "Heal";
    }

    @Override
    public List<String> getLore() {
        return new ArrayList<String>() {
            {
                this.add(ChatColor.RED + "To stop all the bleed...");
                this.add(ChatColor.GREEN + "... a heal is all you need!");
            }
        };
    }

    @Override
    public int getManacost() {
        return 5;
    }

    @Override
    public void cast(Main main, SpellsPlayer player) {
        Player p = main.getServer().getPlayer(player.getUuid());
        p.setHealth(p.getMaxHealth());
        p.setLevel(p.getLevel() - this.getManacost());
        player.cooldown(this);
    }

    @Override
    public int getLearnChance() {
        return 95;
    }

    @Override
    public List<LearnRequirement> getLearnRequirements() {
        return new ArrayList<LearnRequirement>() {
            {
                this.add(new LearnRequirement(RequirementType.SCROLL, 5));
            }
        };
    }

    @Override
    public int getCooldown() {
        return 60;
    }

}
