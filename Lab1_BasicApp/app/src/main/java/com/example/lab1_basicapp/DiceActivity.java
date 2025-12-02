package com.example.lab1_basicapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.Random;
import android.content.Intent;

public class DiceActivity extends AppCompatActivity {

    private TextView txtResult;
    private ImageView imgDice;
    private Button btnRoll, btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dice);

        txtResult = findViewById(R.id.txtResult);
        imgDice = findViewById(R.id.imgDice);
        btnRoll = findViewById(R.id.btnRoll);
        btnBack = findViewById(R.id.btnBack);

        btnRoll.setOnClickListener(v -> rollDice());
        btnBack.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        });
    }

    private void rollDice() {
        Random random = new Random();
        int randomNumber = random.nextInt(6) + 1;
        txtResult.setText("Bạn tung được số: " + randomNumber);

        int diceImage;
        switch (randomNumber) {
            case 1: diceImage = R.drawable.dice1; break;
            case 2: diceImage = R.drawable.dice2; break;
            case 3: diceImage = R.drawable.dice3; break;
            case 4: diceImage = R.drawable.dice4; break;
            case 5: diceImage = R.drawable.dice5; break;
            default: diceImage = R.drawable.dice6; break;
        }

        imgDice.setImageResource(diceImage);
    }
}
