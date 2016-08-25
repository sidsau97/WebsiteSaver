package testapp.example.com.databaseonlist;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Register extends AppCompatActivity implements View.OnClickListener {
    EditText etPassword, etPassword2, etOldpassword;
    Button bRegister;
    TextView tvOldpassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etOldpassword = (EditText) findViewById(R.id.etOldPassword);
        tvOldpassword = (TextView) findViewById(R.id.tvOldPassword);
        etPassword = (EditText) findViewById(R.id.etPassword);
        etPassword2 = (EditText) findViewById(R.id.etPassword2);
        bRegister = (Button) findViewById(R.id.bRegister);
        bRegister.setOnClickListener(this);

        SharedPreferences sharedPreferences = getSharedPreferences("myData", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if(sharedPreferences.getBoolean("registered", true)){
            etOldpassword.setVisibility(View.VISIBLE);
            tvOldpassword.setVisibility(View.VISIBLE);
        } else {
            etOldpassword.setVisibility(View.INVISIBLE);
            tvOldpassword.setVisibility(View.INVISIBLE);
        }
        editor.putBoolean("registered", true);
        editor.commit();
    }
    private boolean isEmpty(EditText etText, String s){
        if (etText.getText().toString().trim().length() > 0){
            return false;
        }else{
            Toast.makeText(Register.this, "you did not enter a " + s, Toast.LENGTH_SHORT).show();
            return true;
        }
    }
    @Override
    public void onClick(View v){
        if (isEmpty(etPassword, "password")){
        }else if (isEmpty(etPassword2, "password2")){
        }else if(!etPassword.getText().toString().matches(etPassword2.getText().toString())){
            Toast.makeText(Register.this, "Enter the same password", Toast.LENGTH_SHORT).show();
        }
        else if (etPassword.getText().toString().matches(etPassword2.getText().toString())){
            SharedPreferences sharedPreferences = getSharedPreferences("myData", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("registered", true);
            editor.putString("password", etPassword.getText().toString());
            editor.commit();

            Intent intent = new Intent(Register.this, LoginActivity.class);
            startActivity(intent);
        }
    }
}
