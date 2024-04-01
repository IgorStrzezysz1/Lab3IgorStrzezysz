package com.example.naukajavy;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.naukajavy.R;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView infoTextView;
    private EditText guessEditText;
    private Button guessButton;
    private int randomNumber;
    private int attempts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        infoTextView = findViewById(R.id.infoTextView);
        guessEditText = findViewById(R.id.guessEditText);
        guessButton = findViewById(R.id.guessButton);

        generateRandomNumber(); // Losowanie liczby

        guessButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkGuess();
            }
        });
    }

    private void generateRandomNumber() {
        Random random = new Random();
        randomNumber = random.nextInt(10) + 1; // Losowanie liczby z zakresu 1-10
        attempts = 0; // Resetowanie licznika prób
    }

    private void checkGuess() {
        String guessText = guessEditText.getText().toString();
        if (!guessText.isEmpty()) {
            int guess = Integer.parseInt(guessText);
            attempts++;

            if (guess < randomNumber) {
                infoTextView.setText("Wartość zbyt mała. Spróbuj ponownie.");
            } else if (guess > randomNumber) {
                infoTextView.setText("Wartość zbyt duża. Spróbuj ponownie.");
            } else {
                infoTextView.setText("Brawo! Trafiłeś w liczbę " + randomNumber + " za " + attempts + " razem.");
                guessButton.setEnabled(false); // Wyłącz przycisk po odgadnięciu liczby
            }
        } else {
            infoTextView.setText("Wprowadź liczbę.");
        }
        guessEditText.setText(""); // Wyczyść pole wprowadzania po każdej próbie
    }
}
