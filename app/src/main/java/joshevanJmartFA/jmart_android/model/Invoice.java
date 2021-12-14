package joshevanJmartFA.jmart_android.model;

import java.util.Date;

/**
 * This class stores user invoice information, such as date, buyer id, product id,complaint id,
 * rating and extends serializable so that the complaint will have id that is serialized
 * @author Joshevan
 * @version 1.0
 *
 */
public class Invoice extends Serializable{
    /**
     * Invoice date
     */
    public Date date;
    /**
     * Buyer id
     */
    public int buyerId;
    /**
     * Product id
     */
    public int productId;
    /**
     * Product complaint
     */
    public int complaintId;
    /**
     * Product rating
     */
    public Rating rating;

    /**
     * Enumeration of product rating
     * @author Joshevan
     *
     */
    public static enum Rating{
        NONE,
        BAD,
        NEUTRAL,
        GOOD;
    }
    /**
     * Enumeration of invoice status
     * @author Joshevan
     *
     */
    public static enum Status{
        WAITING_CONFIRMATION,
        CANCELLED,
        ON_PROGRESS,
        ON_DELIVERY,
        DELIVERED,
        COMPLAINT,
        FINISHED,
        FAILED;
    }
}
