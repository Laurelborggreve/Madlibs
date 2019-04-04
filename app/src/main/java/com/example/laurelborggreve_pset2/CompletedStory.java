package com.example.laurelborggreve_pset2;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.InputStream;

public class CompletedStory extends AppCompatActivity {

    Story completeStory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_completed_story);

        Intent intent = getIntent();
        completeStory = (Story) intent.getSerializableExtra("completeStory");
        String storyId = completeStory.getStoryId();

        TextView finalStory = findViewById(R.id.final_story);
        finalStory.setText(Html.fromHtml(completeStory.toString(), 0));

        // EXTRA vor improving the app: set an image for each story
        ImageView storyImage = findViewById(R.id.story_image);
        Log.d("storyId", storyId);

        if (storyId.equals("simple")) {
            storyImage.setImageResource(getResources().getIdentifier("madlibs", "drawable", getPackageName()));
        }
        else if (storyId.equals("tarzan")) {
            storyImage.setImageResource(getResources().getIdentifier("tarzan", "drawable", getPackageName()));
        }
        else if (storyId.equals("university")) {
            storyImage.setImageResource(getResources().getIdentifier("university", "drawable", getPackageName()));
        }
        else if (storyId.equals("clothes")) {
            storyImage.setImageResource(getResources().getIdentifier("clothes", "drawable", getPackageName()));
        }
        else {
            storyImage.setImageResource(getResources().getIdentifier("dance", "drawable", getPackageName()));
            }
        }

    // Go back to 'Welcome Screen'
    public void MakeAnotherStoryClicked(View view) {
        Intent intent = new Intent(CompletedStory.this, WelcomeScreen.class);
        startActivity(intent);
    }
}

