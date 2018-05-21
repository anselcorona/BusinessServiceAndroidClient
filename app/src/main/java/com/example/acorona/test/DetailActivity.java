package com.example.acorona.test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        TextView name = findViewById(R.id.customer_detail_name);
        TextView number = findViewById(R.id.customer_detail_number);

        Bundle extras = getIntent().getExtras();
        final Customer customer = extras.getParcelable("CUSTOMER");

        if(customer!=null){
            name.setText(customer.getCustomerName());
            number.setText(customer.getPhone());
        }
    }
}
