package com.example.finalexam07600498;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.finalexam07600498.db.LedgerItem;
import com.example.finalexam07600498.db.Repository;
import com.example.finalexam07600498.model.User;

import java.util.ArrayList;
import java.util.List;

public class RegisterActivity  extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Button RegisButton = findViewById(R.id.register_button);
        RegisButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditText fullnameRegisEditText = findViewById(R.id.full_name_edit_text);
                final String fullname = fullnameRegisEditText.getText().toString();

                EditText userRegisEditText = findViewById(R.id.username_edit_text);
                final String username = userRegisEditText.getText().toString();

                EditText passRegisEditText = findViewById(R.id.password_edit_text);
                final String password = passRegisEditText.getText().toString();

                if (username.equals("") || fullname.equals("") || password.equals("")) {
                    Toast.makeText(RegisterActivity.this, "All fields are required", Toast.LENGTH_LONG).show();
                } else {
                    final Repository repo = new Repository(RegisterActivity.this);
                    final List<User> userList = new ArrayList<>();
                    repo.getUser(new Repository.GetCallback() {
                        @Override
                        public void onSuccess(List<LedgerItem> ledgerItemList) {
                            Boolean check = false;

                            if (!check) {
                                repo.insertRegister(new LedgerItem(username, fullname, password), new Repository.InsertCallback() {
                                    @Override
                                    public void onSuccess() {
                                        Toast.makeText(RegisterActivity.this, "Register successfully", Toast.LENGTH_LONG).show();
                                        startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                                        RegisterActivity.this.finish();
                                    }
                                });
                            }
                        }
                    });


                }
            }
        });
    }
}