package com.myapplicationdev.android.p04_revisionnotes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

public class SongAdapter extends ArrayAdapter<Songs> {
    Context context;
    ArrayList<Songs> songs;
    int resource;
    TextView tvSongTitle, tvSinger, tvYear;
    ImageView iv1, iv2, iv3, iv4, iv5;

    public SongAdapter(Context context, int resource, ArrayList<Songs> songs) {
        super(context, resource, songs);
        this.context = context;
        this.songs = songs;
        this.resource = resource;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(resource, parent, false);

        //Match the UI components with Java variables
        tvSongTitle = rowView.findViewById(R.id.tvSongTitle);
        tvSinger = rowView.findViewById(R.id.tvSinger);
        tvYear = rowView.findViewById(R.id.tvYear);

        iv1 = rowView.findViewById(R.id.imageView1star);
        iv2 = rowView.findViewById(R.id.imageView2star);
        iv3 = rowView.findViewById(R.id.imageView3star);
        iv4 = rowView.findViewById(R.id.imageView4star);
        iv5 = rowView.findViewById(R.id.imageView5star);
        Songs song = songs.get(position);

        if (song.getStars() == 5) {
            iv5.setImageResource(android.R.drawable.btn_star_big_on);
            iv4.setImageResource(android.R.drawable.btn_star_big_on);
            iv3.setImageResource(android.R.drawable.btn_star_big_on);
            iv2.setImageResource(android.R.drawable.btn_star_big_on);
            iv1.setImageResource(android.R.drawable.btn_star_big_on);
        } else if (song.getStars() == 4) {
            iv4.setImageResource(android.R.drawable.btn_star_big_on);
            iv3.setImageResource(android.R.drawable.btn_star_big_on);
            iv2.setImageResource(android.R.drawable.btn_star_big_on);
            iv1.setImageResource(android.R.drawable.btn_star_big_on);
        } else if (song.getStars() == 3) {
            iv3.setImageResource(android.R.drawable.btn_star_big_on);
            iv2.setImageResource(android.R.drawable.btn_star_big_on);
            iv1.setImageResource(android.R.drawable.btn_star_big_on);
        } else if (song.getStars() == 2) {
            iv2.setImageResource(android.R.drawable.btn_star_big_on);
            iv1.setImageResource(android.R.drawable.btn_star_big_on);
        } else if (song.getStars() == 1) {
            iv1.setImageResource(android.R.drawable.btn_star_big_on);
        }

        tvSongTitle.setText(song.getTitle() + "");
        tvSinger.setText(song.getSingers() + "");
        tvYear.setText(song.getYear() + "");

        return rowView;
    }
}
