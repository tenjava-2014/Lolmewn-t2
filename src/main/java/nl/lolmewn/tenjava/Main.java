package nl.lolmewn.tenjava;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import nl.lolmewn.tenjava.players.PlayerManager;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    private PlayerManager playerManager;

    @Override
    public void onEnable() {
        this.checkFiles();
        this.playerManager = new PlayerManager(this);
        this.loadOnlinePlayers();
        this.createRecipe();
        new Messages(YamlConfiguration.loadConfiguration(new File(this.getDataFolder(), "messages.yml")));
        this.getServer().getPluginManager().registerEvents(new EventListener(this), this);
        this.getServer().getPluginManager().registerEvents(new SpellLearnListener(this), this);
    }

    @Override
    public void onDisable() {
        for(Player player : this.getServer().getOnlinePlayers()){
            this.playerManager.savePlayer(player);
        }
    }

    private void createRecipe() {
        ItemStack scrollItemStack = new ItemStack(Material.PAPER, 1);
        ItemMeta im = scrollItemStack.getItemMeta();
        im.setDisplayName("Magical scroll");
        im.setLore(new ArrayList<String>() {
            {
                this.add("Magical powers surge through this scroll...");
            }
        });
        scrollItemStack.setItemMeta(im);
        ShapelessRecipe scroll = new ShapelessRecipe(scrollItemStack);
        scroll.addIngredient(Material.PAPER);
        scroll.addIngredient(Material.NETHER_STAR);
        this.getServer().addRecipe(scroll);
    }

    public PlayerManager getPlayerManager() {
        return playerManager;
    }

    private void checkFiles() {
        this.saveDefaultConfig();
        File msg = new File(this.getDataFolder(), "messages.yml");
        if (!msg.exists()) {
            this.saveResource("messages.yml", false);
        }
        File user = new File(this.getDataFolder(), "users.yml");
        if (!user.exists()) {
            try {
                user.createNewFile();
            } catch (IOException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void loadOnlinePlayers() {
        for(Player player : this.getServer().getOnlinePlayers()){
            this.getPlayerManager().loadPlayer(player.getUniqueId());
        }
    }

}
