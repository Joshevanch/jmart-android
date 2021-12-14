package joshevanJmartFA.jmart_android.model;

import java.util.ArrayList;
import java.util.Date;
/**
 * This class contains payment information, such as product count, shipment,
 * record history and extends invoice
 * @author Joshevan
 *
 */
public class Payment extends Invoice{
    /**
     * Number of products paid
     */
    public int productCount;
    /**
     * Payment shipment
     */
    public Shipment shipment;
    /**
     * List of record history
     */
    public ArrayList<Record> history= new ArrayList<Record>();
    /**
     * Inner class contains record information, such as status of the invoice,
     * record date, and record message
     * @author Joshevan
     *
     */
    public static class Record{
        public Status status;
        public Date date;
        public String message;
        /**
         * This is the default constructor for record
         * @param status record status
         * @param message record message
         */
        public Record (Status status, String message) {
            this.date = new Date();
            this.status = status;
            this.message = message;
        }
    }
    @Override
    /**
     * This method override toString for array adapter
     */
    public String toString(){
        return String.valueOf(this.productId) + "                                                 "+this.history.get(this.history.size()-1).status;
    }
}
