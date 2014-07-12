package nl.lolmewn.tenjava.players;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import nl.lolmewn.tenjava.Main;
import nl.lolmewn.tenjava.spells.Spell;
import org.bukkit.configuration.file.YamlConfiguration;

/**
 *
 * @author Lolmewn
 */
public class PlayerManager extends HashMap<UUID, SpellsPlayer>{
    
    private final Main plugin;
    private final YamlConfiguration config;
    
    public PlayerManager(Main plugin) {
        this.plugin = plugin;
        this.config = YamlConfiguration.loadConfiguration(new File(plugin.getDataFolder(), "users.yml"));
    }
    
    public void loadPlayer(UUID uuid){
        this.put(uuid, new SpellsPlayer(plugin, uuid));
        if(config.contains(uuid.toString())){
            for(String itemName : config.getStringList(uuid.toString() + ".unlocked")){
                Spell spell = this.findSpell(itemName);
                if(spell == null){
                    continue;
                }
                this.get(uuid).learnSpell(spell);
            }
        }
    }
    
    public void savePlayer(UUID uuid){
        SpellsPlayer sp = this.get(uuid);
        List<String> known = new ArrayList<>();
        for(Spell spell : sp.getKnownSpells()){
            known.add(spell.getName());
        }
        config.set(uuid.toString() + ".unlocked", known);
        try {
            config.save(new File(plugin.getDataFolder(), "users.yml"));
        } catch (IOException ex) {
            Logger.getLogger(PlayerManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Spell findSpell(String itemName) {
        for (Spell spell : this.plugin.getSpellManager().values()) {
            if (spell.getName().equals(itemName)) {
                return spell;
            }
        }
        return null;
    }

}
