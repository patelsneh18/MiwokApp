package android.example.miwokapp;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class ColorsActivity extends AppCompatActivity {
    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colors);

        ArrayList<Word> words=new ArrayList<Word>();
        words.add(new Word("weṭeṭṭi", "red",R.drawable.color_red,R.raw.color_red));
        words.add(new Word("chokokki", "green",R.drawable.color_green,R.raw.color_green));
        words.add(new Word("ṭakaakki", "brown",R.drawable.color_brown,R.raw.color_brown));
        words.add(new Word("ṭopoppi", "gray",R.drawable.color_gray,R.raw.color_gray));
        words.add(new Word("kululli", "black",R.drawable.color_black,R.raw.color_black));
        words.add(new Word("temmokka", "white",R.drawable.color_white,R.raw.color_white));
        words.add(new Word("kenekaku", "dusty yellow",R.drawable.color_dusty_yellow,R.raw.color_dusty_yellow));
        words.add(new Word("kawinta", "mustard yellow",R.drawable.color_mustard_yellow,R.raw.color_mustard_yellow));

        WordAdapter adapter = new WordAdapter(this,words,R.color.category_colors);


        ListView listView = findViewById(R.id.listColor);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Word word = words.get(position);
                mediaPlayer = MediaPlayer.create(ColorsActivity.this,word.getAudioResourceId());
                mediaPlayer.start();
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        releaseMediaPlayer();
                    }
                });
            }
        });
    }

    @Override
    public void onStop(){
        super.onStop();
        releaseMediaPlayer();

    }
    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mediaPlayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mediaPlayer.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mediaPlayer = null;
        }
    }
}