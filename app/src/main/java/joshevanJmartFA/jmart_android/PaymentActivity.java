package joshevanJmartFA.jmart_android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import joshevanJmartFA.jmart_android.request.PaymentRequest;

public class PaymentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        EditText paymentProductCount = findViewById(R.id.paymentProductCount);
        EditText paymentShipmentAddress = findViewById(R.id.paymentShipmentAddress);
        Button buttonPayProduct = findViewById(R.id.payProduct);
        Button buttonCancelPayProduct = findViewById(R.id.cancelPayProduct);
        buttonPayProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Response.Listener<String> listener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                    }
                };
                Response.ErrorListener errorListener = new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                };
                PaymentRequest paymentRequest = new PaymentRequest(paymentProductCount.getText().toString(),
                        paymentShipmentAddress.getText().toString(),listener,errorListener);
                RequestQueue requestQueue = Volley.newRequestQueue(PaymentActivity.this);
                requestQueue.add (paymentRequest);
            }
        });
    }
}