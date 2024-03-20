package my.edu.utar.individualassignment;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.media.MediaPlayer;
import com.google.android.material.snackbar.Snackbar;
public class Compare extends AppCompatActivity {
    private int BigOrSmall;
    private Button button_left;
    private Button button_right;
    private MediaPlayer CorrectEffect;
    private MediaPlayer WrongEffect;
    private MediaPlayer background;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compare);

        CorrectEffect = MediaPlayer.create(this, R.raw.correct);
        WrongEffect = MediaPlayer.create(this, R.raw.wrong);

        background = MediaPlayer.create(this, R.raw.comparebackground);
        background.setLooping(true); // Loop the background music
        background.start();
        // Initialize buttons
        button_left = findViewById(R.id.ansbt1);
        button_right = findViewById(R.id.ansbt2);
        // Generate initial question
        generateQuestion();
        // Set onClickListener for button_left
        button_left.setOnClickListener(view -> {
            checkAnswer(button_left);
        });
        // Set onClickListener for button_right
        button_right.setOnClickListener(view -> {
            checkAnswer(button_right);
        });
        // Set onClickListener for back button
        Button back = findViewById(R.id.backBt);
        back.setOnClickListener(v -> {
            Intent intent = new Intent(Compare.this, MainActivity.class);
            startActivity(intent);
            background.release();
            finish();
            // Close the current activity and return to the previous one
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
        if (randomleft == randomright){
            generateQuestion();
        }
        else;
    }

    private void checkAnswer(Button selectedButton) {
        int leftNumber = Integer.parseInt(button_left.getText().toString());
        int rightNumber = Integer.parseInt(button_right.getText().toString());

        boolean correct = (BigOrSmall == 1 && leftNumber > rightNumber) || (BigOrSmall == 0 && leftNumber < rightNumber);

        if (selectedButton.getId() == R.id.ansbt1 && correct) {
            Snackbar.make(findViewById(android.R.id.content), "Correct!", Snackbar.LENGTH_SHORT).show();
            playSoundEffect(CorrectEffect);
        } else if (selectedButton.getId() == R.id.ansbt2 && !correct) {
            Snackbar.make(findViewById(android.R.id.content), "Correct!", Snackbar.LENGTH_SHORT).show();
            playSoundEffect(CorrectEffect);
        } else {
            Snackbar.make(findViewById(android.R.id.content), "Incorrect!", Snackbar.LENGTH_SHORT).show();
            playSoundEffect(WrongEffect);
        }

        // Generate a new question
        generateQuestion();
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

}
