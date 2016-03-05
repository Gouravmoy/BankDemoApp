package com.example.lenovo.bankapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.lenovo.bankapp.R;

public class AcountBalanceActivity extends AppCompatActivity {

    Intent intent;
    TextView userNameTV;
    TextView savingsBalanceTV;
    TextView currentBalanceTV;
    String userName = "Solomon Kane";


    public static double savingsBalance = 70000;
    public static double currentBalance = 50000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_balance);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initilizeViews();
        setValuesInView();
    }


    private void setValuesInView() {
        userNameTV.setText(userName);
        savingsBalanceTV.setText("Rs. " + Double.toString(savingsBalance));
        currentBalanceTV.setText("Rs. " + Double.toString(currentBalance));
    }

    private void initilizeViews() {
        intent = getIntent();
        userNameTV = (TextView) findViewById(R.id.name);
        savingsBalanceTV = (TextView) findViewById(R.id.balance);
        currentBalanceTV = (TextView) findViewById(R.id.currBalance);
    }


}
