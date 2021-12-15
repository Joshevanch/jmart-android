package joshevanJmartFA.jmart_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import joshevanJmartFA.jmart_android.model.Account;
import joshevanJmartFA.jmart_android.model.Product;
import joshevanJmartFA.jmart_android.request.CreateProductRequest;
import joshevanJmartFA.jmart_android.request.LoginRequest;

/**
 * This class contains all layout and logic in the create product activity
 */
public class CreateProductActivity extends AppCompatActivity {
    private static final Gson gson = new Gson();
    private static Product product = null;
    private String shipmentPlansString = "0";
    private String createProductConditionUsed = "false";
    private String productCategoryString ;
    private String getSelectedShipmentPlans ;

    @Override
    /**
     * This method override AppCompatActivity.onCreate
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Gson gson = new Gson();
        setContentView(R.layout.activity_create_product);
        EditText createProductName = findViewById(R.id.createProductName);
        EditText createProductWeight = findViewById(R.id.createProductWeight);
        EditText createProductPrice = findViewById(R.id.createProductPrice);
        EditText createProductDiscount = findViewById(R.id.createProductDiscount);
        Spinner productCategory = findViewById(R.id.createProductCategory);
        Spinner shipmentPlans = findViewById(R.id.createProductShipmentPlan);
        /**
         * Logic when product category spinner clicked
         */
        productCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                productCategoryString = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        /**
         * Logic on shipment plans spinner  clicked
         */
        shipmentPlans.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                getSelectedShipmentPlans = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        RadioGroup createProductRadioGroup = findViewById(R.id.createProductRadioGroup);
        /**
         * Login on radio group
         */
        createProductRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.createProductConditionNew){
                    createProductConditionUsed = "false";
                }
                else if (checkedId == R.id.createProductConditionUsed){
                    createProductConditionUsed = "true";
                }
            }
        });
        Button buttonCreateProduct= findViewById(R.id.buttonCreateProduct);
        Button buttonCancelProduct = findViewById(R.id.buttonCreateProductCancel);
        /**
         *  Logic on button create product clicked
         */
        buttonCreateProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Response.Listener<String> listener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject object = new JSONObject(response);
                            if (object != null) {
                                Toast.makeText(CreateProductActivity.this, "Create Product Successful", Toast.LENGTH_SHORT).show();
                                product = gson.fromJson(object.toString(), Product.class);
                                MainActivity.productList.add(product);
                                Intent intent = new Intent (CreateProductActivity.this, MainActivity.class);
                                startActivity(intent);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(CreateProductActivity.this, "Create Product failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                };
                Response.ErrorListener errorListener = new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(CreateProductActivity.this, "System error", Toast.LENGTH_SHORT).show();
                    }
                };
                if (getSelectedShipmentPlans.equals("INSTANT")) {
                    shipmentPlansString = "0";
                } else if (getSelectedShipmentPlans.equals("SAME_DAY")) {
                    shipmentPlansString = "1";
                } else if (getSelectedShipmentPlans.equals("NEXT_DAY")) {
                    shipmentPlansString = "2";
                } else if (getSelectedShipmentPlans.equals("REGULER")) {
                    shipmentPlansString = "3";
                } else if (getSelectedShipmentPlans.equals("KARGO")) {
                    shipmentPlansString = "4";
                }
                CreateProductRequest createProductRequest = new CreateProductRequest(String.valueOf(LoginActivity.getLoggedAccount().id),
                        createProductName.getText().toString(), createProductWeight.getText().toString(),
                        createProductConditionUsed, createProductPrice.getText().toString(), createProductDiscount.getText().toString(),
                        productCategoryString, shipmentPlansString, listener, errorListener);
                RequestQueue requestQueue = Volley.newRequestQueue(CreateProductActivity.this);
                requestQueue.add(createProductRequest);
            }
        });
        /**
         * Logic on button cancel clicked
         */
        buttonCancelProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (CreateProductActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}