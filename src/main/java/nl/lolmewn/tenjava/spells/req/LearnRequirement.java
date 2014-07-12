package nl.lolmewn.tenjava.spells.req;

/**
 *
 * @author Lolmewn
 * @param <T> Type required to learn a spell
 */
public class LearnRequirement <T>{
    
    private final RequirementType type;
    private final T value;
    
    public LearnRequirement(RequirementType type, T value){
        this.type = type;
        this.value = value;
    }

    public RequirementType getType() {
        return type;
    }

    public T getValue() {
        return value;
    }

}
