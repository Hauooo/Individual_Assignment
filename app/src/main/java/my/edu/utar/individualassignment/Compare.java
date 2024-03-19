package my.edu.utar.individualassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

public class Compare extends AppCompatActivity {

    private int BigOrSmall;
    private Button button_left;
    private Button button_right;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compare);

        // Initialize buttons
        button_left = findViewById(R.id.ansbt1);
        button_right = findViewById(R.id.ansbt2);

        // Generate initial question
        generateQuestion();

        // Set onClickListener for button_left
        button_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(button_left);
            }
        });

        // Set onClickListener for button_right
        button_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(button_right);
            }
        });

        // Set onClickListener for back button
        Button back = findViewById(R.id.backBt);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Close the current activity and return to the previous one
            }
        });
    }

    private void generateQuestion() {
        // Randomly decide if the question should be about finding a bigger or smaller number
        BigOrSmall = (int) (Math.random() * 2);

        // Set question text based on BigOrSmall value
        TextView CompareTitle = findViewById(R.id.QuestionText);
        CompareTitle.setText(BigOrSmall == 1 ? R.string.which_number_is_larger : R.string.which_number_is_smaller);

        // Generate random numbers and set text to buttons
        int randomleft = (int) (Math.random() * 100);
        int randomright = (int) (Math.random() * 100);
        button_left.setText(String.valueOf(randomleft));
        button_right.setText(String.valueOf(randomright));
    }

    private void checkAnswer(Button selectedButton) {
        int leftNumber = Integer.parseInt(button_left.getText().toString());
        int rightNumber = Integer.parseInt(button_right.getText().toString());

        boolean correct = false;

        if ((BigOrSmall == 1 && leftNumber > rightNumber) || (BigOrSmall == 0 && leftNumber < rightNumber)) {
            correct = true;
        }

        if (selectedButton.getId() == R.id.ansbt1 && correct) {
            Snackbar.make(findViewById(android.R.id.content), "Correct!", Snackbar.LENGTH_SHORT).show();
        } else if (selectedButton.getId() == R.id.ansbt2 && !correct) {
            Snackbar.make(findViewById(android.R.id.content), "Correct!", Snackbar.LENGTH_SHORT).show();
        } else {
            Snackbar.make(findViewById(android.R.id.content), "Incorrect!", Snackbar.LENGTH_SHORT).show();
        }

        // Generate a new question
        generateQuestion();
    }

}
