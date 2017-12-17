package fi.centria.bitarafan_chapagain_quizer;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class QuizerActivity extends Activity
                implements AnswerFragment.IOnMessageSend
{

    List<Drawable>  pictures;
    List<String>    words;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quizer);

        getListFromResources();
    }

    private void getListFromResources(){
        pictures = new ArrayList<>();
        words = new ArrayList<>();

        for (String word: getResources().getStringArray(R.array.animals_array)) {
            words.add(word);
            pictures.add(getResources().getDrawable(getResources()
                    .getIdentifier("animals_"+ word,
                            "drawable", getPackageName())));
        }
    }

    @Override
    public void onMessageSend(String msg) {

    }
}
