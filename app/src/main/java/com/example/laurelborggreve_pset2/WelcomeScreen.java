package com.example.laurelborggreve_pset2;

import android.content.Intent;
import android.renderscript.ScriptGroup;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.io.InputStream;

public class WelcomeScreen extends AppCompatActivity {

    Story story;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);
    }

    // Obtain story that is clicked on
    public void StoryClicked(View view){
        Log.d("story", "GetStartedButton");
        InputStream inputStream = getResources().openRawResource(R.raw.madlib0_simple);
        String storyId = "simple";

        int id = view.getId();
        switch(id) {
            case R.id.tarzan:
                inputStream = getResources().openRawResource(R.raw.madlib1_tarzan);
                storyId = "tarzan";
                break;
            case R.id.university:
                inputStream = getResources().openRawResource(R.raw.madlib2_university);
                storyId = "university";
                break;
            case R.id.clothes:
                inputStream = getResources().openRawResource(R.raw.madlib3_clothes);
                storyId = "clothes";
                break;
            case R.id.dance:
                inputStream = getResources().openRawResource(R.raw.madlib4_dance);
                storyId = "dance";
                break;
        }

        // Set story and go to next activity
        story = new Story(inputStream);
        story.setStoryId(storyId);
        Intent intent = new Intent(WelcomeScreen.this, FillInPlaceholders.class);
        intent.putExtra("retrievedStory", story);
        startActivity(intent);
    }
}
