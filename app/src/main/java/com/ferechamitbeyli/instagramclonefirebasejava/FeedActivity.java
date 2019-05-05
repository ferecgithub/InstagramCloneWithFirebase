package com.ferechamitbeyli.instagramclonefirebasejava;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class FeedActivity extends AppCompatActivity {
    private ListView listView;
    private PostClass adapter;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference myRef;
    private ArrayList<String> userEmailFromFb;
    private ArrayList<String> userImageFromFb;
    private ArrayList<String> userCommentFromFb;


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_post, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.add_post) {
            Intent intent = new Intent(this, UploadActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);

        listView = findViewById(R.id.listView);
        userEmailFromFb = new ArrayList<>();
        userImageFromFb = new ArrayList<>();
        userCommentFromFb = new ArrayList<>();

        firebaseDatabase = FirebaseDatabase.getInstance();
        myRef = firebaseDatabase.getReference();

        adapter = new PostClass(userEmailFromFb, userCommentFromFb, userImageFromFb, this);
        listView.setAdapter(adapter);
        getDataFromFirebase();

    }
    public void getDataFromFirebase() {
        DatabaseReference newReference = firebaseDatabase.getReference("Posts");
        newReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                System.out.println("FBV children:" +  dataSnapshot.getChildren());
//                System.out.println("FBV key:" +  dataSnapshot.getKey());
//                System.out.println("FBV value:" +  dataSnapshot.getValue());
//                System.out.println("FBV priority:" +  dataSnapshot.getPriority());
                for(DataSnapshot ds: dataSnapshot.getChildren()) {
                    HashMap<String, String> hashMap = (HashMap<String, String>) ds.getValue();
                    userEmailFromFb.add(hashMap.get("useremail"));
                    userCommentFromFb.add(hashMap.get("comment"));
                    userImageFromFb.add(hashMap.get("downloadurl"));
                    adapter.notifyDataSetChanged();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
