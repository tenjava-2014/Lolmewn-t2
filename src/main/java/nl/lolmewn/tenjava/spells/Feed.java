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
public class Feed implements Spell {

    @Override
    public String getName() {
        return "Feed";
    }

    @Override
    public List<String> getLore() {
        return new ArrayList<String>(){{
            this.add(ChatColor.RED + "Looks like you need to eat...");
            this.add(ChatColor.GREEN + "... all you need is a feed!");
        }};
    }

    @Override
    public int getManacost() {
        return 5;
    }

    @Override
    public void cast(Main main, SpellsPlayer player) {
        Player p = main.getServer().getPlayer(player.getUuid());
        p.setFoodLevel(20);
    }

    @Override
    public int getLearnChance() {
        return 95;
    }

    @Override
    public List<LearnRequirement> getLearnRequirements() {
        return new ArrayList<LearnRequirement>(){{
            this.add(new LearnRequirement(RequirementType.SCROLL, 5));
        }};
    }

}
