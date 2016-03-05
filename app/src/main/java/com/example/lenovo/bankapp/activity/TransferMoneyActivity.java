package com.example.lenovo.bankapp.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lenovo.bankapp.R;

import static android.widget.Toast.LENGTH_LONG;
import static com.example.lenovo.bankapp.activity.AcountBalanceActivity.currentBalance;
import static com.example.lenovo.bankapp.activity.AcountBalanceActivity.savingsBalance;

public class TransferMoneyActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    TextView name;
    Spinner spinner;
    EditText toAccNo;
    EditText amount;
    String accountType;
    Double transferAmt;
    String userName = "Solomon Kane";
    ArrayAdapter<CharSequence> spinnerAdapter;
    ProgressDialog progressDialog;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer_money);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initilize();
    }

    private void initilize() {
        name = (TextView) findViewById(R.id.name);
        name.setText(userName);
        spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);
        spinnerAdapter = ArrayAdapter.createFromResource(this, R.array.accType, android.R.layout.simple_spinner_item);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);
        toAccNo = (EditText) findViewById(R.id.toAcc);
        amount = (EditText) findViewById(R.id.amout);
        intent = new Intent(this, MainActivity.class);
        progressDialog = new ProgressDialog(this);
    }

    public void transferMoney(View view) {
        transferAmt = Double.parseDouble(amount.getText().toString());
        progressDialog.setMessage("Transferring Money");
        progressDialog.show();
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                progressDialog.cancel();
                switch (accountType) {
                    case "SAVINGS": {
                        if (transferAmt <= savingsBalance) {
                            savingsBalance -= transferAmt;
                            toastMessage("Money Successfully Transfered!");
                            startActivity(intent);
                        } else {
                            toastMessage("Insufficient balance in Savings Account");
                        }
                        break;
                    }
                    case "CURRENT": {
                        if (transferAmt <= currentBalance) {
                            currentBalance -= transferAmt;
                            toastMessage("Money Successfully Transfered!");
                            startActivity(intent);
                        } else {
                            toastMessage("Insufficient balance in Current Account");
                        }
                        break;
                    }
                }
            }
        }, 3000);


    }

    private void toastMessage(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        accountType = (String) parent.getItemAtPosition(position);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        accountType = "SAVINGS";
    }
}
