package com.example.laurelborggreve_pset2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;


public class FillInPlaceholders extends AppCompatActivity {

    // declare fields
    String Type_of_Word;
    Story story;
    TextView wordsleft, typeofword, stimulation;
    EditText wordfillin;

    // Method to create new story if bundle is empty
    // Method to restore story if the bundle is not empty
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fill_in_placeholders);

        if (savedInstanceState == null) {
            Intent intent = getIntent();
            story = (Story) intent.getSerializableExtra("retrievedstory");
            GetRightLayout();
        }
        else {
            story = (Story) savedInstanceState.getSerializable("story");
        }
    }

    // Method to save the story in a bundle
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("story", story);
    }


    public void GetRightLayout() {
        // Set LayOut for the 'Fill in Placeholders' Activity
        int Words_Left = story.getPlaceholderRemainingCount();
        wordsleft = findViewById(R.id.Words_Left);
        wordsleft.setText(Words_Left + " word(s) left");

        wordfillin = findViewById(R.id.Word_Fill_In);

        Type_of_Word = story.getNextPlaceholder();
        typeofword = findViewById(R.id.Word_Description);
        typeofword.setText("please type a/an " + Type_of_Word);

    }

    public void OkClicked(View view) {
        story.fillInPlaceholder(wordfillin.getText().toString());

        // Check if all words are filled in
        // If not, ask for new input, otherwise go to next activity
        if (story.getPlaceholderRemainingCount() != 0) {
            wordsleft.setText("");
            wordfillin.setText("");
            typeofword.setText("");
            stimulation = findViewById(R.id.Stimulation);
            stimulation.setVisibility(View.VISIBLE);
            GetRightLayout();
        }
        else {
            // Set image for each story category
            ImageView storyimage = findViewById(R.id.storyimage);
            int id = view.getId();
            switch(id) {
                case R.id.tarzan:
                    storyimage.setImageResource(getResources().getIdentifier("tarzan", "drawable", getPackageName()));
                    break;
                case R.id.university:
                    storyimage.setImageResource(getResources().getIdentifier("university", "drawable", getPackageName()));
                    break;
                case R.id.clothes:
                    storyimage.setImageResource(getResources().getIdentifier("tarzan", "drawable", getPackageName()));
                    break;
                case R.id.dance:
                    storyimage.setImageResource(getResources().getIdentifier("dance", "drawable", getPackageName()));
                    break;
            }

            Intent intent = new Intent(this, CompletedStory.class);
            intent.putExtra("complete_story", story);
            startActivity(intent);
            finish();
        }
    }

}
