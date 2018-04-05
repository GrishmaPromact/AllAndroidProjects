package com.promact.sqlitedemo;



import java.util.ArrayList;
import java.util.HashMap;
import android.os.Bundle;
import android.app.ListActivity;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.ListView;


public class MainActivity extends ListActivity {
    Intent intent;
    TextView animalId;
    DBController controller = new DBController(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ArrayList<HashMap<String, String>> animalList =  controller.getAllAnimals();
        if(animalList.size()!=0) {
            ListView lv = getListView();
            lv.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
                    animalId = (TextView) view.findViewById(R.id.animalId);
                    String valAnimalId = animalId.getText().toString();
                    Intent  objIndent = new Intent(getApplicationContext(),EditAnimal.class);
                    objIndent.putExtra("animalId", valAnimalId);
                    startActivity(objIndent);
                }
            });
            ListAdapter adapter = new SimpleAdapter( MainActivity.this,animalList, R.layout.view_animal_entry, new String[] { "animalId","animalName"}, new int[] {R.id.animalId, R.id.animalName});
            setListAdapter(adapter);
        }
    }
    public void showAddForm(View view) {
        Intent objIntent = new Intent(getApplicationContext(), NewAnimal.class);
        startActivity(objIntent);
    }
}

        /*DatabaseHandler db = new DatabaseHandler(this);

        // Inserting Contacts
        Log.d("Insert: ", "Inserting ..");
        db.addContact(new Contact("Grishma", "7405588239"));
        db.addContact(new Contact("Palak", "9897562453"));
        db.addContact(new Contact("Pooja", "8834622567"));
        db.addContact(new Contact("Vrutika", "9099910941"));

        // Reading all contacts
        Log.d("Reading: ", "Reading all contacts..");
        List<Contact> contacts = db.getAllContacts();

        for (Contact cn : contacts) {
            String log = "Id: "+cn.getID()+" ,Name: " + cn.getName() + " ,Phone: " +
                    cn.getPhoneNumber();
            // Writing Contacts to log
            Log.d("Name: ", log);
        }
        Contact contact=new Contact();
            db.updateContact(contact);
           String log= contact.setName("Binal");
            contact.setID(99);
            contact.setPhoneNumber("000000000");

            Log.d("Name: ", String.valueOf(contact));*/
