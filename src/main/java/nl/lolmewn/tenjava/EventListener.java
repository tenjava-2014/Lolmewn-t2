package nl.lolmewn.tenjava;

import nl.lolmewn.tenjava.players.SpellsPlayer;
import nl.lolmewn.tenjava.spells.Spell;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;

/**
 *
 * @author Lolmewn
 */
public class EventListener implements Listener {

    private final Main plugin;

    public EventListener(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void signClick(PlayerInteractEvent event) {
        if (!event.hasBlock()) {
            return;
        }
        Block clicked = event.getClickedBlock();
        if (!clicked.getType().equals(Material.WALL_SIGN) && !clicked.getType().equals(Material.SIGN_POST)) {
            return;
        }
        Sign sign = (Sign) clicked.getState();
        if (!sign.getLine(0).equalsIgnoreCase("[learn magic]")) {
            return;
        }
        event.getPlayer().openInventory(plugin.getPlayerManager().get(event.getPlayer().getUniqueId()).getSpellInventory().getInventory());
    }
    
    @EventHandler
    public void cast(PlayerInteractEvent event){
        if(!event.hasItem()){
            return;
        }
        ItemStack stack = event.getItem();
        if(!stack.getType().equals(Material.ENCHANTED_BOOK)){
            return;
        }
        Spell spell = this.findSpell(stack.getItemMeta().getDisplayName());
        if(spell == null){
            return;
        }
        SpellsPlayer sp = plugin.getPlayerManager().get(event.getPlayer().getUniqueId());
        if(!sp.knowsSpell(spell)){
            event.getPlayer().sendMessage(Messages.getMessage("cannot-cast-spell"));
            return;
        }
        if(event.getPlayer().getLevel() < spell.getManacost()){
            event.getPlayer().sendMessage(Messages.getMessage("not-enough-mana", new Pair("%req%", spell.getManacost() + "")));
            return;
        }
        spell.cast(plugin, sp);
    }

    @EventHandler
    public void login(PlayerJoinEvent event){
        plugin.getPlayerManager().loadPlayer(event.getPlayer().getUniqueId());
    }
    
    @EventHandler
    public void quit(PlayerQuitEvent event){
        plugin.getPlayerManager().savePlayer(event.getPlayer().getUniqueId());
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
