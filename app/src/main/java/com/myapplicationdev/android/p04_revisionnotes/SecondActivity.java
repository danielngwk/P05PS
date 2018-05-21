package com.myapplicationdev.android.p04_revisionnotes;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {
    ArrayList<Songs> al;
    ListView lv;
    Button btnShowFiveStar;
    ArrayAdapter aa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        btnShowFiveStar = (Button) findViewById(R.id.btnAll);
        lv = (ListView) findViewById(R.id.lv);
        al = new ArrayList<Songs>();

        DBHelper db = new DBHelper(SecondActivity.this);
        al.clear();
        al.addAll(db.getAllSongs());
        db.close();

        aa = new SongAdapter(SecondActivity.this, R.layout.row, al);
        lv.setAdapter(aa);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(SecondActivity.this, Edit.class);

                Songs data = al.get(position);

                i.putExtra("data", (Parcelable) data);
                startActivityForResult(i, 9);

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == 9) {
            lv = (ListView) findViewById(R.id.lv);
            al = new ArrayList<Songs>();
            DBHelper dbh = new DBHelper(SecondActivity.this);
            al.clear();
            al.addAll(dbh.getAllSongs());
            dbh.close();
            aa = new SongAdapter(this, R.layout.row, al);

            lv.setAdapter(aa);
        }
    }


}
