package joshevanJmartFA.jmart_android;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.cardview.widget.CardView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
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

import joshevanJmartFA.jmart_android.model.Payment;
import joshevanJmartFA.jmart_android.request.RequestFactory;

public class InvoiceHistoryActivity extends AppCompatActivity {
    private static final Gson gson = new Gson();
    String searchText;
    SearchView searchView;
    TabLayout tabLayoutPayment;
    CardView cardViewStoreActivity;
    CardView cardViewAccountActivity;
    ListView listViewStore;
    ListView listViewAccount;
    Button buttonNextStore;
    Button buttonPrevStore;
    Button buttonGoStore;
    Button buttonNextAccount;
    Button buttonPrevAccount;
    Button buttonGoAccount;
    EditText storePage;
    EditText accountPage;
    public static int pageStore = 0;
    public static int pageAccount = 0;
    public static Payment storePayment = null;
    public static Payment accountPayment = null;
    ArrayAdapter<Payment> storeArrayAdapter;
    ArrayAdapter<Payment> accountArrayAdapter;
    public static  ArrayList<Payment> storePaymentList = null;
    public static  ArrayList<Payment> accountPaymentList = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invoice_history);
        tabLayoutPayment = findViewById(R.id.tabLayoutPayment);
        cardViewStoreActivity  = findViewById(R.id.cardViewStoreActivity);
        cardViewAccountActivity = findViewById(R.id.cardViewAccountActivity);
        buttonNextStore = findViewById(R.id.buttonNextStore);
        buttonPrevStore = findViewById(R.id.buttonPrevStore);
        buttonGoStore = findViewById(R.id.buttonGoStore);
        listViewStore = findViewById(R.id.listViewStore);
        listViewAccount = findViewById(R.id.listViewAccount);
        buttonNextAccount = findViewById(R.id.buttonNextAccount);
        buttonPrevAccount = findViewById(R.id.buttonPrevAccount);
        buttonGoAccount = findViewById(R.id.buttonGoAccount);
        storePage = findViewById(R.id.editTextPageStore);
        accountPage = findViewById(R.id.editTextPageAccount);
        requestStorePayment();
        requestAccountPayment();
        if (LoginActivity.getLoggedAccount().store == null){
            tabLayoutPayment.removeTabAt(0);
            cardViewAccountActivity.setVisibility(View.VISIBLE);
            cardViewStoreActivity.setVisibility(View.GONE);
        }

        tabLayoutPayment.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()){
                    case 0:
                        cardViewStoreActivity.setVisibility(View.VISIBLE);
                        cardViewAccountActivity.setVisibility(View.GONE);
                        break;
                    case 1:
                        cardViewStoreActivity.setVisibility(View.GONE);
                        cardViewAccountActivity.setVisibility(View.VISIBLE);
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


        buttonNextStore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InvoiceHistoryActivity.pageStore += 1;
                requestStorePayment();
            }
        });
        buttonPrevStore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (InvoiceHistoryActivity.pageStore >0 ){
                    InvoiceHistoryActivity.pageStore -= 1;
                    requestStorePayment();
                }
                else{
                    Toast.makeText(InvoiceHistoryActivity.this,"This is start of the page",Toast.LENGTH_SHORT).show();
                }
            }
        });
        buttonGoStore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Integer.parseInt(storePage.getText().toString()) >= 0){
                    InvoiceHistoryActivity.pageStore = Integer.parseInt(storePage.getText().toString());
                }
                else{
                    Toast.makeText(InvoiceHistoryActivity.this,"Invalid page input",Toast.LENGTH_SHORT).show();
                }
            }
        });
        listViewStore.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                storePayment = (Payment) storeArrayAdapter.getItem(position);
                Intent intent = new Intent(InvoiceHistoryActivity.this, StorePaymentActivity.class);
                startActivity(intent);
            }
        });
        buttonNextAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InvoiceHistoryActivity.pageAccount += 1;
                requestAccountPayment();
            }
        });
        buttonPrevAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (InvoiceHistoryActivity.pageAccount >0 ){
                    InvoiceHistoryActivity.pageAccount -= 1;
                    requestAccountPayment();
                }
            }
        });
        buttonGoAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Integer.parseInt(accountPage.getText().toString()) >= 0){
                    InvoiceHistoryActivity.pageAccount = Integer.parseInt(accountPage.getText().toString());
                }
                else{
                    Toast.makeText(InvoiceHistoryActivity.this,"Invalid page input",Toast.LENGTH_SHORT).show();
                }
            }
        });
        listViewAccount.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                accountPayment = (Payment) accountArrayAdapter.getItem(position);
                Intent intent = new Intent(InvoiceHistoryActivity.this, AccountPaymentActivity.class);
                startActivity(intent);
            }
        });
}
    private void requestStorePayment() {
        Response.Listener<String> listener = new Response.Listener<String>() {


            @Override
            public void onResponse(String response) {

                try{
                    JSONArray object = new JSONArray(response);
                    if (object != null){
                        Type userListType = new TypeToken<List<Payment>>(){}.getType();
                        InvoiceHistoryActivity.storePaymentList = gson.fromJson(object.toString(),userListType);
                        storeArrayAdapter = new ArrayAdapter<Payment>(InvoiceHistoryActivity.this,R.layout.activity_listview,storePaymentList);
                        listViewStore.setAdapter(storeArrayAdapter);
                    }
                }
                catch (JSONException e) {
                    e.printStackTrace();
                }

            }

        };
        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(InvoiceHistoryActivity.this, "System Error", Toast.LENGTH_SHORT).show();
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = RequestFactory.getPayment(LoginActivity.getLoggedAccount().id,
                "storepayment",InvoiceHistoryActivity.pageStore,10,listener,errorListener);
        requestQueue.add (stringRequest);
    }
    private void requestAccountPayment() {
        Response.Listener<String> listener = new Response.Listener<String>() {


            @Override
            public void onResponse(String response) {

                try{
                    JSONArray object = new JSONArray(response);
                    if (object != null){
                        Type userListType = new TypeToken<List<Payment>>(){}.getType();
                        InvoiceHistoryActivity.accountPaymentList = gson.fromJson(object.toString(),userListType);
                        accountArrayAdapter = new ArrayAdapter<Payment>(InvoiceHistoryActivity.this,R.layout.activity_listview,accountPaymentList);
                        listViewAccount.setAdapter(accountArrayAdapter);
                    }
                }
                catch (JSONException e) {
                    e.printStackTrace();
                }

            }

        };
        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(InvoiceHistoryActivity.this, "System Error", Toast.LENGTH_SHORT).show();
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = RequestFactory.getPayment(LoginActivity.getLoggedAccount().id,
                "buyerpayment",InvoiceHistoryActivity.pageAccount,10,listener,errorListener);
        requestQueue.add (stringRequest);
    }
}