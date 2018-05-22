package com.example.acorona.test;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Customer> customers = new ArrayList<>();
    private RecyclerView customerView;

    private CustomerAdapter customerAdapter;



    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    return true;
            }
            return false;
        }
    };
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        customerView = findViewById(R.id.customerRecyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        customerView.setLayoutManager(linearLayoutManager);
        loadClients();


        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    private void loadClients(){
        final ProgressDialog loading = ProgressDialog.show(this, "Cargando clientes", "Por favor espere", false, false);
        businessServiceClient bsc = ServiceGenerator.createService(businessServiceClient.class);

        Call<List<Customer>> receivedCustomers = bsc.getCustomer();
        if(receivedCustomers!=null){
            receivedCustomers.enqueue(new Callback<List<Customer>>() {
                @Override
                public void onResponse(@NonNull Call<List<Customer>> call, @NonNull Response<List<Customer>> response) {
                    loading.dismiss();
                    if(response.code()==404){
                        AlertDialog.Builder builder;
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            builder = new AlertDialog.Builder(MainActivity.this, android.R.style.Theme_Material_Dialog_Alert);
                        } else {
                            builder = new AlertDialog.Builder(MainActivity.this);
                        }
                        builder.setTitle("¡Error!")
                                .setMessage("Error en el servidor")
                                .setPositiveButton("Intentar de nuevo", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        loadClients();
                                    }
                                })
                                .setNegativeButton("Cerrar aplicación", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        finish();
                                    }
                                })
                                .show();
                    }else{
                        if(response.body()==null){
                            customers = new ArrayList<>();
                        }else{
                            customers = (ArrayList<Customer>) response.body();
                        }
                        customerAdapter = new CustomerAdapter(MainActivity.this, customers);
                        customerView.setAdapter(customerAdapter);

                        customerAdapter.setOnCustomerClickListener(new CustomerAdapter.OnCustomerClickListener() {
                            @Override
                            public void onCustomerClick(Customer customer) {
                                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                                intent.putExtra("CUSTOMER", customer);
                                startActivity(intent);
                            }
                        });
                    }
                }


                @Override
                public void onFailure(@NonNull Call<List<Customer>> call, @NonNull Throwable t) {
                    loading.dismiss();
                    AlertDialog.Builder builder;
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        builder = new AlertDialog.Builder(MainActivity.this, android.R.style.Theme_Material_Dialog_Alert);
                    } else {
                        builder = new AlertDialog.Builder(MainActivity.this);
                    }
                    builder.setTitle("¡Error!")
                            .setMessage("Error en el servidor")
                            .setPositiveButton("Intentar de nuevo", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    loadClients();
                                }
                            })
                            .setNegativeButton("Cerrar aplicación", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    finish();
                                }
                            })
                            .show();
                    if (t instanceof IOException) {
                        Toast.makeText(MainActivity.this, "this is an actual network failure :( inform the user and possibly retry", Toast.LENGTH_SHORT).show();
                        // logging probably not necessary
                    }
                    else {
                        Toast.makeText(MainActivity.this, "conversion issue! big problems :(", Toast.LENGTH_SHORT).show();
                        // todo log to some central bug tracking service
                    }
                }
            });
        }
    }

}
