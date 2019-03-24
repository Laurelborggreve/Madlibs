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

    private int [][] stories_total = {{R.id.simple, R.id.tarzan, R.id.university, R.id.clothes, R.id.dance}};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);
    }

    public void StoryClicked(View view){
        Log.d("story", "GetStartedButton");
        InputStream inputstream = getResources().openRawResource(R.raw.madlib0_simple);

        int id = view.getId();
        switch(id) {
            case R.id.tarzan:
                inputstream = getResources().openRawResource(R.raw.madlib1_tarzan);
                break;
            case R.id.university:
                inputstream = getResources().openRawResource(R.raw.madlib2_university);
                break;
            case R.id.clothes:
                inputstream = getResources().openRawResource(R.raw.madlib3_clothes);
                break;
            case R.id.dance:
                inputstream = getResources().openRawResource(R.raw.madlib4_dance);
                break;
        }

        story = new Story(inputstream);
        Intent intent = new Intent(WelcomeScreen.this, FillInPlaceholders.class);
        intent.putExtra("retrievedstory", story);
        startActivity(intent);
    }

}
