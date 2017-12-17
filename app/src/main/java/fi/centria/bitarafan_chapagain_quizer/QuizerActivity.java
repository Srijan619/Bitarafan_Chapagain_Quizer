package fi.centria.bitarafan_chapagain_quizer;

import android.app.Activity;
import android.app.FragmentManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QuizerActivity extends Activity
                implements AnswerFragment.IOnAnswerPicked
{

    List<Drawable>  pictures;
    List<String>    words;
    Map<String, Integer> wordsBasedOnCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quizer);
        init_wordsResourceIds();

        Bundle extras = getIntent().getExtras();
        if (extras == null) return;
        String cat = extras.getString("category");
        getListFromResources(cat);
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
    private void getListFromResources(String category){
        pictures = new ArrayList<>();
        words = new ArrayList<>();

        for (String word: getResources().getStringArray(wordsBasedOnCategory.get(category))) {
            words.add(word);
            pictures.add(getResources().getDrawable(getResources()
                    .getIdentifier(category + "_" + word,
                            "drawable", getPackageName())));
        }
    }

    @Override
    public void onAnswerPicked(String msg) {

    }

    private void setNewPicture() {
//        FragmentManager manager = getFragmentManager();
//        PictureFragment picFrag = (PictureFragment) manager.findFragmentById(R.id.picFrag);
//        picFrag.changePicture(getResources().getIdentifier());
    }
}
