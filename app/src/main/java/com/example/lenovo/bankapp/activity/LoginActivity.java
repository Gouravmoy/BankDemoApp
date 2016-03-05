package com.example.lenovo.bankapp.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lenovo.bankapp.R;

import static android.widget.Toast.LENGTH_LONG;

public class LoginActivity extends AppCompatActivity {

    EditText accNoEditText;
    EditText passwordEditText;
    String accNO = "123456";
    String pass = "Welcome123";
    String name = "Solomon";
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        accNoEditText = (EditText) findViewById(R.id.accountNo);
        passwordEditText = (EditText) findViewById(R.id.accPass);
        progressDialog = new ProgressDialog(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public void login(View view) {
        progressDialog.setMessage("Authenticating");
        progressDialog.show();
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                progressDialog.cancel();
                String a = accNoEditText.getText().toString();
                if (accNoEditText.getText().toString().equals(accNO) && passwordEditText.getText().toString().equals(pass)) {
                    nextIntent();
                } else {
                    Toast.makeText(getApplicationContext(), "UserName or Password is Incorrect", LENGTH_LONG).show();
                    accNoEditText.setText("");
                    passwordEditText.setText("");
                }
            }
        }, 3000);
    }

    private void nextIntent() {
        Intent bankIntent = new Intent(this, MainActivity.class);
        bankIntent.putExtra("name", name);
        startActivity(bankIntent);
    }
}
