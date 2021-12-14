package joshevanJmartFA.jmart_android.model;
import java.util.HashMap;

/**
 * This class used for serialized object with id
 * @author Joshevan
 *
 */
public class Serializable implements Comparable<Serializable>
{
    /**
     * Hash map for id
     */
    private static HashMap <Class<?>, Integer> mapCounter = new HashMap<>();
    /**
     * object id
     */
    public final int id;
    /**
     * This is the default constructor, construct id and put id to hashmap
     */
    protected Serializable(){
        Integer counter = mapCounter.get(getClass());
        counter = counter == null ? 0 : counter + 1;
        mapCounter.put(getClass(), counter);
        this.id = counter;

    }
    /**
     * This method set id to object
     * @param <T> Can be used with all types of objects
     * @param clazz object class
     * @param id object id
     * @return Integer id
     */
    public static <T extends Serializable>Integer setClosingId(Class<T> clazz, int id) {
        return mapCounter.put(clazz, id);
    }
    /**
     * This method get object id
     * @param <T> Can be used with all types of objects
     * @param clazz object class
     * @return Integer id
     */
    public static <T extends Serializable>Integer getClosingId(Class <T> clazz) {
        return mapCounter.get(clazz);
    }
    /**
     * This method compare object with this object
     */
    public boolean equals (Object other){
        return (other instanceof Serializable && id == ((Serializable)other).id);
    }
    /**
     * This method compare object with serializable object
     * @param other serializable object
     * @return true if object same with serializable object
     */
    public boolean equals (Serializable other){
        return (id == other.id);
    }
    /**
     * This method compare object with serializable object
     */
    public int compareTo(Serializable other) {
        return Integer.compare(this.id, other.id);
    }
}