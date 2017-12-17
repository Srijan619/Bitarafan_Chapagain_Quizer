package fi.centria.bitarafan_chapagain_quizer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity implements View.OnClickListener{

    private Button animals, objects, colors, fruits;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        animals = findViewById(R.id.btn_animals);
        objects = findViewById(R.id.btn_objects);
        colors  = findViewById(R.id.btn_colors);
        fruits  = findViewById(R.id.btn_fruits);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_animals:
                startTest("animals");
                break;
            case R.id.btn_objects:
                startTest("objects");
                break;
            case R.id.btn_colors:
                startTest("colors");
                break;
            case R.id.btn_fruits:
                startTest("fruits");
                break;
        }
    }

    /*
    *
    *
    */
    private void startTest(String category){
        Intent i = new Intent();
        i.setClass(this, QuizerActivity.class);
        i.putExtra("category", category);
        startActivity(i);
    }
}
