package com.example.laurelborggreve_pset2;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;


public class FillInPlaceholders extends AppCompatActivity {

    // Declare fields
    String typeOfWord;
    Story story;
    TextView wordsLeft, wordDescription, stimulation;
    EditText wordFillIn;

    // Method to create new story if bundle is empty or restore story if the bundle is not empty
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fill_in_placeholders);

        if (savedInstanceState == null) {
            Intent intent = getIntent();
            story = (Story) intent.getSerializableExtra("retrievedStory");
            GetRightLayout();
        }
        else {
            story = (Story) savedInstanceState.getSerializable("story");
            GetRightLayout();
        }
    }

    // Method to save the story in a bundle
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("story", story);
    }

    // Set layout for the 'Fill in Placeholders Activity'
    public void GetRightLayout() {
        int Words_Left = story.getPlaceholderRemainingCount();
        wordsLeft = findViewById(R.id.words_left);
        wordsLeft.setText(Words_Left + " word(s) left");

        wordFillIn = findViewById(R.id.word_fill_in);

        typeOfWord = story.getNextPlaceholder();
        wordDescription = findViewById(R.id.word_description);
        wordDescription.setText("please type a/an " + typeOfWord);
    }

    // Method to check if all words are filled in
    // If not, ask for new input, otherwise go to next activity
    public void OkClicked(View view) {
        story.fillInPlaceholder(wordFillIn.getText().toString());

        if (story.getPlaceholderRemainingCount() != 0) {
            wordsLeft.setText("");
            wordFillIn.setText("");
            wordDescription.setText("");

            // EXTRA for improving project: set stimulation textview (Great, keep going)!
            stimulation = findViewById(R.id.stimulation);
            stimulation.setVisibility(View.VISIBLE);
            GetRightLayout();
        }
        else {
            Intent intent = new Intent(this, CompletedStory.class);
            intent.putExtra("completeStory", story);
            startActivity(intent);
            finish();
        }
    }
}
