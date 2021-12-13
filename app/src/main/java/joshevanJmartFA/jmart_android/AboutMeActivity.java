package joshevanJmartFA.jmart_android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

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

import joshevanJmartFA.jmart_android.model.Account;
import joshevanJmartFA.jmart_android.request.LoginRequest;
import joshevanJmartFA.jmart_android.request.RegisterStoreRequest;
import joshevanJmartFA.jmart_android.request.RequestFactory;
import joshevanJmartFA.jmart_android.request.TopUpRequest;

public class AboutMeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_me);
        CardView aboutMe = findViewById(R.id.cardViewAboutMe);
        CardView registerStore = findViewById(R.id.cardViewRegisterStore);
        CardView store =  findViewById(R.id.cardViewStore);
        Button registerStoreButton = findViewById(R.id.buttonRegisterStore);
        TextView aboutMeNameAccount = findViewById(R.id.aboutMeNameAccount);
        TextView aboutMeEmailAccount = findViewById(R.id.aboutMeEmailAccount);
        TextView aboutMeBalanceAccount = findViewById(R.id.aboutMeBalanceAccount);
        EditText topUpAmount = findViewById(R.id.aboutMeTopUpAmount);
        EditText nameStore = findViewById(R.id.nameRegisterStore);
        EditText addressStore = findViewById(R.id.addressRegisterStore);
        EditText phoneNumberStore = findViewById(R.id.phoneNumberRegisterStore);
        Button topUp = findViewById(R.id.buttonTopUp);
        Button register = findViewById(R.id.buttonStoreRegisterStore);
        Button cancel = findViewById(R.id.buttonStoreRegisterCancel);
        aboutMeNameAccount.setText(LoginActivity.getLoggedAccount().name);
        aboutMeEmailAccount.setText(LoginActivity.getLoggedAccount().email);
        aboutMeBalanceAccount.setText(String.format("%.2f",LoginActivity.getLoggedAccount().balance));
        TextView storeNameName = findViewById(R.id.storeNameName);
        TextView storeAddressAddress = findViewById(R.id.storeAddressAddress);
        TextView storePhoneNumberPhoneNumber = findViewById(R.id.storePhoneNumberPhoneNumber);

        topUp.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Response.Listener<String> listener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(AboutMeActivity.this, "Top Up Successful", Toast.LENGTH_SHORT).show();
                        LoginActivity.getLoggedAccount().balance += Double.parseDouble(topUpAmount.getText().toString());
                        aboutMeBalanceAccount.setText(String.valueOf(LoginActivity.getLoggedAccount().balance));
                    }
                };
                Response.ErrorListener errorListener = new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(AboutMeActivity.this, "System Error", Toast.LENGTH_SHORT).show();
                    }
                };
                TopUpRequest topUpRequest = new TopUpRequest(LoginActivity.getLoggedAccount().id, topUpAmount.getText().toString(),
                        listener, errorListener);
                RequestQueue requestQueue = Volley.newRequestQueue(AboutMeActivity.this);
                requestQueue.add (topUpRequest);
            }
        });
        if (LoginActivity.getLoggedAccount().store == null){
            registerStoreButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    registerStoreButton.setVisibility(View.GONE);
                    registerStore.setVisibility(View.VISIBLE);
                }
            });
            register.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Response.Listener<String> listener = new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try{
                                JSONObject object = new JSONObject(response);
                                if (object != null){
                                    Toast.makeText(AboutMeActivity.this, "Register Successful, please relog your account", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(AboutMeActivity.this, LoginActivity.class);
                                    startActivity(intent);
                                     }
                            }
                            catch (JSONException e) {
                                e.printStackTrace();
                                Toast.makeText(AboutMeActivity.this, "Register Error", Toast.LENGTH_SHORT).show();
                            }
                        }
                    };
                    Response.ErrorListener errorListener = new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(AboutMeActivity.this, "System Error", Toast.LENGTH_SHORT).show();
                        }
                    };
                    RegisterStoreRequest registerStoreRequest = new RegisterStoreRequest(LoginActivity.getLoggedAccount().id,
                            nameStore.getText().toString(), addressStore.getText().toString(), phoneNumberStore.getText().toString(),
                            listener,null);
                    RequestQueue requestQueue = Volley.newRequestQueue(AboutMeActivity.this);
                    requestQueue.add (registerStoreRequest);
                }
            });
            cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    registerStoreButton.setVisibility(View.VISIBLE);
                    registerStore.setVisibility(View.GONE);
                }
            });
        }
        else{
            registerStoreButton.setVisibility(View.GONE);
            store.setVisibility(View.VISIBLE);
            storeNameName.setText(LoginActivity.getLoggedAccount().store.name);
            storeAddressAddress.setText(LoginActivity.getLoggedAccount().store.address);
            storePhoneNumberPhoneNumber.setText(LoginActivity.getLoggedAccount().store.phoneNumber);
        }
    }
}