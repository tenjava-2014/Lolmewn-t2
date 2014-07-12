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
public class DayTime implements Spell {

    @Override
    public String getName() {
        return "Daytime";
    }

    @Override
    public List<String> getLore() {
        return new ArrayList<String>(){{
            this.add(ChatColor.DARK_BLUE + "Away with the night...");
            this.add(ChatColor.YELLOW + "and into the light!");
        }};
    }

    @Override
    public int getManacost() {
        return 1;
    }

    @Override
    public void cast(Main main, SpellsPlayer sPlayer) {
        Player player = main.getServer().getPlayer(sPlayer.getUuid());
        player.getWorld().setTime(9000);
    }

    @Override
    public int getLearnChance() {
        return 40;
    }

    @Override
    public List<LearnRequirement> getLearnRequirements() {
        return new ArrayList<LearnRequirement>(){{
            this.add(new LearnRequirement(RequirementType.SCROLL, 25));
        }};
    }

}
