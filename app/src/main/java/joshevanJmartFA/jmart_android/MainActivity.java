package joshevanJmartFA.jmart_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.Spinner;

import androidx.appcompat.widget.SearchView;
import androidx.cardview.widget.CardView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import joshevanJmartFA.jmart_android.model.Product;
import joshevanJmartFA.jmart_android.request.RequestFactory;

public class MainActivity extends AppCompatActivity {
    private static final Gson gson = new Gson();
    String searchText;
    SearchView searchView;
    TabLayout tabLayout;
    CardView cardViewProducts;
    CardView cardViewFilters;
    ListView listView;
    Button buttonNext;
    Button buttonPrev;
    Button buttonGo;
    EditText productPage;
    EditText filterName;
    EditText filterLowestPrice;
    EditText filterHighestPrice;
    Spinner filterProductCategory;
    Button applyFilter;
    Button clearFilter;
    private String filterProductCategoryString;
    private Boolean filterProduct = false;
    private Boolean filterConditionUsed = true;
    public static int page = 0;
    public static Product product = null;
    ArrayAdapter<Product> productArrayAdapter;
    public static  ArrayList<Product> productList = null;
    public static  ArrayList<Product> filteredProductList = null;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main_login, menu);
        if (LoginActivity.getLoggedAccount().store ==null){
            menu.findItem(R.id.createItem).setVisible(false);
        }
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.createItem:
                Intent a = new Intent(this,CreateProductActivity.class);
                this.startActivity(a);
                return true;
            case R.id.accountItem:
                Intent i = new Intent(this,AboutMeActivity.class);
                this.startActivity(i);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tabLayout = findViewById(R.id.tabLayout);
        cardViewProducts = findViewById(R.id.cardViewProducts);
        cardViewFilters = findViewById(R.id.cardViewFilters);
        buttonNext = findViewById(R.id.buttonNext);
        buttonPrev = findViewById(R.id.buttonPrev);
        buttonGo = findViewById(R.id.buttonGo);
        productPage = findViewById(R.id.editTextPage);
        listView = findViewById(R.id.listView);
        filterName = findViewById(R.id.editTextFilterName);
        filterLowestPrice = findViewById(R.id.editTextFilterLowestPrice);
        filterHighestPrice = findViewById(R.id.editTextFilterHighestPrice);
        filterProductCategory = findViewById(R.id.filterProductCategory);
        applyFilter = findViewById(R.id.buttonFilterApply);
        clearFilter = findViewById(R.id.buttonFilterClear);
        if (filterProduct == false) requestProduct();
        else requestFilteredProduct();
        requestProduct();
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()){
                    case 0:
                        cardViewProducts.setVisibility(View.VISIBLE);
                        cardViewFilters.setVisibility(View.GONE);
                        break;
                    case 1:
                        cardViewProducts.setVisibility(View.GONE);
                        cardViewFilters.setVisibility(View.VISIBLE);
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.page += 1;
                if (filterProduct == false) requestProduct();
                else requestFilteredProduct();
            }
        });
        buttonPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MainActivity.page >= 0) {
                    MainActivity.page -= 1;
                    if (filterProduct == false) requestProduct();
                    else requestFilteredProduct();
                }
            }
        });
        buttonGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Integer.parseInt(productPage.getText().toString()) >= 0){
                    MainActivity.page = Integer.parseInt(productPage.getText().toString());
                    if (filterProduct == false) requestProduct();
                    else requestFilteredProduct();
                }
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                product = (Product)productArrayAdapter.getItem(position);
                Intent intent = new Intent(MainActivity.this,ProductDetailActivity.class);
                startActivity(intent);
            }
        });
        filterProductCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                filterProductCategoryString = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        RadioGroup filterProductRadioGroup = findViewById(R.id.filterProductRadioGroup);
        filterProductRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.filterProductConditionNew){
                    filterConditionUsed = false;
                }
                else if (checkedId == R.id.filterProductConditionUsed){
                    filterConditionUsed =true;
                }
            }
        });
        applyFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filterProduct = true;
                requestFilteredProduct();
            }
        });
        clearFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filterProduct = false;
                requestProduct();
            }
        });
    }
    public void requestProduct() {
        Response.Listener<String> listener = new Response.Listener<String>() {


            @Override
            public void onResponse(String response) {

                try{
                    JSONArray object = new JSONArray(response);
                    if (object != null){
                        Type userListType = new TypeToken<List<Product>>(){}.getType();
                        MainActivity.productList = gson.fromJson(object.toString(),userListType);
                        productArrayAdapter = new ArrayAdapter<Product>(MainActivity.this,R.layout.activity_listview,productList);
                        listView.setAdapter(productArrayAdapter);
                    }
                }
                catch (JSONException e) {
                    e.printStackTrace();
                }

            }

        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = RequestFactory.getPage("product", page,10,listener,null);
        requestQueue.add (stringRequest);
    }
    public void requestFilteredProduct() {
        Response.Listener<String> listener = new Response.Listener<String>() {


            @Override
            public void onResponse(String response) {

                try{
                    JSONArray object = new JSONArray(response);
                    if (object != null){
                        Type userListType = new TypeToken<List<Product>>(){}.getType();
                        MainActivity.filteredProductList = gson.fromJson(object.toString(),userListType);
                        productArrayAdapter = new ArrayAdapter<Product>(MainActivity.this,R.layout.activity_listview,filteredProductList);
                        listView.setAdapter(productArrayAdapter);
                    }
                }
                catch (JSONException e) {
                    e.printStackTrace();
                }

            }

        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = RequestFactory.getFiltered(page,10,filterName.getText().toString(),
                Integer.parseInt(filterLowestPrice.getText().toString()),Integer.parseInt(filterHighestPrice.getText().toString()),
                filterProductCategoryString,filterConditionUsed, listener,null);
        requestQueue.add (stringRequest);
    }
}