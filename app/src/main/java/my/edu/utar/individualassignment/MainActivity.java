package my.edu.utar.individualassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Get the button input
        Button compare = (Button) findViewById(R.id.compareb);
        {
            compare.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, Compare.class);

                    startActivity(intent);
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
                }
            });
        }
    }



}