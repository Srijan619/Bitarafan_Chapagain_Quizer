package fi.centria.bitarafan_chapagain_quizer;

import android.app.Activity;
import android.app.FragmentManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class QuizerActivity extends Activity
                implements AnswerFragment.IOnAnswerPicked
{
    final String TAG = "quizerTAGS";

    FragmentManager     manager;
    PictureFragment     picFrag;
    AnswerFragment      ansFrag;
    QuestionGenerator   questionGenerator;
    String              category    = null;
    String              answerWord  = null;
    List<String>        choices     = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quizer);
        getActionBar().setDisplayHomeAsUpEnabled(true);

        manager = getFragmentManager();
        picFrag = (PictureFragment) manager.findFragmentById(R.id.picFrag);
        ansFrag = (AnswerFragment)  manager.findFragmentById(R.id.ansFrag);
        Bundle extra = getIntent().getExtras();
        category = extra.getString("category");
        if (category != null){
            questionGenerator = new QuestionGenerator(getApplicationContext(), category);
        }
        setNewQuestion();

    }

    private void setNewQuestion(){
        choices = questionGenerator.questionToAsk();
        if (choices != null){
            picFrag.changePicture(GetIdentifier(category + "_" + choices.get(0),"drawable"));
            answerWord = getStringLocale(choices.get(0));
            Log.d(TAG, "setNewQuestion: " + choices.get(0));
            Collections.shuffle(choices);
            ansFrag.setAnswers(localizeChoices(choices));
        }   else {
            picFrag.changePicture(GetIdentifier("stop", "drawable"));
            ansFrag.setAnswers(null);
        }
    }

    private List<String> localizeChoices(List<String> choices){
        List<String> localChoices = new ArrayList<>();
        for (String s: choices)
            localChoices.add(getStringLocale(s));
        return localChoices;
    }

    private String getStringLocale(String engWord){
        return getResources().getString(GetIdentifier(category+"_"+engWord, "string"));
    }

    private int GetIdentifier(String name, String type){
        return getResources().getIdentifier(name, type, getPackageName());
    }

//    private boolean checkAnswer(String rcvdAnsw){
//        if (rcvdAnsw == answerWord) return true;
//        return false;
//    }

    @Override
    public void onAnswerPicked(String msg) {
//        if(checkAnswer(msg))
        setNewQuestion();
    }

}
