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
        TextView AddressLine1 = findViewById(R.id.customer_detail_addressline1);
        TextView AddressLine2 = findViewById(R.id.customer_detail_addressline2);
        TextView City = findViewById(R.id.customer_detail_city);
        TextView State = findViewById(R.id.customer_detail_state);
        TextView Country = findViewById(R.id.customer_detail_country);
        TextView PostalCode = findViewById(R.id.customer_detail_PostalCode);
        TextView CreditLimit = findViewById(R.id.customer_detail_CreditLimit);

        Bundle extras = getIntent().getExtras();
        final Customer customer = extras != null ? (Customer) extras.getParcelable("CUSTOMER") : null;

        if(customer!=null){
            name.setText(customer.getCustomerName());
            number.setText(customer.getPhone());
            AddressLine1.setText(customer.getAddressLine1());
            AddressLine2.setText(customer.getAddressLine2());
            City.setText(customer.getCity());
            State.setText(customer.getState());
            Country.setText(customer.getCountry());
            PostalCode.setText(customer.getPostalCode());
            CreditLimit.setText(String.valueOf(customer.getCreditLimit()));
        }
    }
}
