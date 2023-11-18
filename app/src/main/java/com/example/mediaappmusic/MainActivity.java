package com.example.mediaappmusic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    TextView txtTitle, txtTimeSong, txtTimeTotal, txtArtist;
    SeekBar skSong;
    ImageButton btnPrev, btnRewind, btnPlay, btnForward, btnNext;
    ImageView imgSong;
    ArrayList<Song> arraySong;
    MediaPlayer mediaPlayer;
    Animation animation;
    int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewMapping();
        addSong();
        initMediaPlayer();
        SetTimeTotal();
        UpdateTimeSong();
        Intent intent = getIntent();
        position = intent.getIntExtra("songPosition", 0);
        imgSong.setImageResource(arraySong.get(position).getImageResource());
        animation = AnimationUtils.loadAnimation(this, R.anim.disc_rotate);
        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                    btnPlay.setImageResource(R.drawable.play_button_icon);
                    imgSong.clearAnimation();
                } else {
                    mediaPlayer.start();
                    btnPlay.setImageResource(R.drawable.pause);
                    imgSong.startAnimation(animation);
                }
            }
        });
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                position++;
                if (position > arraySong.size() - 1) {
                    position = 0;
                }
                imgSong.setImageResource(arraySong.get(position).getImageResource());
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                    initMediaPlayer();
                    mediaPlayer.start();
                    imgSong.startAnimation(animation);
                } else {
                    initMediaPlayer();
                    imgSong.clearAnimation();
                }
                SetTimeTotal();
                mediaPlayer.seekTo(0);
            }
        });
        btnPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                position--;
                if (position < 0) {
                    position = arraySong.size() - 1;
                }
                imgSong.setImageResource(arraySong.get(position).getImageResource());
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                    initMediaPlayer();
                    mediaPlayer.start();
                    imgSong.startAnimation(animation);
                } else {
                    initMediaPlayer();
                    imgSong.clearAnimation();
                }
                SetTimeTotal();
                mediaPlayer.seekTo(0);
            }
        });
        skSong.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mediaPlayer.seekTo(skSong.getProgress());
            }
        });
    }

    private void UpdateTimeSong() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                SimpleDateFormat hourFormat = new SimpleDateFormat("mm:ss");
                txtTimeSong.setText(hourFormat.format(mediaPlayer.getCurrentPosition()));
                skSong.setProgress(mediaPlayer.getCurrentPosition());
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        position++;
                        if (position > arraySong.size() - 1) {
                            position = 0;
                        }
                        mediaPlayer.pause();
                        initMediaPlayer();
                        mediaPlayer.start();
                        SetTimeTotal();
                        imgSong.setImageResource(arraySong.get(position).getImageResource());
                        mediaPlayer.seekTo(0);
                    }
                });
                handler.postDelayed(this, 500);
            }
        }, 100);
    }

    private void SetTimeTotal() {
        SimpleDateFormat hourFormat = new SimpleDateFormat("mm:ss");
        txtTimeTotal.setText(hourFormat.format(mediaPlayer.getDuration()));
        skSong.setMax(mediaPlayer.getDuration());
    }

    private void initMediaPlayer() {
        mediaPlayer = MediaPlayer.create(MainActivity.this, arraySong.get(position).getFile());
        txtTitle.setText(arraySong.get(position).getSongName());
        txtArtist.setText(arraySong.get(position).getArtist());
    }

    private void addSong() {
        arraySong = new ArrayList<>();
        arraySong.add(new Song("Ánh Sao Và Bầu Trời", "T.R.I, Cá", R.raw.anh_sao_va_bau_troi, R.drawable.anh_sao_va_bau_troi_img));
        arraySong.add(new Song("Mây (Gió 2)", "JanK, Sỹ Tây, N2L", R.raw.may, R.drawable.may_img));
        arraySong.add(new Song("Ngõ Chạm", "BigDaddy, Emily", R.raw.ngo_cham, R.drawable.ngo_cham_img));
        arraySong.add(new Song("Răng Khôn", "Phí Phương Anh, RIN9", R.raw.rang_khon, R.drawable.rang_khon_img));
        arraySong.add(new Song("Sau Cơn Mưa", "CoolKid, Quang Anh Rhyder", R.raw.sau_con_mua, R.drawable.sau_con_mua_img));
    }

    private void viewMapping() {
        txtTitle = findViewById(R.id.tvTitle);
        txtTimeSong = findViewById(R.id.tvStartTime);
        txtTimeTotal = findViewById(R.id.tvEndTime);
        txtArtist = findViewById(R.id.tvArtist);
        skSong = findViewById(R.id.sbTimeline);
        btnPrev = findViewById(R.id.ibPrev);
        btnRewind = findViewById(R.id.ibRewind);
        btnPlay = findViewById(R.id.ibPlay);
        btnForward = findViewById(R.id.ibForward);
        btnNext = findViewById(R.id.ibNext);
        imgSong = findViewById(R.id.ivSong);
    }

}