package testapp.example.com.databaseonlist;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class LoginActivity extends AppCompatActivity {
    private EditText etPassword;
    public static boolean loggedIn = false;
    private TextView tvRegisterLink, tvChangePassword;
    public static final String DEFAULT="N/A";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        tvRegisterLink = (TextView) findViewById(R.id.tvRegisterLink);
        tvChangePassword = (TextView) findViewById(R.id.tvChangePassword);
        etPassword = (EditText) findViewById(R.id.etPassword);
    }
    public void onClickRegister(View v){
        switch (v.getId()) {
            case R.id.tvRegisterLink:
                SharedPreferences sharedPreferences = getSharedPreferences("myData", Context.MODE_PRIVATE);
                boolean registered = sharedPreferences.getBoolean("registered", false);
                Intent intent = new Intent(LoginActivity.this, Register.class);
                if (registered == false) {
                    startActivity(intent);
                }else{
                    Toast.makeText(LoginActivity.this, "you already registered", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.tvChangePassword:
                sharedPreferences = getSharedPreferences("myData", Context.MODE_PRIVATE);
                registered = sharedPreferences.getBoolean("registered", false);
                if (registered == false) {
                    Toast.makeText(LoginActivity.this, "you are not registered", Toast.LENGTH_SHORT).show();
                }else{
                    intent = new Intent(LoginActivity.this, Register.class);
                    startActivity(intent);
                }
                break;
            case R.id.bSettings:
                Intent intent2 = new Intent(LoginActivity.this, ChangeColor.class);
                startActivity(intent2);
                break;
        }
    }
    public static boolean getLoggedIn(){
        return loggedIn;
    }
    public void setLoggedIn(boolean loggedIn1){
        loggedIn = loggedIn1;
    }
    public void checkPasswords(View v){
        SharedPreferences sharedPreferences1 = getSharedPreferences("myData", Context.MODE_PRIVATE);
        String pass = sharedPreferences1.getString("password", DEFAULT);
        SharedPreferences sharedPreferences2 = getSharedPreferences("myData", Context.MODE_PRIVATE);
        boolean registered = sharedPreferences2.getBoolean("registered", false);
        if(registered == false){
            Toast.makeText(LoginActivity.this, "You have not registered a password", Toast.LENGTH_SHORT).show();}
        else if (isEmpty(etPassword, "password")){}
        else if (!pass.equals(etPassword.getText().toString())){
            Toast.makeText(LoginActivity.this, "Password is incorrect", Toast.LENGTH_SHORT).show();
        } else if (pass.equals(etPassword.getText().toString())){
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            setLoggedIn(true);
        }
    }
    private boolean isEmpty(EditText etText, String s){
        if (etText.getText().toString().trim().length() > 0){
            return false;
        }else{
            Toast.makeText(LoginActivity.this, "you did not enter a " + s, Toast.LENGTH_SHORT).show();
            return true;
        }
    }
}
