package nl.lolmewn.tenjava;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;

/**
 *
 * @author Lolmewn
 */
public class Messages {
    
    private static YamlConfiguration config;
    
    public Messages(YamlConfiguration config){
        Messages.config = config;
    }
    
    public static String getMessage(String path){
        return colorise(config.getString(path));
    }
    
    public static String getMessage(String path, Pair... pairs){
        String message = config.getString(path);
        for(Pair pair : pairs){
            message = message.replace(pair.getFind(), pair.getReplace());
        }
        return colorise(message);
    }
    
    private static String colorise(String message){
        return ChatColor.translateAlternateColorCodes('&', message);
    }

}
