package fi.centria.bitarafan_chapagain_quizer;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by ali on 12/20/2017.
 */

public class QuestionGenerator {
    final private String TAG ="quizerTAGS";
    private Context         context;
    private List<String>    WordsInCategory;
    private int             NumberOfWords;
    private List<String>    wordsAsked;
    private String          category;



    QuestionGenerator(Context context, String category){
        this.context    = context;
        this.category   = category;
        fillWordsListFromResources();
        wordsAsked = new ArrayList<>();
    }

    /**
     *  Randomly pick a word from words in a category
     *  If the word hasn't been asked previously
     *  then submit for question else pick a new word.
     */
    public List<String> questionToAsk() {
        if (wordsAsked.size() < NumberOfWords){
            List<String> newQuestion = new ArrayList<>();
            String wordToAsk;
            do {
                wordToAsk = getRandomWord();
            }
            while (wordsAsked.contains(wordToAsk));
            newQuestion.add(wordToAsk);     //index 0 of newQuestion List contains the answer to question
            wordsAsked.add(wordToAsk);
            Log.d(TAG, "questionToAsk: " + wordToAsk);
            while (newQuestion.size() < 4) {
                do {
                    wordToAsk = getRandomWord();
                } while (newQuestion.contains(wordToAsk));
                newQuestion.add(wordToAsk);
            }
            return newQuestion;
        } else return null;
    }

//    private String getStringLocale(String engWord){
//        return context.getResources().getString(GetIdentifier(category+"_"+engWord, "string"));
//    }

//    private int GetIdentifier(String name, String type){
//        return context.getResources().getIdentifier(name, type, context.getPackageName());
//    }
    /**
     * a random word from a list of words based on category
     * @return
     */
    private String getRandomWord(){
        return WordsInCategory.get(new Random().nextInt(NumberOfWords));
    }

    /**
     *
     */
    private void fillWordsListFromResources(){

        WordsInCategory = new ArrayList<>();
        for (String word: context.getResources().getStringArray(
                GetIdentifier(category + "_array" , "array") ) ) {
            WordsInCategory.add(word);
        }
        NumberOfWords = WordsInCategory.size();
    }

    public int GetIdentifier(String name, String type){
        return context.getResources().getIdentifier(name, type, context.getPackageName());
    }

}
