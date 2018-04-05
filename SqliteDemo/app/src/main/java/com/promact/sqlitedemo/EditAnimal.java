package com.promact.sqlitedemo;

/**
 * Created by grishma on 05-12-2016.
 */import java.util.Calendar;
import java.util.HashMap;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.app.Activity;
import android.content.Intent;
import android.view.View;


public class EditAnimal extends Activity{
    EditText animalName;
    DBController controller = new DBController(this);
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_animal);
        animalName = (EditText) findViewById(R.id.animalName);
        Intent objIntent = getIntent();
        String animalId = objIntent.getStringExtra("animalId");
        Log.d("Reading: ", "Reading all contacts..");
        HashMap<String, String> animalList = controller.getAnimalInfo(animalId);
        Log.d("animalName",animalList.get("animalName"));
        if(animalList.size()!=0) {
            animalName.setText(animalList.get("animalName"));
        }
    }
    public void editAnimal(View view) {
        HashMap<String, String> queryValues =  new  HashMap<String, String>();
        animalName = (EditText) findViewById(R.id.animalName);
        Intent objIntent = getIntent();
        String animalId = objIntent.getStringExtra("animalId");
        queryValues.put("animalId", animalId);
        queryValues.put("animalName", animalName.getText().toString());

        controller.updateAnimal(queryValues);
        this.callHomeActivity(view);

    }
    public void removeAnimal(View view) {
        Intent objIntent = getIntent();
        String animalId = objIntent.getStringExtra("animalId");
        controller.deleteAnimal(animalId);
        this.callHomeActivity(view);

    }
    public void callHomeActivity(View view) {
        Intent objIntent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(objIntent);
    }
}