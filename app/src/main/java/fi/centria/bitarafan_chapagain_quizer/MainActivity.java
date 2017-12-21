package fi.centria.bitarafan_chapagain_quizer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity implements View.OnClickListener {

    private Button animals, objects, colors, fruits;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        animals = findViewById(R.id.btn_animals);
        animals.setOnClickListener(this);
        objects = findViewById(R.id.btn_objects);
        objects.setOnClickListener(this);
        colors = findViewById(R.id.btn_colors);
        colors.setOnClickListener(this);
        fruits = findViewById(R.id.btn_fruits);
        fruits.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.exit) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_animals:
                startIntest("animals");
                break;
            case R.id.btn_objects:
                startIntest("objects");
                break;
            case R.id.btn_colors:
                startIntest("colors");
                break;
            case R.id.btn_fruits:
                startIntest("fruits");
                break;
            default:
                break;
        }
    }

    private void startIntest(String category) {
        Intent i = new Intent();
        i.setClass(this, QuizerActivity.class);
        i.putExtra("category", category);
        startActivity(i);
    }
}
