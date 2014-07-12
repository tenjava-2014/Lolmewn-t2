package nl.lolmewn.tenjava.spells;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import nl.lolmewn.tenjava.Main;
import nl.lolmewn.tenjava.custom.Activatable;
import nl.lolmewn.tenjava.custom.ActivatableBroadcast;
import nl.lolmewn.tenjava.custom.ActivatableCommand;
import nl.lolmewn.tenjava.custom.ActivatableConsoleCommand;
import nl.lolmewn.tenjava.players.SpellsPlayer;
import nl.lolmewn.tenjava.spells.req.LearnRequirement;
import nl.lolmewn.tenjava.spells.req.RequirementType;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

/**
 *
 * @author Lolmewn
 */
public class SpellManager extends HashMap<String, Spell> {

    public void loadCustomSpells(FileConfiguration config) {
        if (config.contains("custom")) {
            for (String key : config.getConfigurationSection("custom").getKeys(false)) {
                this.loadCustomSpell(key, config.getConfigurationSection("custom." + key));
            }
        }
    }

    public void loadCustomSpell(final String name, ConfigurationSection sec) {
        if (!sec.contains("description") || !sec.contains("manacost") || !sec.contains("forge-req") || !sec.contains("activate") || !sec.contains("learnchance")) {
            Bukkit.getLogger().warning("[Spells] Wrongly configured the " + name + " spell, it doesn't contain either a description, manacost, forge-req, learnchance or activate.");
            return;
        }

        final List<String> description = new ArrayList<>();
        for (String msg : sec.getStringList("description")) {
            description.add(ChatColor.translateAlternateColorCodes('&', msg));
        }
        final int manacost = sec.getInt("manacost");
        final int learnchance = sec.getInt("learnchance");
        final List<LearnRequirement> reqs = new ArrayList<>();
        for (String req : sec.getStringList("forge-req")) {
            String[] sp = req.split(" ");
            RequirementType type = RequirementType.valueOf(sp[0]);
            if (type == null) {
                Bukkit.getLogger().warning("[Spells] Unknown requirement: " + req);
                return;
            }
            switch (type) {
                case EXP:
                case SCROLL:
                    if (sp.length < 2) {
                        Bukkit.getLogger().warning("[Spells] Requirement needs int: " + req + " for spell " + name);
                        return;
                    }
                    reqs.add(new LearnRequirement(type, Integer.parseInt(req.split(" ")[1])));
                    break;
                case ITEMSTACK:
                    if (sp.length < 3) {
                        Bukkit.getLogger().warning("[Spells] Requirement needs Material and int: " + req + " for spell " + name);
                        return;
                    }
                    reqs.add(new LearnRequirement(type, new ItemStack(Material.valueOf(sp[1]), Integer.parseInt(sp[2]))));
            }
        }

        final List<Activatable> actives = new ArrayList<>();
        for (String type : sec.getConfigurationSection("activate").getKeys(false)) {
            switch (type) {
                case "commands":
                    for (String command : sec.getStringList("activate.commands")) {
                        actives.add(new ActivatableCommand(command));
                    }
                    break;
                case "broadcast":
                    for (String message : sec.getStringList("activate.broadcast")) {
                        actives.add(new ActivatableBroadcast(message));
                    }
                    break;
                case "console":
                    for (String command : sec.getStringList("activate.console")) {
                        actives.add(new ActivatableConsoleCommand(command));
                    }
            }
        }

        this.put(name, new Spell() {

            @Override
            public String getName() {
                return name;
            }

            @Override
            public List<String> getLore() {
                return description;
            }

            @Override
            public int getManacost() {
                return manacost;
            }

            @Override
            public void cast(Main main, SpellsPlayer sPlayer) {
                Player player = main.getServer().getPlayer(sPlayer.getUuid());
                for (Activatable active : actives) {
                    active.activate(player);
                }
            }

            @Override
            public int getLearnChance() {
                return learnchance;
            }

            @Override
            public List<LearnRequirement> getLearnRequirements() {
                return reqs;
            }
        });
    }

}
