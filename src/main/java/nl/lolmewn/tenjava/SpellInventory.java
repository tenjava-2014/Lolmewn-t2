package nl.lolmewn.tenjava;

import nl.lolmewn.tenjava.spells.SpellType;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

/**
 *
 * @author Lolmewn
 */
public class SpellInventory {
    
    private Inventory inventory;
    
    public SpellInventory(Main plugin){
        for(SpellType type : SpellType.values()){
            ItemStack spellStack = new ItemStack(Material.ENCHANTED_BOOK);
            ItemMeta meta = spellStack.getItemMeta();
            meta.setDisplayName(type.getSpell().getName());
            
        }
    }

}
