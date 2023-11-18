package com.example.mediaappmusic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MusicListActivity extends AppCompatActivity {
    ListView lvMusicList;

    List<Song> list = addSong();

    public List<Song> getList() {
        return list;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_list);
        lvMusicList = findViewById(R.id.lvMusicList);
        CustomListviewAdapter customListviewAdapter = new CustomListviewAdapter(addSong());
        lvMusicList.setAdapter(customListviewAdapter);
        customListviewAdapter.notifyDataSetChanged();
        lvMusicList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MusicListActivity.this, MainActivity.class);
                intent.putExtra("songPosition", position);
                startActivity(intent);
            }
        });
    }
    private List<Song> addSong() {
        ArrayList<Song> arraySong = new ArrayList<>();
        arraySong.add(new Song("Ánh Sao Và Bầu Trời", "T.R.I, Cá", R.raw.anh_sao_va_bau_troi, R.drawable.anh_sao_va_bau_troi_img));
        arraySong.add(new Song("Mây (Gió 2)", "JanK, Sỹ Tây, N2L", R.raw.may, R.drawable.may_img));
        arraySong.add(new Song("Ngõ Chạm", "BigDaddy, Emily", R.raw.ngo_cham, R.drawable.ngo_cham_img));
        arraySong.add(new Song("Răng Khôn", "Phí Phương Anh, RIN9", R.raw.rang_khon, R.drawable.rang_khon_img));
        arraySong.add(new Song("Sau Cơn Mưa", "CoolKid, Quang Anh Rhyder", R.raw.sau_con_mua, R.drawable.sau_con_mua_img));
        return arraySong;
    }
}