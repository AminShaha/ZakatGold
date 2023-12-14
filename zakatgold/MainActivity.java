package com.example.zakatgold;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    RadioGroup radioGroup;
    Button calcZakat, resetButton; // Added resetButton here
    TextView tvTotVal, tvGoldPay, tvTotZakat;
    EditText etWeight, etGoldVal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        etWeight = findViewById(R.id.etWeight);
        etGoldVal = findViewById(R.id.etGoldValue);

        radioGroup = findViewById(R.id.radioGroup);
        calcZakat = findViewById(R.id.btCalc);
        calcZakat.setOnClickListener(this);

        tvTotVal = findViewById(R.id.tvTotVal);
        tvGoldPay = findViewById(R.id.tvGoldPay);
        tvTotZakat = findViewById(R.id.tvTotZakat);

        resetButton = findViewById(R.id.resetButton); // Initialize resetButton
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Reset input fields
                etWeight.setText("");
                etGoldVal.setText("");

                // Reset output texts
                tvTotVal.setText("Total value of the gold: ");
                tvGoldPay.setText("Total Gold Value Zakat Payable:");
                tvTotZakat.setText("Total Zakat:");
            }
        });
    }

    @Override
    public void onClick(View v) {
        int radioId = radioGroup.getCheckedRadioButtonId();

        if (radioId == -1) {
            Toast.makeText(MainActivity.this, "Select an option", Toast.LENGTH_SHORT).show();
            return;
        }

        if (radioId == R.id.rbKeep) {
            calculateZakat(85);
        } else if (radioId == R.id.rbWear) {
            calculateZakat(200);
        } else {
            Toast.makeText(MainActivity.this, "Select an option", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.item_share) {
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_TEXT, "Please use my application - http://t.co/app");
            startActivity(Intent.createChooser(shareIntent, null));
            return true;
        } else if (item.getItemId() == R.id.item_about){
            Intent aboutIntent = new Intent(this, AboutActivity.class);
            startActivity(aboutIntent);
        }
        return false;
    }

    private void calculateZakat(int limit) {
        try {
            double weight = Double.parseDouble(etWeight.getText().toString());
            double goldVal = Double.parseDouble(etGoldVal.getText().toString());

            double totValGold = weight * goldVal;
            double zPay = (weight - limit) * goldVal;
            if (zPay <= 0) {
                zPay = 0;
            }
            double totZ = zPay * 0.025;

            tvTotVal.setText("Total value of the gold: RM " + totValGold);
            tvGoldPay.setText("Total Gold Value Zakat Payable: RM " + zPay);
            tvTotZakat.setText("Total Zakat: RM " + totZ);

        } catch (NumberFormatException e) {
            Toast.makeText(MainActivity.this, "Fill the number properly", Toast.LENGTH_SHORT).show();
        }
    }
}
