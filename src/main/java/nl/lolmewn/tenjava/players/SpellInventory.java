package nl.lolmewn.tenjava.players;

import java.util.List;
import nl.lolmewn.tenjava.Main;
import nl.lolmewn.tenjava.spells.Spell;
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
        for (Spell spell : plugin.getSpellManager().values()) {
            ItemStack spellStack = new ItemStack(Material.ENCHANTED_BOOK);
            ItemMeta meta = spellStack.getItemMeta();
            meta.setDisplayName(spell.getName());
            List<String> lore = spell.getLore();
            lore.add(ChatColor.YELLOW + "Cast cost: " + ChatColor.BLUE + spell.getManacost() + ChatColor.YELLOW + " mana");
            lore.add(ChatColor.YELLOW + "Learn chance: " + this.getChanceColor(spell.getLearnChance()) + spell.getLearnChance());
            lore.add(ChatColor.YELLOW + "Learn cost: " + getCost(spell));
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
                    first = false;
                    break;
                case SCROLL:
                    if (!first) {
                        sb.append(", ");
                    }
                    sb.append((int) req.getValue()).append(" scrolls");
                    first = false;
            }
        }
        return sb.toString();
    }
    
    private ChatColor getChanceColor(int chance){
        if(chance <= 10){
            return ChatColor.DARK_RED;
        }
        if(chance <= 25){
            return ChatColor.RED;
        }
        if(chance <= 50){
            return ChatColor.YELLOW;
        }
        if(chance <= 75){
            return ChatColor.GREEN;
        }
        if(chance <= 100){
            return ChatColor.DARK_GREEN;
        }
        return ChatColor.BLACK;
    }
}
