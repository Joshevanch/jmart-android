package joshevanJmartFA.jmart_android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ProductDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail_acitivity);
        TextView productDetailName = findViewById(R.id.productDetailName);
        TextView productDetailPrice = findViewById(R.id.productDetailPrice);
        TextView productDetailCategory = findViewById(R.id.productDetailCategory);
        productDetailName.setText(MainActivity.product.name);
        productDetailPrice.setText(String.valueOf(MainActivity.product.price));
        productDetailCategory.setText(MainActivity.product.category.name());
    }
}