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
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import joshevanJmartFA.jmart_android.model.Account;
import joshevanJmartFA.jmart_android.request.LoginRequest;

public class LoginActivity extends AppCompatActivity {
    private static final Gson gson = new Gson();
    private static Account loggedAccount = null;
    public static Account getLoggedAccount(){
        return LoginActivity.loggedAccount;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        EditText emailLogin = findViewById(R.id.emailLogin);
        EditText passwordLogin = findViewById(R.id.passwordLogin);
        TextView register = findViewById(R.id.register);
        Button buttonLogin = findViewById(R.id.buttonLogin);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Response.Listener<String> listener = new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONObject object = new JSONObject(response);
                            if (object != null){
                                Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                loggedAccount = gson.fromJson(object.toString(), Account.class);
                                startActivity(intent);
                            }
                        }
                        catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(LoginActivity.this, "Login Error", Toast.LENGTH_SHORT).show();
                        }
                    }
                };
                Response.ErrorListener errorListener = new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(LoginActivity.this, "System Error", Toast.LENGTH_SHORT).show();
                    }
                };
                LoginRequest loginRequest = new LoginRequest(emailLogin.getText().toString(),
                        passwordLogin.getText().toString(), listener,errorListener);
                RequestQueue requestQueue = Volley.newRequestQueue(LoginActivity.this);
                requestQueue.add (loginRequest);
            }
        });
    }
}