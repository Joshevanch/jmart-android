package joshevanJmartFA.jmart_android.model;

import java.util.ArrayList;
import java.util.Date;

public class Payment extends Invoice{
    public int productCount;
    public Shipment shipment;
    public ArrayList<Record> history= new ArrayList<Record>();

    public static class Record{
        public Status status;
        public Date date;
        public String message;
        public Record (Status status, String message) {
            this.date = new Date();
            this.status = status;
            this.message = message;
        }
    }
    @Override
    public String toString(){
        return String.valueOf(this.productId) + this.history.get(this.history.size()-1).status;
    }
}
