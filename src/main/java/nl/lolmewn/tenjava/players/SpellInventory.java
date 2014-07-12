package nl.lolmewn.tenjava.players;

import java.util.List;
import nl.lolmewn.tenjava.Main;
import nl.lolmewn.tenjava.spells.Spell;
import nl.lolmewn.tenjava.spells.SpellType;
import nl.lolmewn.tenjava.spells.req.LearnRequirement;
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
            lore.add("Learn cost: " + getCost(type.getSpell()));
            meta.setLore(lore);
            spellStack.setItemMeta(meta);
            inventory.addItem(spellStack);
        }
    }

    public Inventory getInventory() {
        return inventory;
    }

    private String getCost(Spell spell) {
        StringBuilder sb = new StringBuilder();
        if (spell.getLearnRequirements().isEmpty()) {
            sb.append("None!");
            return sb.toString();
        }
        boolean first = true;
        for (LearnRequirement req : spell.getLearnRequirements()) {
            switch (req.getType()) {
                case EXP:
                    if (!first) {
                        sb.append(", ");
                    }
                    sb.append((int) req.getValue()).append(" EXP");
                    first = false;
                    break;
                case ITEMSTACK:
                    if (!first) {
                        sb.append(", ");
                    }
                    ItemStack stack = (ItemStack) req.getValue();
                    sb.append(stack.getAmount()).append(" ").append(stack.getType().name().toLowerCase());
                    break;
                case SCROLL:
                    if (!first) {
                        sb.append(", ");
                    }
                    sb.append((int) req.getValue()).append(" scrolls");
            }
        }
        return sb.toString();
    }
}
