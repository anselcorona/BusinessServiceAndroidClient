package com.example.iperez.tallerimagenes.Services;

import com.example.iperez.tallerimagenes.entidades.Cliente;
import com.example.iperez.tallerimagenes.entidades.UsuarioLogin;
import com.example.iperez.tallerimagenes.entidades.foto;
import com.example.iperez.tallerimagenes.entidades.recepcionTest;

import java.util.List;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface tallerClient {
    @GET("TallerServiciosWebService/RecepcionesVehiculos/{limit}/{offset}")
    Call<List<recepcionTest>> getFotos(@Path("limit") int parLimit, @Path("offset") int parOffset);
    @GET("TallerServiciosWebService/RecepcionesVehiculos/{idRecepcion}/Imagenes")
    Call<List<foto>> getFotos(@Path("idRecepcion") int parIdRecepcion);
    @POST("TallerServiciosWebService/RecepcionesVehiculos/{recepcionID}/Imagenes")
    Call<foto> enviarFoto( @Body foto parFot, @Path("recepcionID") int parrecepcionID);
    @PUT("TallerServiciosWebService/RecepcionesVehiculos/{recepcionID}/Imagenes/{idImagen}")
    Call<foto> actualizarFoto( @Body foto parFot,@Path("recepcionID") int parrecepcionID,@Path("idImagen") int paridImagen );
    @DELETE("TallerServiciosWebService/RecepcionesVehiculos/Imagenes/{idImagen}")
    Call<foto> eliminarFoto(@Path("idImagen") int idImagen);
    @POST ("TallerServiciosWebService/RecepcionesVehiculos/Login")
    Call<String> iniciarSesion(@Body String credenciales);

}
