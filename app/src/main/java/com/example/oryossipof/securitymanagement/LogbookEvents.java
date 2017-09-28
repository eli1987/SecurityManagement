package com.example.oryossipof.securitymanagement;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.google.firebase.database.IgnoreExtraProperties;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class LogbookEvents extends Activity {

    private Context context;
    private Firebase mRef;
    private ListView mListView ;
    private ImageView iv;
    private ArrayList<String> mUsersName = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logbook_events);

        mListView = (ListView) findViewById(R.id.lv_msglist);
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,mUsersName);
        mListView.setAdapter(arrayAdapter);
        context= this;
        iv = (ImageView)findViewById(R.id.imageUpload);
        mRef = new Firebase("https://securitymanagement-8dd8d.firebaseio.com/Events");

        mRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {


                for (DataSnapshot child: dataSnapshot.getChildren()) {
                    User people = child.getValue(User.class);

                    mUsersName.add(people.getUsername());
                    mUsersName.add(people.getTime());
                    mUsersName.add(people.getDis());
                    mUsersName.add(people.getUrlImage());
                    arrayAdapter.notifyDataSetChanged();

                    if(!people.getUrlImage().equals("")) {
                       Uri uri;
                       String stringUri;
                       uri = Uri.parse(people.getUrlImage());

                       Log.e("URI:" , uri.toString());
                       Picasso.with(context).load(uri).into(iv);
                   }

                }


            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

    }




}
@IgnoreExtraProperties
class User
{
    public String  username;
    public String  time;
    public String  dis;
    public String urlImage;

    public User()
    {


    }

    public User(String username ,String time , String dis ,String urlImage)
    {

        this.username=username;
        this.time= time;
        this.dis = dis;
        this.urlImage =urlImage;
    }

    public String getDis() {
        return dis;
    }

    public String getTime() {
        return time;
    }

    public String getUsername() {
        return username;
    }

    public void setDis(String dis) {
        this.dis = dis;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }
}


