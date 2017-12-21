package fi.centria.bitarafan_chapagain_quizer;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.FragmentManager;
import android.os.Build;
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
        implements AnswerFragment.IOnAnswerPicked {
    final String TAG = "quizerTAGS";
    int count_correct = 0;
    FragmentManager manager;
    PictureFragment picFrag;
    AnswerFragment ansFrag;
    QuestionGenerator questionGenerator;
    String category = null;
    String answerWord = null;
    List<String> choices = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quizer);
        getActionBar().setDisplayHomeAsUpEnabled(true);

        manager = getFragmentManager();
        picFrag = (PictureFragment) manager.findFragmentById(R.id.picFrag);
        ansFrag = (AnswerFragment) manager.findFragmentById(R.id.ansFrag);
        Bundle extra = getIntent().getExtras();
        category = extra.getString("category");
<<<<<<< HEAD
        if (category != null) {
=======
        Log.d(TAG, "onCreate: " + category);
        if (category != null){
>>>>>>> 8d3de62b964aa367a44e4cf6c74441692e0bcfcd
            questionGenerator = new QuestionGenerator(getApplicationContext(), category);
        }
        setNewQuestion();

    }

    private void setNewQuestion() {
        choices = questionGenerator.questionToAsk();
<<<<<<< HEAD
        if (choices != null) {
            picFrag.changePicture(GetIdentifier(category + "_" + choices.get(0), "drawable"));
=======
        Log.d(TAG, "setNewQuestion: " + choices.get(0));

        if (choices != null){
            picFrag.changePicture(GetIdentifier(category + "_" + choices.get(0),"drawable"));
>>>>>>> 8d3de62b964aa367a44e4cf6c74441692e0bcfcd
            answerWord = getStringLocale(choices.get(0));
            Collections.shuffle(choices);
            ansFrag.setAnswers(localizeChoices(choices));
        } else {
            showDialog().show();
            picFrag.changePicture(GetIdentifier("stop", "drawable"));
            ansFrag.setAnswers(null);
        }
    }

    private List<String> localizeChoices(List<String> choices) {
        List<String> localChoices = new ArrayList<>();
        for (String s : choices)
            localChoices.add(getStringLocale(s));
        return localChoices;
    }

    private String getStringLocale(String engWord) {
        return getResources().getString(GetIdentifier(category + "_" + engWord, "string"));
    }

    private int GetIdentifier(String name, String type) {
        return getResources().getIdentifier(name, type, getPackageName());
    }

    private boolean checkAnswer(String rcvdAnsw) {
        if (rcvdAnsw == answerWord) return true;
        return false;
    }

    @Override
    public void onAnswerPicked(String msg) {
        if (checkAnswer(msg))
        count_correct++;
        setNewQuestion();

    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private AlertDialog showDialog() {

        AlertDialog.Builder dlgbldr = new AlertDialog.Builder(this);
        dlgbldr.setView(R.layout.dialog_view);
        dlgbldr.setMessage("Game finished with score " + count_correct);
        dlgbldr.setPositiveButton("OK", null);

        return dlgbldr.create();

    }
}
