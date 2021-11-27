package joshevanJmartFA.jmart_android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView helloUser = findViewById(R.id.helloUser);
        helloUser.setText("Hello "+ LoginActivity.getLoggedAccount().name);
    }
}