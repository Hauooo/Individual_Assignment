package my.edu.utar.individualassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Compare extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compare);

        // declare a new int variable
        int BigOrSmall;


        // Random either Bigger or Smaller
        int random = (int) (Math.random() * 2);
        if (random == 0) {
            // Bigger
            BigOrSmall = 1;
            //Replace CompareTitle text with "Bigger"
            TextView CompareTitle = findViewById(R.id.QuestionText);
            CompareTitle.setText(R.string.which_number_is_larger);

        }
        else {
            // Smaller
            BigOrSmall = 0;
            //Replace CompareTitle text with "Smaller"
            TextView CompareTitle = findViewById(R.id.QuestionText);
            CompareTitle.setText(R.string.which_number_is_smaller);
        }

        // Get the button
        Button button_left = (Button) findViewById(R.id.ansbt1);
        Button button_right = (Button) findViewById(R.id.ansbt2);

        // Create 2 random integers and set text to the buttons
        int randomleft = (int) (Math.random() * 100);
        int randomright = (int) (Math.random() * 100);
        button_left.setText(String.valueOf(randomleft));
        button_right.setText(String.valueOf(randomright));

        // Create a button listener
        button_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create an intent to start the compare activity
                if (BigOrSmall == 1 /*Bigger*/) {
                    if (randomleft > randomright){
                        Toast.makeText(Compare.this, "Correct!", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(Compare.this, "Wrong!", Toast.LENGTH_SHORT).show();
                    }
                }
                else /*Smaller*/{
                    if (randomleft < randomright){
                        Toast.makeText(Compare.this, "Correct!", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(Compare.this, "Wrong!", Toast.LENGTH_SHORT).show();
                    }
                }
                Intent intent = new Intent(Compare.this, Compare.class);
                // Start the activity
                startActivity(intent);
            }
        });

        // Create a button listener
        button_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create an intent to start the compare activity
                if (BigOrSmall == 1 /*Bigger*/) {
                    if (randomright > randomleft){
                        Toast.makeText(Compare.this, "Correct!", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(Compare.this, "Wrong!", Toast.LENGTH_SHORT).show();
                    }
                }
                else /*Smaller*/{
                    if (randomright < randomleft){
                        Toast.makeText(Compare.this, "Correct!", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(Compare.this, "Wrong!", Toast.LENGTH_SHORT).show();
                    }
                }
                Intent intent = new Intent(Compare.this, Compare.class);
                // Start the activity
                startActivity(intent);
            }
        });

        Button back = (Button) findViewById(R.id.backBt);
        {
            back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Compare.this, MainActivity.class);

                    startActivity(intent);
                }
            });
        }
    }



}