package my.edu.utar.individualassignment;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.media.MediaPlayer;
import com.google.android.material.snackbar.Snackbar;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
public class order extends AppCompatActivity {
    private List<Button> btnNum;
    private List<Integer> num;
    private List<Integer> input;
    private boolean asc;
    private MediaPlayer background;
    private MediaPlayer CorrectEffect;
    private MediaPlayer WrongEffect;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);


        Button btn1 = findViewById(R.id.num1Button);
        Button btn2 = findViewById(R.id.num2Button);
        Button btn3 = findViewById(R.id.num3Button);
        Button btn4 = findViewById(R.id.num4Button);
        Button btn5 = findViewById(R.id.num5Button);
        Button btn6 = findViewById(R.id.num6Button);
        Button backBtn = findViewById(R.id.backButton);
        Button undoBtn = findViewById(R.id.undoButton);

        background = MediaPlayer.create(this, R.raw.orderbackground);
        background.setLooping(true); // Loop the background music
        background.start();

        CorrectEffect = MediaPlayer.create(this, R.raw.correct);
        WrongEffect = MediaPlayer.create(this, R.raw.wrong);

        btnNum = new ArrayList<>();
        btnNum.add(btn1);
        btnNum.add(btn2);
        btnNum.add(btn3);
        btnNum.add(btn4);
        btnNum.add(btn5);
        btnNum.add(btn6);

        num = new ArrayList<>();
        input = new ArrayList<>();

        generateRandomNumbers();

        for (final Button button : btnNum) {
            button.setOnClickListener(v -> handleButtonClick(button));
        }

        Button confirmButton = findViewById(R.id.confirmButton);
        // Set click listener for each number button
        for (final Button button : btnNum) {
            button.setOnClickListener(v -> handleButtonClick(button));
        }

        // Set click listener for the confirm button
        confirmButton.setOnClickListener(v -> {
            if (input.size() == 6) {
                // Handle confirm button click when all numbers are input
                handleConfirm();
            } else {
                Snackbar.make(findViewById(android.R.id.content), "Please select all 6 numbers!!", Snackbar.LENGTH_SHORT).show();
            }
        });

        backBtn.setOnClickListener(v -> {
            Intent intent = new Intent(order.this, MainActivity.class);
            startActivity(intent);
            background.release();
            finish();
        });


        undoBtn.setOnClickListener(v -> undoLastInput());

        String order = asc ? "ascending" : "descending";
        String initialInstruction = "Order the numbers in " + order + " order";
        ((TextView) findViewById(R.id.instrucTextView)).setText(initialInstruction);
    }

    private void generateRandomNumbers() {
        Random random = new Random();
        num.clear();

        // Generate unique random numbers
        while (num.size() < 6) {
            int randomNumber = random.nextInt(100); // Generate a random number between 0 and 99
            if (!num.contains(randomNumber)) {
                num.add(randomNumber);
            }
        }

        // Shuffle the numbers to scramble them
        Collections.shuffle(num);

        // Decide whether to order in ascending or descending order
        asc = random.nextBoolean();

        // Set numbers to buttons
        for (int i = 0; i < btnNum.size(); i++) {
            Button button = btnNum.get(i);
            button.setText(String.valueOf(num.get(i)));
            button.setVisibility(View.VISIBLE); // Make sure all buttons are visible
        }
    }

    private void handleButtonClick(Button button) {
        if (input.size() < 6) {
            int number = Integer.parseInt(button.getText().toString());
            input.add(number);
            button.setVisibility(View.INVISIBLE); // Hide the button after selecting the number
            updateInstruction();
        }
    }

    private void handleConfirm() {
        // Compare userInput with the correct order of numbers
        List<Integer> correctOrder = new ArrayList<>(num);

        // Sort the correct order according to the chosen order (ascending or descending)
        if (!asc) {
            correctOrder.sort(Collections.reverseOrder());
        } else {
            Collections.sort(correctOrder);
        }

        // Check if userInput matches the correct order
        if (input.equals(correctOrder)) {
            // User input is correct
            Snackbar.make(findViewById(android.R.id.content), "Correct!", Snackbar.LENGTH_SHORT).show();
            playSoundEffect(CorrectEffect);
        } else {
            // User input is incorrect
            Snackbar.make(findViewById(android.R.id.content), "Incorrect! PLease try again", Snackbar.LENGTH_SHORT).show();
            playSoundEffect(WrongEffect);
        }

        // Reset userInput and display buttons again
        input.clear();
        generateRandomNumbers();
        updateInstruction();
    }

    private void playSoundEffect(MediaPlayer mediaPlayer) {
        if (mediaPlayer != null) {
            mediaPlayer.seekTo(0); // Rewind the sound to the beginning
            mediaPlayer.start(); // Start playing the sound effect
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (CorrectEffect != null) {
            CorrectEffect.release(); // Release the MediaPlayer resources
        }
        if (WrongEffect != null) {
            WrongEffect.release(); // Release the MediaPlayer resources
        }
        if (background != null) {
            background.release();
            background = null;
        }
    }

    private void undoLastInput() {
        if (!input.isEmpty()) {
            int lastNumber = input.remove(input.size() - 1);
            for (Button button : btnNum) {
                if (button.getText().toString().equals(String.valueOf(lastNumber))) {
                    button.setVisibility(View.VISIBLE); // Show the button again
                    break; // Assuming each number appears only once
                }
            }
            updateInstruction();
        } else {
            Toast.makeText(this, "No recent input to undo", Toast.LENGTH_SHORT).show();
        }
    }

    private void updateInstruction() {
        StringBuilder builder = new StringBuilder();
        for (int num : input) {
            builder.append(num).append(", ");
        }
        String userNumbers = builder.toString();
        if (userNumbers.endsWith(", ")) {
            userNumbers = userNumbers.substring(0, userNumbers.length() - 2); // Remove the trailing comma
        }
        String order = asc ? "ascending" : "descending";
        String instruction = "Order the numbers in " + order + " order\n" + userNumbers;
        ((TextView) findViewById(R.id.instrucTextView)).setText(instruction);
    }
}