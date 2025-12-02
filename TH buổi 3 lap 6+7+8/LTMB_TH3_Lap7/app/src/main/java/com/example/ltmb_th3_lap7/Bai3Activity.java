package com.example.ltmb_th3_lap7;
import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.*;
import android.view.View;
public class Bai3Activity extends AppCompatActivity {

    SeekBar seekR, seekG, seekB;
    View viewRGB, viewCMY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bai3);

        seekR = findViewById(R.id.seekR);
        seekG = findViewById(R.id.seekG);
        seekB = findViewById(R.id.seekB);

        viewRGB = findViewById(R.id.viewRGB);
        viewCMY = findViewById(R.id.viewCMY);
        Button btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> finish());


        SeekBar.OnSeekBarChangeListener listener = new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int value, boolean b) {

                int R = seekR.getProgress();
                int G = seekG.getProgress();
                int B = seekB.getProgress();

                int C = 255 - R;
                int M = 255 - G;
                int Y = 255 - B;

                viewRGB.setBackgroundColor(Color.rgb(R, G, B));
                viewCMY.setBackgroundColor(Color.rgb(C, M, Y));
            }

            public void onStartTrackingTouch(SeekBar seekBar) {}
            public void onStopTrackingTouch(SeekBar seekBar) {}
        };

        seekR.setOnSeekBarChangeListener(listener);
        seekG.setOnSeekBarChangeListener(listener);
        seekB.setOnSeekBarChangeListener(listener);
    }
}
