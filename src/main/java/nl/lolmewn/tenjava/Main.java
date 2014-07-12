package nl.lolmewn.tenjava;

import java.util.ArrayList;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        ItemStack scrollItemStack = new ItemStack(Material.PAPER, 1);
        ItemMeta im = scrollItemStack.getItemMeta();
        im.setDisplayName("Magical scroll");
        im.setLore(new ArrayList<String>(){{
            this.add("Magical powers surge through this scroll...");
        }});
        scrollItemStack.setItemMeta(im);
        ShapelessRecipe scroll = new ShapelessRecipe(scrollItemStack);
        scroll.addIngredient(Material.PAPER);
        scroll.addIngredient(Material.NETHER_STAR);
        this.getServer().addRecipe(scroll);
        
    }

    @Override
    public void onDisable() {
        
    }
    
    
}
