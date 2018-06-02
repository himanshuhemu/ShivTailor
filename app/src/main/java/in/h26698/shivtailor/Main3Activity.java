package in.h26698.shivtailor;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import org.w3c.dom.Text;

public class Main3Activity extends AppCompatActivity {
  private EditText mSearchField;
  private Button mSearchBtn;
  private RecyclerView mresultList;
  private DatabaseReference mUserDatabase;
  @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        mUserDatabase = FirebaseDatabase.getInstance().getReference("Users");
         mSearchField = (EditText) findViewById(R.id.searchBox);
         mSearchBtn = (Button) findViewById(R.id.butn);
        mresultList =  (RecyclerView) findViewById(R.id.result_list);
        mresultList.setHasFixedSize(true);
        mresultList.setLayoutManager(new LinearLayoutManager(this));

        mSearchBtn.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                String searchText = mSearchField.getText().toString();
                int fsearch = Integer.parseInt(searchText);
                firebaseUserSearch(fsearch);
            }
        });


    }

    private void firebaseUserSearch(int  searchText) {
       Toast.makeText(Main3Activity.this,"Searchiing", Toast.LENGTH_LONG).show();

        Query firebaseSearchQuerry = mUserDatabase.orderByChild("number").equalTo(searchText);
        FirebaseRecyclerAdapter<Users , UsersViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Users, UsersViewHolder>(
                Users.class,
                R.layout.list_layout,
               UsersViewHolder.class,
                firebaseSearchQuerry

        ) {
            @Override
            protected void populateViewHolder(UsersViewHolder viewHolder, Users model, int position) {
                //
                viewHolder.setdetails(getApplication(),model.getName(),model.getStatus(),model.getNumber());

            }
        };

        mresultList.setAdapter(firebaseRecyclerAdapter);
    }

    //View holder Class
    public static class UsersViewHolder extends RecyclerView.ViewHolder {
      View mView;

      public UsersViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
      }
   public void setdetails(Context ctx ,String userName , String userStatus , String userNumber){
       TextView user_name =(TextView)mView.findViewById(R.id.name_text);
       TextView user_status=(TextView)mView.findViewById(R.id.status_text);
       TextView user_number=(TextView)mView.findViewById(R.id.number_view);
       user_name.setText(userName);
       user_status.setText(userStatus);
       user_number.setText(userNumber);
      }

    }
}
