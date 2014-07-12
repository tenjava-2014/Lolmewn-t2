package nl.lolmewn.tenjava;

import java.util.Random;
import nl.lolmewn.tenjava.players.SpellsPlayer;
import nl.lolmewn.tenjava.spells.Spell;
import nl.lolmewn.tenjava.spells.req.LearnRequirement;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

/**
 *
 * @author Lolmewn
 */
public class SpellLearnListener implements Listener {

    private final Main plugin;

    public SpellLearnListener(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void pickup(InventoryClickEvent event) {
        if (!event.getInventory().getName().equals(plugin.getConfig().getString("inventory.name"))) {
            return;
        }
        if (!(event.getWhoClicked() instanceof Player)) {
            return;
        }
        Player player = (Player) event.getWhoClicked();
        SpellsPlayer sp = plugin.getPlayerManager().get(event.getWhoClicked().getUniqueId());
        ItemStack stack = event.getCurrentItem();
        if (stack == null || !stack.hasItemMeta() || !stack.getItemMeta().hasDisplayName()) {
            return;
        }
        Spell spell = this.findSpell(stack.getItemMeta().getDisplayName());
        if (spell == null) {
            plugin.getLogger().info("Spell not found, but is in inventory: " + stack.getItemMeta().getDisplayName());
            return;
        }
        if (sp.knowsSpell(spell)) {
            event.setCancelled(true);
            if (!player.getInventory().contains(stack)) {
                player.getInventory().addItem(stack);
            } else {
                player.sendMessage(Messages.getMessage("already-know-spell"));
            }
            return;
        }
        if (!hasRequirements(player, spell)) {
            player.sendMessage(Messages.getMessage("not-meets-requirements"));
            event.setCancelled(true);
            player.closeInventory();
            return;
        }
        takeRequirements(player, spell);
        if (!learnSpell(sp, spell)) {
            player.damage(1);
            player.sendMessage(Messages.getMessage("spell-learning-failed"));
            event.setCancelled(true);
            player.closeInventory();
        } else {
            player.sendMessage(Messages.getMessage("spell-learnt", new Pair("%spell%", spell.getName())));
            event.setCancelled(true);
            player.closeInventory();
            player.getInventory().addItem(stack);
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

    public boolean learnSpell(SpellsPlayer player, Spell spell) {
        Random rant = new Random();
        if (rant.nextDouble() * 100 < spell.getLearnChance()) {
            player.learnSpell(spell);
            return true;
        }
        return false;
    }

    private boolean hasRequirements(Player player, Spell spell) {
        for (LearnRequirement req : spell.getLearnRequirements()) {
            switch (req.getType()) {
                case EXP:
                    if (player.getLevel() < (int) req.getValue()) {
                        return false;
                    }
                    break;
                case ITEMSTACK:

                    ItemStack item = (ItemStack) req.getValue();
                    int left = item.getAmount();
                    for (ItemStack stack : player.getInventory().getContents()) {
                        if (stack == null) {
                            continue;
                        }
                        if (stack.getType().equals(item.getType())) {
                            left -= stack.getAmount();
                        }
                    }
                    if (left > 0) {
                        return false;
                    }
                    break;
                case SCROLL:
                    int required = (int) req.getValue();
                    for (ItemStack stack : player.getInventory().getContents()) {
                        if (stack == null) {
                            continue;
                        }
                        if (stack.hasItemMeta() && stack.getItemMeta().getDisplayName().equals("Magical scroll")) {
                            required -= stack.getAmount();
                        }
                    }
                    if (required > 0) {
                        return false;
                    }
            }
        }
        return true;
    }

    private void takeRequirements(Player player, Spell spell) {
        for (LearnRequirement req : spell.getLearnRequirements()) {
            switch (req.getType()) {
                case EXP:
                    player.setLevel(player.getLevel() - (int) req.getValue());
                    break;
                case ITEMSTACK:
                    player.getInventory().removeItem((ItemStack) req.getValue());
                    break;
                case SCROLL:
                    int required = (int) req.getValue();
                    for (ItemStack stack : player.getInventory().getContents()) {
                        if (stack == null) {
                            continue;
                        }
                        if (stack.hasItemMeta() && stack.getItemMeta().getDisplayName().equals("Magical scroll")) {
                            if (required < stack.getAmount()) {
                                stack.setAmount(stack.getAmount() - required);
                                required = 0;
                            } else {
                                player.getInventory().removeItem(stack);
                                required -= stack.getAmount();
                            }
                        }
                        if (required <= 0) {
                            break;
                        }
                    }
            }
        }
    }

}
