package com.example.android.firebaseapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button write_button = (Button) findViewById(R.id.write_data);
        Button retrieve_button = (Button) findViewById(R.id.retrieve_data);
        final TextView displaydata = (TextView) findViewById(R.id.display_data);

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("message");



        write_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Write a message to the database


                myRef.setValue("Testing Firebase");
            }
        });


        retrieve_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Read Data from Firebase

                // Read from the database
                myRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // This method is called once with the initial value and again
                        // whenever data at this location is updated.
                        String value = dataSnapshot.getValue(String.class);
                        displaydata.setText("Value is: " + value);

                          //TAG is displaying error
                       // Log.d(TAG, "Value is: " + value);
                    }

                    @Override
                    public void onCancelled(DatabaseError error) {
                        // Failed to read value

                        displaydata.setText("Failed to read value." + error.toException());

                        //TAG is displaying error
                        //Log.w(TAG, "Failed to read value.", error.toException());
                    }
                });
            }
        });

    }

}
