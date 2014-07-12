package nl.lolmewn.tenjava.spells;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import nl.lolmewn.tenjava.Main;
import nl.lolmewn.tenjava.players.SpellsPlayer;
import nl.lolmewn.tenjava.spells.req.LearnRequirement;
import nl.lolmewn.tenjava.spells.req.RequirementType;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

/**
 *
 * @author Lolmewn
 */
public class MeteorRain implements Spell {

    @Override
    public String getName() {
        return "MeteorRain";
    }

    @Override
    public List<String> getLore() {
        return new ArrayList<String>() {
            {
                this.add(ChatColor.RED + "Bring down death and despair...");
                this.add(ChatColor.RED + "... let all hide in their lair!");
            }
        };
    }

    @Override
    public int getManacost() {
        return 50;
    }

    @Override
    public void cast(Main main, SpellsPlayer sPlayer) {
        int amount = main.getConfig().getInt("spell.meteorrain.amount", 20);
        Player player = main.getServer().getPlayer(sPlayer.getUuid());
        Random rant = new Random();
        while (amount > 0) {
            amount--;
            Location loc = player.getLocation();
            loc.setX(loc.getX() + rant.nextDouble() * 100 - 50);
            loc.setY(loc.getWorld().getMaxHeight());
            loc.setZ(loc.getZ() + rant.nextDouble() * 100 - 50);
            loc.setDirection(new Vector(rant.nextDouble() * 3 - 1.5, rant.nextDouble() * -5 - 5, rant.nextDouble() * 3 - 1.5));
            org.bukkit.entity.Fireball fire = player.launchProjectile(org.bukkit.entity.SmallFireball.class, loc.getDirection());
            fire.setIsIncendiary(true);
            fire.setYield(5);
            fire.teleport(loc);
        }
        player.setLevel(player.getLevel() - this.getManacost());
    }

    @Override
    public int getLearnChance() {
        return 5;
    }

    @Override
    public List<LearnRequirement> getLearnRequirements() {
        return new ArrayList<LearnRequirement>() {
            {
                this.add(new LearnRequirement(RequirementType.EXP, 10));
                this.add(new LearnRequirement(RequirementType.SCROLL, 50));
                this.add(new LearnRequirement(RequirementType.ITEMSTACK, new ItemStack(Material.GHAST_TEAR, 20)));
            }
        };
    }

}
