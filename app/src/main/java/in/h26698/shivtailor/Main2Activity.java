package in.h26698.shivtailor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {

   private DatabaseReference mdatabase ;
   private ListView muserlist;
  private ArrayList<String>musername = new ArrayList<>();
    private ArrayList<String>mkey = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
//retrival
        mdatabase = FirebaseDatabase.getInstance().getReference();
        muserlist = (ListView) findViewById(R.id.user_list);
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this , android.R.layout.simple_list_item_1, musername);
        muserlist.setAdapter(arrayAdapter);
        mdatabase.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String value = dataSnapshot.getValue(String.class);
                String key = dataSnapshot.getKey();
                mkey.add(key);
                musername.add(value);



                arrayAdapter.notifyDataSetChanged();

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                String value = dataSnapshot.getValue(String.class);
                String key = dataSnapshot.getKey();
                int index = mkey.indexOf(key);
                musername.set(index,value);


            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}
