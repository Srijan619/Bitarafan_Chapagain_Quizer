package fi.centria.bitarafan_chapagain_quizer;

import android.app.Activity;
import android.app.FragmentManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class QuizerActivity extends Activity
                implements AnswerFragment.IOnAnswerPicked
{
    final String TAG = "quizerTAGS";

    FragmentManager manager;
    PictureFragment picFrag;
    AnswerFragment  ansFrag;

    String          category    = null;
    List<String>    words       = null;
    String          answerWord  = null;
    List<String>    choices     = null;
    Map<String, Integer> wordsBasedOnCategory = null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quizer);
        getActionBar().setDisplayHomeAsUpEnabled(true);

        init_wordsResourceIds();
        manager = getFragmentManager();
        picFrag = (PictureFragment) manager.findFragmentById(R.id.picFrag);
        ansFrag = (AnswerFragment)  manager.findFragmentById(R.id.ansFrag);
        Bundle extra = getIntent().getExtras();
        category = extra.getString("category");
        if (category != null)   fillListFromResources(category);
        setNewQuestion();

    }

    /**
     * returns a list with 4 strings
     * */
    private void fillChoicesList(){
        choices = new ArrayList<>();
        String choice;
        while(choices.size() <= 4){
            choice = getRandomWord();
            if (!choices.contains(choice))
                choices.add(choice);
        }
    }

    private void setNewQuestion(){
        fillChoicesList();
        Log.d(TAG, "setNewQuestion: Choices made");

        answerWord = choices.get(new Random().nextInt(4));
        ansFrag.setAnswers(choices);
        int pic_ID = getResources().getIdentifier(category + "_" + answerWord,
                "drawable", getPackageName());
        Log.d(TAG, "setNewQuestion: fetched PIC_ID: " + pic_ID);
        if (pic_ID != 0)
            picFrag.changePicture(pic_ID);
    }

    private boolean checkAnswer(String rcvdAnsw){
        if (rcvdAnsw == answerWord) return true;

        return false;
    }

    @Override
    public void onAnswerPicked(String msg) {
        if(checkAnswer(msg)) Toast.makeText(this, "Right", Toast.LENGTH_SHORT).show();
        setNewQuestion();
    }

    private String getRandomWord(){
        return words.get(new Random().nextInt(words.size()));
    }

    /**
     * This method assigns identifier of string arrays found in string resource file
     * to the key defined by categories (key = category, Value = resource identifier)
     * */
    private void init_wordsResourceIds() {
        wordsBasedOnCategory = new HashMap<>();

        wordsBasedOnCategory.put("animals",
                getResources().getIdentifier("animals_array",
                        "array", getPackageName()));

        wordsBasedOnCategory.put("objects",
                getResources().getIdentifier("objects_array",
                        "array", getPackageName()));

        wordsBasedOnCategory.put("colors",
                getResources().getIdentifier("colors_array",
                        "string", getPackageName()));

        wordsBasedOnCategory.put("fruits",
                getResources().getIdentifier("fruits_array",
                        "string", getPackageName()));
    }

    /**
     * This method dynamically  assigns pictures and words based on the category
     * given from intent bundle (chosen category)
     * */
    private void fillListFromResources(String category){
//        pictures = new ArrayList<>();
        words = new ArrayList<>();

        for (String word: getResources().getStringArray(wordsBasedOnCategory.get(category))) {
            words.add(word);
//            pictures.add(getResources().getDrawable(getResources()
//                    .getIdentifier(category + "_" + word,
//                            "drawable", getPackageName())));
        }
    }

}
