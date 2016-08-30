package testapp.example.com.databaseonlist;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DeleteWebsiteActivity extends Activity {
    Button bYes, bNo;
    TextView tvDelItem1;
    int confirmed = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_del_item);
        tvDelItem1 =(TextView) findViewById(R.id.tvDelItem);

        /*
        https://developer.android.com/reference/android/util/DisplayMetrics.html
        hiervan heb ik de displaymetric methode gehaald. voor het pop-up venster
         */
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int width = displayMetrics.widthPixels;
        int height = displayMetrics.heightPixels;

        final DatabaseHandler db = new DatabaseHandler(this);

        Intent intent = getIntent();
        final Website website = (Website) intent.getSerializableExtra("website_to_delete");

        getWindow().setLayout((int)(width*.6),(int)(height*.2));
        bYes = (Button) findViewById(R.id.bYes);
        bYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(confirmed == 0){
                    setTvDelItem("Are you sure you want to delete this item?");
                    confirmed = 1;
                }else {
                    db.deleteWebsite(website);

                    setResult(Activity.RESULT_OK);
                    finish();
                }
            }
        });
        bNo = (Button) findViewById(R.id.bNo);
        bNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTvDelItem("Do you want to delete this item");
                confirmed =0;

                finish();
            }
        });
    }
    public void setTvDelItem(String tvDelItem){tvDelItem1.setText(tvDelItem);}
}
