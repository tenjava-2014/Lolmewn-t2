package nl.lolmewn.tenjava;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

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
        event.getPlayer().openInventory(new SpellInventory(plugin, event.getPlayer()).getInventory());
    }

}
