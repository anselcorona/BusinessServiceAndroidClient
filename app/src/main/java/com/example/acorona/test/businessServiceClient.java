package com.example.acorona.test;

import com.example.acorona.test.Customer;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface businessServiceClient {
    @GET("/customers")
    Call<List<Customer>> getCustomer();
    @DELETE("/customers/delete/{cusNumber}")
    Call<Customer> eliminarCliente(@Path("cusNumber") int cusNumber);

}
