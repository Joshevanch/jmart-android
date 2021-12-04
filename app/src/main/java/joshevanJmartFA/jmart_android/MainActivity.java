package joshevanJmartFA.jmart_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.cardview.widget.CardView;

import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {
    TabLayout tabLayout;
    CardView cardViewProducts;
    CardView cardViewFilters;
    ListView listView;
    String product[] = {"Android","IPhone","WindowsMobile","Blackberry",
            "WebOS","Ubuntu","Windows7","Max OS X"};
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (LoginActivity.getLoggedAccount().store!=null){
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.menu_main, menu);
            return true;
        }
        else {
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.menu_main_login, menu);
            return true;
        }
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
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
        ArrayAdapter<String> productArrayAdapter = new ArrayAdapter<String>(this,R.layout.activity_listview,product);
        listView = findViewById(R.id.listView);
        listView.setAdapter(productArrayAdapter);
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

    }
}