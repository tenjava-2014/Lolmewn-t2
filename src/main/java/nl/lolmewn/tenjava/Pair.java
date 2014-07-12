package nl.lolmewn.tenjava;

/**
 *
 * @author Lolmewn
 */
public class Pair {
    
    private final String find, replace;
    
    public Pair(String find, String replace){
        this.find = find;
        this.replace = replace;
    }

    public String getFind() {
        return find;
    }

    public String getReplace() {
        return replace;
    }

}
