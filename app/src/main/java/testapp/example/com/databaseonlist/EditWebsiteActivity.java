package testapp.example.com.databaseonlist;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class EditWebsiteActivity extends Activity implements View.OnTouchListener{
    public TextView tvWebsite, tvEmail, tvUsername, tvPassword, tvComment;
    public EditText etWebsite, etEmail, etUsername, etPassword, etComment, etUrl;
    public Button bChangeData, bReturn, bShowPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_website);

        etWebsite =(EditText) findViewById(R.id.etWebsite);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etUsername =(EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);
        etComment = (EditText) findViewById(R.id.etComment);
        etUrl = (EditText) findViewById(R.id.etUrl);

        bShowPassword = (Button) findViewById(R.id.bShowPassword);
        bShowPassword.setOnTouchListener(this);

        Intent intent = getIntent();
        final Website website = (Website) intent.getSerializableExtra("website_to_show_contact");
        if(website != null){
            etWebsite.setText(website.getName());
            etEmail.setText(website.getEmail());
            etUsername.setText(website.getUsername());
            etPassword.setText(website.getPassword());
            etComment.setText(website.getComment());
            etUrl.setText(website.getUrl());
        } else {
            etUrl.setText("http://");
        }
        bChangeData = (Button) findViewById(R.id.bChangeData);
        bChangeData.setText(website == null ? getString(R.string.btn_edit_website_create) : getString(R.string.btn_edit_website_update));
        bReturn = (Button) findViewById(R.id.bReturn);
        bChangeData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isEmpty(etWebsite, getString(R.string.website_name)) == true) {
                } else if (isEmpty(etEmail, getString(R.string.website_email)) == true) {
                } else if (isEmpty(etUsername, getString(R.string.website_username)) == true) {
                } else if (isEmpty(etPassword, getString(R.string.website_password)) == true) {
                } else {
                    DatabaseHandler db = new DatabaseHandler(EditWebsiteActivity.this);
                    if(bChangeData.getText().toString().equals(getString(R.string.btn_edit_website_create))){
                        db.addWebsite(etWebsite.getText().toString(), etEmail.getText().toString(),
                                etUsername.getText().toString(), etPassword.getText().toString(), etComment.getText().toString(), etUrl.getText().toString());
                        setResult(Activity.RESULT_OK);
                    }else{
                        db.updateWebsite(Integer.toString(website.getId()), etWebsite.getText().toString(), etEmail.getText().toString(),
                                etUsername.getText().toString(), etPassword.getText().toString(), etComment.getText().toString(), etUrl.getText().toString());
                        setResult(Activity.RESULT_OK);
                    }
                    finish();
                }
            }
        });
        bReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EditWebsiteActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
    private boolean isEmpty(EditText etText, String s){
        if (etText.getText().toString().trim().length() > 0){
            return false;
        } else {
            Toast.makeText(EditWebsiteActivity.this, s + " " + getString(R.string.required)  , Toast.LENGTH_SHORT).show();
            return true;
        }
    }
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                etPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                break;
            case MotionEvent.ACTION_UP:
                etPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                break;
        }
        return false;
    }
}
