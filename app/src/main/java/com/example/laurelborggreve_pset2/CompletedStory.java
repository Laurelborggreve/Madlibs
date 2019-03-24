package com.example.laurelborggreve_pset2;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.InputStream;

public class CompletedStory extends AppCompatActivity {

    Story complete_story;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_completed_story);

        Intent intent = getIntent();
        complete_story = (Story) intent.getSerializableExtra("complete_story");

        TextView finalstory = findViewById(R.id.FinalStory);
        finalstory.setText(Html.fromHtml(complete_story.toString(), 0));

        ImageView imageview = findViewById(R.id.storyimage);
        imageview.setVisibility(View.VISIBLE);
    }

    // Go back to 'Welcome Screen'
    public void MakeAnotherStoryClicked(View view) {
        Intent intent = new Intent(CompletedStory.this, WelcomeScreen.class);
        startActivity(intent);
    }
}
