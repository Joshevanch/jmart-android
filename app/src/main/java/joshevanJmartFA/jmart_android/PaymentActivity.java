package joshevanJmartFA.jmart_android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class PaymentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        TextView testPayment = findViewById(R.id.TESTPAYMENT);
        testPayment.setText(String.format ("%.2f", (MainActivity.product.price - MainActivity.product.discount)));
    }
}