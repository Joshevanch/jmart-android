package joshevanJmartFA.jmart_android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class StoreActivity extends AppCompatActivity {
    DateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy HH:mm:ss");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);
        TextView storePaymentBuyerId = findViewById(R.id.storePaymentBuyerId);
        TextView storePaymentDate = findViewById(R.id.storePaymentDate);
        TextView storePaymentStatus = findViewById(R.id.storePaymentStatus);
        TextView storePaymentShipmentAddress = findViewById(R.id.storePaymentShipmentAddress);
        storePaymentBuyerId.setText(String.valueOf(InvoiceHistoryActivity.storePayment.buyerId));
        storePaymentDate.setText(dateFormat.format(InvoiceHistoryActivity.storePayment.date));
        storePaymentStatus.setText(InvoiceHistoryActivity.storePayment.history.get(InvoiceHistoryActivity.storePayment.history.size()-1).status.name());
        storePaymentShipmentAddress.setText(InvoiceHistoryActivity.storePayment.shipment.address);
    }
}