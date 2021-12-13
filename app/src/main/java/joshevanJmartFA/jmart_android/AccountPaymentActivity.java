package joshevanJmartFA.jmart_android;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import joshevanJmartFA.jmart_android.model.Invoice;
import joshevanJmartFA.jmart_android.model.Payment;
import joshevanJmartFA.jmart_android.request.CancelPaymentRequest;
import joshevanJmartFA.jmart_android.request.TopUpRequest;

public class AccountPaymentActivity extends AppCompatActivity {
    DateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy HH:mm:ss");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_payment);
        TextView accountPaymentBuyerId = findViewById(R.id.accountPaymentProductId);
        TextView accountPaymentDate = findViewById(R.id.accountPaymentDate);
        TextView accountPaymentStatus = findViewById(R.id.accountPaymentStatus);
        TextView accountPaymentShipmentAddress = findViewById(R.id.accountPaymentShipmentAddress);
        Button buttonCancelAccountPayment = findViewById(R.id.buttonCancelAccountPayment);
        accountPaymentBuyerId.setText(String.valueOf(InvoiceHistoryActivity.accountPayment.productId));
        accountPaymentDate.setText(dateFormat.format(InvoiceHistoryActivity.accountPayment.date));
        accountPaymentStatus.setText(InvoiceHistoryActivity.accountPayment.history.get(InvoiceHistoryActivity.accountPayment.history.size()-1).status.name());
        accountPaymentShipmentAddress.setText(InvoiceHistoryActivity.accountPayment.shipment.address);
        if (InvoiceHistoryActivity.accountPayment.history.get(InvoiceHistoryActivity.accountPayment.history.size()-1).status != Invoice.Status.WAITING_CONFIRMATION){
             buttonCancelAccountPayment.setVisibility(View.GONE);
        }
        buttonCancelAccountPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Response.Listener<String> listener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(AccountPaymentActivity.this, "Cancel Payment Successful", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(AccountPaymentActivity.this, MainActivity.class);
                        startActivity(intent);
                    }
                };
                Response.ErrorListener errorListener = new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(AccountPaymentActivity.this, "Cancel Payment failed", Toast.LENGTH_SHORT).show();
                    }
                };
                CancelPaymentRequest cancelPaymentRequest = new CancelPaymentRequest(InvoiceHistoryActivity.accountPayment.id,
                        listener, errorListener);
                RequestQueue requestQueue = Volley.newRequestQueue(AccountPaymentActivity.this);
                requestQueue.add (cancelPaymentRequest);
            }
        });
    }
}