package com.example.acorona.test;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomerAdapter extends RecyclerView.Adapter<CustomerAdapter.ViewHolder> {
    private Context context;
    private ArrayList<Customer> customers;
    private OnCustomerClickListener onCustomerClickListener;

    public interface OnCustomerClickListener{
        void onCustomerClick(Customer customer);
    }

    public void setOnCustomerClickListener(OnCustomerClickListener onCustomerClickListener){
        this.onCustomerClickListener = onCustomerClickListener;
    }

    public CustomerAdapter(Context context, ArrayList<Customer> customers){
        this.context = context;
        this.customers = customers;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.customer_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Customer customer = customers.get(position);
        holder.customerPhoneNumberTextView.setText(customer.getPhone());
        holder.customerNameTextView.setText(customer.getCustomerName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onCustomerClickListener.onCustomerClick(customer);
            }
        });
    }

    @Override
    public int getItemCount() {
        return customers.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        public TextView customerNameTextView;
        public TextView customerPhoneNumberTextView;

        public ViewHolder(View itemView){
            super(itemView);
            customerNameTextView = itemView.findViewById(R.id.CustomerName);
            customerPhoneNumberTextView = itemView.findViewById(R.id.CustomerPhoneNumber);
        }
    }
}
