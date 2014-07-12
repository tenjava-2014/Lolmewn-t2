package nl.lolmewn.tenjava;

import java.util.List;
import nl.lolmewn.tenjava.spells.SpellType;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

/**
 *
 * @author Lolmewn
 */
public class SpellInventory {

    private final Inventory inventory;

    public SpellInventory(Main plugin, Player player) {
        inventory = plugin.getServer().createInventory(player, 27, ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("inventory.name", "Spell forge")));
        for (SpellType type : SpellType.values()) {
            ItemStack spellStack = new ItemStack(Material.ENCHANTED_BOOK);
            ItemMeta meta = spellStack.getItemMeta();
            meta.setDisplayName(type.getSpell().getName());
            List<String> lore = type.getSpell().getLore();
            lore.add("Learn chance: " + type.getSpell().getLearnChance());
            meta.setLore(lore);
            spellStack.setItemMeta(meta);
            inventory.all(spellStack);
        }
    }

    public Inventory getInventory() {
        return inventory;
    }

}
