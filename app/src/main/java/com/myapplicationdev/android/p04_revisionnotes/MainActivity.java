package com.myapplicationdev.android.p04_revisionnotes;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    EditText etTitle, etSingers, etYear;
    RadioGroup rgStars;
    RadioButton rb;
    Button btnInsert, btnList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etTitle = (EditText) findViewById(R.id.etTitle);
        etSingers = (EditText) findViewById(R.id.etSingers);
        etYear = (EditText) findViewById(R.id.etYear);
        btnInsert = (Button) findViewById(R.id.btnInsert);
        btnList = (Button) findViewById(R.id.btnList);
        rgStars = (RadioGroup) findViewById(R.id.rgStars);

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String songTitle = etTitle.getText().toString();
                String singers = etSingers.getText().toString();
                String year = etYear.getText().toString();
                int selectedButtonId = rgStars.getCheckedRadioButtonId();
                rb = findViewById(selectedButtonId);

                if (!songTitle.equals("") || !singers.equals("") || !year.equals("")) {

                    DBHelper db = new DBHelper(MainActivity.this);
                    db.getWritableDatabase();
                    db.insertSong(songTitle, singers
                            , Integer.parseInt(year)
                            , Integer.parseInt(rb.getText().toString()));

                    Toast.makeText(MainActivity.this, "Song inserted", Toast.LENGTH_LONG).show();
                    etTitle.setText("");
                    etSingers.setText("");
                    etYear.setText("");


                } else {
                    Toast.makeText(MainActivity.this, "Song failed to insert", Toast.LENGTH_LONG).show();
                }
            }
        });

        btnList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent i = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(i);
            }
        });
    }

}
