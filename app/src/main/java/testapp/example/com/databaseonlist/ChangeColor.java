package testapp.example.com.databaseonlist;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.SeekBar;
import android.widget.TextView;

public class ChangeColor extends AppCompatActivity {
    int rB, bB, gB, rT, gT, bT, r,g,b = 0;
    int tB = 0;//0 is to edit text, 1 is to edit background
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_color);

        final TextView tvRedValue = (TextView) findViewById(R.id.tvRedValue);
        tvRedValue.setText(String.valueOf(r));
        tvRedValue.setPadding((int)((r*3.5)+10),0,0,0);
        final TextView tvGreenValue = (TextView) findViewById(R.id.tvGreenValue);
        tvGreenValue.setText(String.valueOf(g));
        tvGreenValue.setPadding((int)((g*3.5)+10),0,0,0);
        final TextView tvBlueValue = (TextView) findViewById(R.id.tvBlueValue);
        tvBlueValue.setText(String.valueOf(b));
        tvGreenValue.setPadding((int)((b*3.5)+10),0,0,0);

        final TextView editingText = (TextView) findViewById(R.id.editingText);

        final FrameLayout frameLayout =(FrameLayout) findViewById(R.id.colorBox);
        frameLayout.setBackgroundColor(Color.rgb(r,g,b));
        final SeekBar seekBarRed = (SeekBar) findViewById(R.id.seekBarRed);

        seekBarRed.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                r = (int) (progress*255/100);
                frameLayout.setBackgroundColor(Color.rgb(r,g,b));
                tvRedValue.setText(String.valueOf(r));
                tvRedValue.setPadding((int)((r*3.5)+10),0,0,0);
                if(tB == 1){
                    rB = r;
                }else{
                    rT = r;
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
        final SeekBar seekBarGreen = (SeekBar) findViewById(R.id.seekBarGreen);
        seekBarGreen.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                g = (int) (progress*255/100);
                frameLayout.setBackgroundColor(Color.rgb(r,g,b));
                tvGreenValue.setText(String.valueOf(g));
                tvGreenValue.setPadding((int)((g*3.5)+10),0,0,0);
                if(tB == 1){
                    gB = g;
                }else{
                    gT = g;
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
        final SeekBar seekBarBlue = (SeekBar) findViewById(R.id.seekBarBlue);
        seekBarBlue.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                b = (int) (progress*255/100);
                frameLayout.setBackgroundColor(Color.rgb(r,g,b));
                tvBlueValue.setText(String.valueOf(b));
                tvBlueValue.setPadding((int)((b*3.5)+10),0,0,0);
                if(tB == 1){
                    bB = b;
                }else{
                    bT = b;
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
        Button saveColors = (Button) findViewById(R.id.saveColors);
        final Button saveBackgroundColor = (Button) findViewById(R.id.saveBackgroundColor);
        final Button saveTextColor = (Button) findViewById(R.id.saveTextColor);
        saveColors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChangeColor.this, LoginActivity.class);
                if(tB == 0){
                    String textColors = rT + "\n" + gT + "\n" + bT;
                    getIntent().putExtra("textColors", textColors);
                }else{
                    String backgroundColors = rB  + "\n" + gB + "\n" + bB;
                    getIntent().putExtra("backgroundColors", backgroundColors);
                }
                getIntent().putExtra("","");
            }
        });
        saveBackgroundColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editingText.setText("You are now edditing the Background Color");
                tB = 1;
            }
        });
        saveTextColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editingText.setText("You are now edditing the Text Color");
                tB = 0;
            }
        });
        Button returnButton = (Button) findViewById(R.id.returnButton);
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
