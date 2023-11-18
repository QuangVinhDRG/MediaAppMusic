package com.example.mediaappmusic;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class CustomListviewAdapter extends BaseAdapter {
    List<Song> list;

    public CustomListviewAdapter(List<Song> list) {
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.music_item, parent, false);
        TextView tvSongName = view.findViewById(R.id.tvSongName);
        TextView tvArtist = view.findViewById(R.id.tvSongArtist);
        ImageView ivSongImage = view.findViewById(R.id.ivSongImage);
        Song song = list.get(position);
        tvSongName.setText(song.getSongName());
        tvArtist.setText(song.getArtist());
        ivSongImage.setImageResource(song.getImageResource());
        return view;
    }
}
