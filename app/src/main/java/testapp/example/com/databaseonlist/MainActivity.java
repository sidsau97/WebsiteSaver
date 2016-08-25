package testapp.example.com.databaseonlist;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends Activity {
    private ListView listView;
    private WebsiteListAdapter websiteListAdapter;
    public static List<Website> websites = new ArrayList<Website>();
    private Button BAdd;
    private DatabaseHandler db;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        if(!LoginActivity.getLoggedIn()){
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
        }
        db = new DatabaseHandler(this);


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BAdd = (Button) findViewById(R.id.bAdd);
        BAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, EditWebsiteActivity.class);
                startActivityForResult(intent, 1);
            }
        });

        websites = db.getAllWebsites();

        listView = (ListView) findViewById(R.id.websiteListView);
        websiteListAdapter = new WebsiteListAdapter(this, websites);
        listView.setAdapter(websiteListAdapter);
        listView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                Website website = (Website) parent.getItemAtPosition(position);
                Intent intent = new Intent(MainActivity.this, EditWebsiteActivity.class);
                intent.putExtra("website_to_show_contact", website);
                startActivityForResult(intent, 2);
            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Website website = (Website) parent.getItemAtPosition(position);
                Intent intent = new Intent(MainActivity.this, DeleteWebsiteActivity.class);
                intent.putExtra("website_to_delete",website);
                startActivityForResult(intent, 3);
                return true;
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == 1 || requestCode == 2 || requestCode == 3){
            websites.clear();
            websites.addAll(db.getAllWebsites());
            websiteListAdapter.notifyDataSetChanged();
        }
    }
}
