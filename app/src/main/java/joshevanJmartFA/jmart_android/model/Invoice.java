package joshevanJmartFA.jmart_android.model;

import java.util.Date;

public class Invoice extends Serializable{
    public Date date;
    public int buyerId;
    public int productId;
    public int complaintId;
    public Rating rating;

    public static enum Rating{
        NONE,
        BAD,
        NEUTRAL,
        GOOD;
    }
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
