package com.example.smartgarden;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class MainActivity extends AppCompatActivity {
    TextView Temp;
    TextView Humid;
    TextView Moisture;
    ImageButton On_btn;
    ImageButton Off_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Temp=findViewById(R.id.textTemp);
        Humid=findViewById(R.id.textHum);
        Moisture=findViewById(R.id.textSoil);
        On_btn=findViewById(R.id.on_btn);
        Off_btn=findViewById(R.id.off_btn);
        DatabaseReference FB_humid= FirebaseDatabase.getInstance().getReference(  "KVTM").child("Humid");
        DatabaseReference FB_temp=FirebaseDatabase.getInstance().getReference(  "KVTM").child("Temp");
        DatabaseReference FB_moisture=FirebaseDatabase.getInstance().getReference(  "KVTM").child("Moisture");
        DatabaseReference signal=FirebaseDatabase.getInstance().getReference("SIGNAL");
        FB_temp.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Temp.setText(snapshot.getValue().toString()+"°C");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        FB_humid.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull  DataSnapshot snapshot) {
                Humid.setText(snapshot.getValue().toString()+"%");
            }

            @Override
            public void onCancelled(@NonNull  DatabaseError error) {

            }
        });
        FB_moisture.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull  DataSnapshot snapshot) {
                Moisture.setText(snapshot.getValue().toString()+"%");
            }

            @Override
            public void onCancelled(@NonNull  DatabaseError error) {

            }
        });
        On_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signal.setValue(1);
            }
        });
        Off_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signal.setValue(0);
            }
        });

    }
}