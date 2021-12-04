package joshevanJmartFA.jmart_android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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
        Button topUp = findViewById(R.id.buttonTopUp);
        aboutMeNameAccount.setText(LoginActivity.getLoggedAccount().name);
        aboutMeEmailAccount.setText(LoginActivity.getLoggedAccount().email);
        aboutMeBalanceAccount.setText(String.valueOf(LoginActivity.getLoggedAccount().balance));

        if (LoginActivity.getLoggedAccount().store == null){
            registerStoreButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    registerStoreButton.setVisibility(View.GONE);
                    registerStore.setVisibility(View.VISIBLE);
                }
            });
            topUp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
        else{
            registerStoreButton.setVisibility(View.GONE);
            store.setVisibility(View.VISIBLE);
        }
    }
}