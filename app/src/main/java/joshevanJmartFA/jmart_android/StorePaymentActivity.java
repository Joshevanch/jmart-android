package joshevanJmartFA.jmart_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import joshevanJmartFA.jmart_android.model.Invoice;
import joshevanJmartFA.jmart_android.request.CancelPaymentRequest;
import joshevanJmartFA.jmart_android.request.SubmitPaymentRequest;

/**
 * This class contains all layout and logic in the store payment activity
 */
public class StorePaymentActivity extends AppCompatActivity {
    DateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy HH:mm:ss");
    /**
     * This method override AppCompatActivity.onCreate
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_payment);
        TextView storePaymentBuyerId = findViewById(R.id.storePaymentBuyerId);
        TextView storePaymentDate = findViewById(R.id.storePaymentDate);
        TextView storePaymentStatus = findViewById(R.id.storePaymentStatus);
        TextView storePaymentShipmentAddress = findViewById(R.id.storePaymentShipmentAddress);
        Button buttonSubmitStorePayment = findViewById(R.id.buttonSubmitStorePayment);
        Button buttonCancelStorePayment = findViewById(R.id.buttonCancelStorePayment);
        storePaymentBuyerId.setText(String.valueOf(InvoiceHistoryActivity.storePayment.buyerId));
        storePaymentDate.setText(dateFormat.format(InvoiceHistoryActivity.storePayment.date));
        storePaymentStatus.setText(InvoiceHistoryActivity.storePayment.history.get(InvoiceHistoryActivity.storePayment.history.size()-1).status.name());
        storePaymentShipmentAddress.setText(InvoiceHistoryActivity.storePayment.shipment.address);
        if (InvoiceHistoryActivity.storePayment.history.get(InvoiceHistoryActivity.storePayment.history.size()-1).status != Invoice.Status.WAITING_CONFIRMATION){
            buttonSubmitStorePayment.setVisibility(View.GONE);
            buttonCancelStorePayment.setVisibility(View.GONE);
        }
        /**
         * Logic on button submit store payment clicked
         */
        buttonSubmitStorePayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Response.Listener listener = new Response.Listener() {
                    @Override
                    public void onResponse(Object response) {
                        Toast.makeText(StorePaymentActivity.this, "Submit Payment Successful", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(StorePaymentActivity.this, MainActivity.class);
                        startActivity(intent);
                    }
                };
                Response.ErrorListener errorListener = new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(StorePaymentActivity.this, "Submit Payment Failed", Toast.LENGTH_SHORT).show();
                    }
                };
                SubmitPaymentRequest submitPaymentRequest = new SubmitPaymentRequest(InvoiceHistoryActivity.storePayment.id,
                        InvoiceHistoryActivity.storePayment.shipment.address ,listener, errorListener);
                RequestQueue requestQueue = Volley.newRequestQueue(StorePaymentActivity.this);
                requestQueue.add (submitPaymentRequest);
            }
        });
        /**
         * Logic on button cancel store payment clicked
         */
        buttonCancelStorePayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Response.Listener listener = new Response.Listener() {
                    @Override
                    public void onResponse(Object response) {
                        Toast.makeText(StorePaymentActivity.this, "Cancel Payment Successful", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(StorePaymentActivity.this, MainActivity.class);
                        startActivity(intent);
                    }
                };
                Response.ErrorListener errorListener = new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(StorePaymentActivity.this, "Cancel Payment Failed", Toast.LENGTH_SHORT).show();
                    }
                };
                CancelPaymentRequest cancelPaymentRequest = new CancelPaymentRequest(InvoiceHistoryActivity.storePayment.id,
                        listener, errorListener);
                RequestQueue requestQueue = Volley.newRequestQueue(StorePaymentActivity.this);
                requestQueue.add (cancelPaymentRequest);
            }
        });
    }
}