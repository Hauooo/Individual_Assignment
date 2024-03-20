package my.edu.utar.individualassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private MediaPlayer background;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        background = MediaPlayer.create(this, R.raw.background);
        background.setLooping(true); // Loop the background music
        background.start();


        //Get the button input
        Button compare = (Button) findViewById(R.id.compareb);
        {
            compare.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, Compare.class);

                    startActivity(intent);
                    background.release();
                }
            });
        }

        Button order = (Button) findViewById(R.id.orderb);
        {
            order.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, order.class);

                    startActivity(intent);
                    background.release();
                }
            });
        }

        Button compose = (Button) findViewById(R.id.composeb);
        {
            compose.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, compose.class);

                    startActivity(intent);
                    background.release();
                }
            });
        }


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Release the MediaPlayer when the activity is destroyed
        if (background != null) {
            background.release();
            background = null;
        }
    }

}