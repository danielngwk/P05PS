package com.myapplicationdev.android.p04_revisionnotes;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText etNote;
    Button btnInsert, btnShowList;
    RadioGroup rg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNote = (EditText) findViewById(R.id.editTextNote);
        btnInsert = (Button) findViewById(R.id.buttonInsertNote);
        btnShowList = (Button) findViewById(R.id.buttonShowList);
        rg = (RadioGroup) findViewById(R.id.radioGroupStars);

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String note = etNote.getText().toString().trim();
                if (note.length() == 0)
                    return;

                DBHelper dbh = new DBHelper(MainActivity.this);
                if (dbh.isExistingNote(etNote.getText().toString())) {
                    Toast.makeText(MainActivity.this, "Same note already exists", Toast.LENGTH_LONG).show();
                } else {
                    String data = etNote.getText().toString();
                    int stars = getStars();
                    dbh.insertNote(data, stars);
                    Toast.makeText(MainActivity.this, "Inserted", Toast.LENGTH_LONG).show();
                }
            }
        });

        btnShowList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent i = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(i);
            }
        });
    }

    private int getStars() {
        int stars = 1;
        switch (rg.getCheckedRadioButtonId()) {
            case R.id.radio1:
                stars = 1;
                break;
            case R.id.radio2:
                stars = 2;
                break;
            case R.id.radio3:
                stars = 3;
                break;
            case R.id.radio4:
                stars = 4;
                break;
            case R.id.radio5:
                stars = 5;
                break;
        }
        return stars;
    }
}
