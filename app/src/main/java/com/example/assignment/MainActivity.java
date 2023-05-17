package com.example.assignment;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private SqliteDatabase mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView marquee_text = findViewById(R.id.marquee01);
        marquee_text.setSelected(true);

        RecyclerView contactView = findViewById(R.id.myContactList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        contactView.setLayoutManager(linearLayoutManager);
        contactView.setHasFixedSize(true);
        mDatabase = new SqliteDatabase(this);
        ArrayList<Member> allMember = mDatabase.listMember();
        if (allMember.size() > 0) {
            contactView.setVisibility(View.VISIBLE);
            MemberAdapter mAdapter = new MemberAdapter(this, allMember);
            contactView.setAdapter(mAdapter);
        }
        else {
            contactView.setVisibility(View.GONE);
            Toast.makeText(this, "There is no contact in the database. Start adding now", Toast.LENGTH_LONG).show();
        }
        Button btnAdd = findViewById(R.id.button);
        btnAdd.setOnClickListener(view -> addTaskDialog());
    }

    private void addTaskDialog() {
        LayoutInflater inflater = LayoutInflater.from(this);
        View subView = inflater.inflate(R.layout.add_memeber, null);
        final EditText usertype = subView.findViewById(R.id.enterUsername);
        final EditText fNameplate = subView.findViewById(R.id.enterFirstName);
        final EditText lastname = subView.findViewById(R.id.enterLastName);
        final EditText numberplate = subView.findViewById(R.id.enterMobileNo);
        //final EditText addresstxt = subView.findViewById(R.id.enterAddress);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Add new Member");
        builder.setView(subView);
        builder.create();
        builder.setPositiveButton("ADD Member", (dialog, which) -> {
            final String username = usertype.getText().toString();
            final String firstName = fNameplate.getText().toString();
            final String lastName = lastname.getText().toString();
            final String number = numberplate.getText().toString();
            //final String address = addresstxt.getText().toString();
            if (TextUtils.isEmpty(username)) {
                Toast.makeText(MainActivity.this, "Something went wrong. Check your input values", Toast.LENGTH_LONG).show();
            }
            else {
                Member remember = new Member(username, firstName , lastName , number );
                mDatabase.addMembers(remember);
                finish();
                startActivity(getIntent());
            }
        });
        builder.setNegativeButton("CANCEL", (dialog, which) -> Toast.makeText(MainActivity.this, "Task cancelled", Toast.LENGTH_LONG).show());
        builder.show();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mDatabase != null) {
            mDatabase.close();
        }
    }
}