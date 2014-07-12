package nl.lolmewn.tenjava.spells;

import java.util.ArrayList;
import java.util.List;
import nl.lolmewn.tenjava.Main;
import nl.lolmewn.tenjava.players.SpellsPlayer;
import nl.lolmewn.tenjava.spells.req.LearnRequirement;
import nl.lolmewn.tenjava.spells.req.RequirementType;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

/**
 *
 * @author Lolmewn
 */
public class Fireball implements Spell {

    @Override
    public String getName() {
        return "Fireball";
    }

    @Override
    public List<String> getLore() {
        return new ArrayList<String>() {
            {
                this.add(ChatColor.YELLOW + "Set things " + ChatColor.RED + "ON FIRE!");
            }
        };
    }

    @Override
    public int getManacost() {
        return 3;
    }

    @Override
    public void cast(Main main, SpellsPlayer sPlayer) {
        Player player = main.getServer().getPlayer(sPlayer.getUuid());
        Location loc = player.getLocation();
        org.bukkit.entity.Fireball ball = player.launchProjectile(org.bukkit.entity.SmallFireball.class, loc.getDirection().multiply(3));
        ball.setIsIncendiary(true);
        player.setLevel(player.getLevel() - this.getManacost());
        sPlayer.cooldown(this);
    }

    @Override
    public int getLearnChance() {
        return 60;
    }

    @Override
    public List<LearnRequirement> getLearnRequirements() {
        return new ArrayList<LearnRequirement>() {
            {
                this.add(new LearnRequirement(RequirementType.SCROLL, 10));
                this.add(new LearnRequirement(RequirementType.ITEMSTACK, new ItemStack(Material.FIREBALL, 3)));
                this.add(new LearnRequirement(RequirementType.EXP, 8));
            }
        };
    }

    @Override
    public int getCooldown() {
        return 5;
    }

}
