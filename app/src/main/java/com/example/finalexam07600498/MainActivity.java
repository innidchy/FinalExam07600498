package com.example.finalexam07600498;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.finalexam07600498.db.LedgerDao;
import com.example.finalexam07600498.db.LedgerItem;
import com.example.finalexam07600498.db.Repository;
import com.example.finalexam07600498.model.User;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final List<User> userList = new ArrayList<>();
        Repository repo = new Repository(this);

        repo.getUser(new Repository.GetCallback() {
            @Override
            public void onSuccess(List<LedgerItem> ledgerItemList) {
                for(LedgerItem ledgerItem :ledgerItemList){
                    userList.add(new User(ledgerItem.username,ledgerItem.fullname,ledgerItem.password));
                    System.out.println("Have a user fullname : "+ledgerItem.fullname);
                }

            }
        });
        Button loginButton = findViewById(R.id.login_button);
        Button regisButton = findViewById(R.id.register_button);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText _username = findViewById(R.id.username_edit_text);
                EditText _password = findViewById(R.id.password_edit_text);
                String name = _username.getText().toString();
                String password = _password.getText().toString();
                if((name.equals("")||password.equals(""))){
                    Toast.makeText(MainActivity.this,"All fields are required",Toast.LENGTH_LONG).show();
                }
                else{
                    Boolean check = false;
                    for(User u : userList){
                        if(name.equals(u.username)&&password.equals(u.password)){
                            Toast.makeText(MainActivity.this,"Welcome "+u.fullname,Toast.LENGTH_LONG).show();
                            check = true;
                        }
                    }
                    if(!check){
                        Toast.makeText(MainActivity.this,"Invalid username or password",Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
        regisButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,RegisterActivity.class));
                MainActivity.this.finish();
            }
        });
    }
    }
