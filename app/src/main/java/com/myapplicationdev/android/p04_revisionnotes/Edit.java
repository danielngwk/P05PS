package com.myapplicationdev.android.p04_revisionnotes;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class Edit extends AppCompatActivity {
    EditText tvID, etTitle, etSingers, etYear;
    Button btnUpdate, btnDelete, btnCancel;
    RadioGroup rgStars;
    RadioButton rb, rb1, rb2, rb3, rb4, rb5;
    Songs data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        tvID = (EditText) findViewById(R.id.tvID);
        etTitle = (EditText) findViewById(R.id.etTitle);
        etSingers = (EditText) findViewById(R.id.etSingers);
        etYear = (EditText) findViewById(R.id.etYear);
        rgStars = (RadioGroup) findViewById(R.id.rgStars);
        rb1 = (RadioButton) findViewById(R.id.radio1);
        rb2 = (RadioButton) findViewById(R.id.radio2);
        rb3 = (RadioButton) findViewById(R.id.radio3);
        rb4 = (RadioButton) findViewById(R.id.radio4);
        rb5 = (RadioButton) findViewById(R.id.radio5);
        btnUpdate = (Button) findViewById(R.id.btnUpdate);
        btnCancel = (Button) findViewById(R.id.btnCancel);
        btnDelete = (Button) findViewById(R.id.btnDelete);

        Intent i = getIntent();
        data = (Songs) i.getSerializableExtra("data");

        tvID.setText(String.valueOf(data.get_id()));
        etTitle.setText(data.getTitle());
        etSingers.setText(data.getSingers());
        etYear.setText(String.valueOf(data.getYear()));

        if (data.getStars() == 5) {
            rb5.setChecked(true);
        } else if (data.getStars() == 4) {
            rb4.setChecked(true);
        } else if (data.getStars() == 3) {
            rb3.setChecked(true);
        } else if (data.getStars() == 2) {
            rb2.setChecked(true);
        } else {
            rb1.setChecked(true);
        }

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(Edit.this);

                int selectedButtonId = rgStars.getCheckedRadioButtonId();
                rb = findViewById(selectedButtonId);

                data.setTitle(etTitle.getText().toString());
                data.setSinger(etSingers.getText().toString());
                data.setYear(Integer.parseInt(etYear.getText().toString()));
                data.setStars(Integer.parseInt(rb.getText().toString()));
                dbh.updateSong(data);
                dbh.close();
                setResult(RESULT_OK);
                finish();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(Edit.this);
                dbh.deleteSong(data.get_id());
                dbh.close();
                setResult(RESULT_OK);
                finish();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
