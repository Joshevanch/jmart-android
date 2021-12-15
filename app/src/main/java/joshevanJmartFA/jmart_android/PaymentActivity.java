package joshevanJmartFA.jmart_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import joshevanJmartFA.jmart_android.request.PaymentRequest;

/**
 * This class contains all layout and logic in the payment activity
 */
public class PaymentActivity extends AppCompatActivity {

    @Override
    /**
     * This method override AppCompatActivity.onCreate
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        EditText paymentProductCount = findViewById(R.id.paymentProductCount);
        EditText paymentShipmentAddress = findViewById(R.id.paymentShipmentAddress);
        Button buttonPayProduct = findViewById(R.id.payProduct);
        Button buttonCancelPayProduct = findViewById(R.id.cancelPayProduct);
        /**
         * Logic on button pay clicked
         */
        buttonPayProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Response.Listener<String> listener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject object = new JSONObject(response);
                            if (object != null){
                                Toast.makeText(PaymentActivity.this, "Payment successful", Toast.LENGTH_SHORT).show();
                                LoginActivity.getLoggedAccount().balance -= MainActivity.product.price;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(PaymentActivity.this, "Payment failed", Toast.LENGTH_SHORT).show();
                        }

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
        /**
         * Logic on cancel button clicked
         */
        buttonCancelPayProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PaymentActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}