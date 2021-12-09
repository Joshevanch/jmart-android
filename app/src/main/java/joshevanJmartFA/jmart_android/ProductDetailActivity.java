package joshevanJmartFA.jmart_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Locale;

public class ProductDetailActivity extends AppCompatActivity {
    static String shipmentPlansString ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail_acitivity);
        TextView productDetailName = findViewById(R.id.productDetailName);
        TextView productDetailPrice = findViewById(R.id.productDetailPrice);
        TextView productDetailCategory = findViewById(R.id.productDetailCategory);
        TextView productDetailDiscount = findViewById(R.id.productDetailDiscount);
        TextView productDetailConditionUsed = findViewById(R.id.productDetailConditionUsed);
        TextView productDetailShipmentPlans = findViewById(R.id.productDetailShipmentPlans);
        TextView productDetailAccountId = findViewById(R.id.productDetailAccountId);
        Button buttonPayProduct = findViewById(R.id.buttonPayProduct);
        Button buttonPayCancel = findViewById(R.id.buttonProductCancel);
        productDetailName.setText(MainActivity.product.name);
        productDetailPrice.setText(String.format("%.2f",MainActivity.product.price));
        productDetailCategory.setText(MainActivity.product.category.name());
        productDetailDiscount.setText(String.format("%.2f",MainActivity.product.discount));
        if (MainActivity.product.conditionUsed == true){
            productDetailConditionUsed.setText("USED");
        }
        else if (MainActivity.product.conditionUsed == false) {
            productDetailConditionUsed.setText("NEW");
        }
        productDetailAccountId.setText(String.valueOf(MainActivity.product.accountId));
        setProductDetailShipmentPlans(MainActivity.product.shipmentPlans);
        productDetailShipmentPlans.setText(shipmentPlansString);
        buttonPayProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProductDetailActivity.this, PaymentActivity.class);
                startActivity(intent);
            }
        });
        buttonPayCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProductDetailActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void setProductDetailShipmentPlans (Byte shipmentPlans){
        if (MainActivity.product.shipmentPlans == 0){
            shipmentPlansString = "INSTANT";
        }
        else if (MainActivity.product.shipmentPlans == 1){
            shipmentPlansString = "SAME_DAY";
        }
        else if (MainActivity.product.shipmentPlans == 2){
            shipmentPlansString = "NEXT_DAY";
        }
        else if (MainActivity.product.shipmentPlans == 3){
            shipmentPlansString = "REGULER";
        }
        else if (MainActivity.product.shipmentPlans == 4){
            shipmentPlansString = "KARGO";
        }
    }
}