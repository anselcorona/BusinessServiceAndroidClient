package com.example.iperez.tallerimagenes.Services;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.util.Patterns;
import android.widget.Toast;

import java.security.cert.CertificateException;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import com.example.iperez.tallerimagenes.MainActivity;
import com.example.iperez.tallerimagenes.Sesiones.AlertDialogManager;
import com.example.iperez.tallerimagenes.Utilities.Utilidades;
import com.example.iperez.tallerimagenes.loginActivity;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by avasquez on 05/01/2017.
 */

public class ServiceGenerator {
        public static String API_BASE_URL = "http:/" +
                "/10.0.0.27:22198/";
    static AlertDialogManager alert = new AlertDialogManager();
    private static OkHttpClient.Builder httpClient;

    private static Retrofit.Builder builder;
    private static OkHttpClient okHttpClient;

    public static void reiniciar(Context context){
        httpClient = null;
        builder = null;
        okHttpClient = null;
        inicializar(context);
    }

    public static void finalizar(){
        httpClient = null;
        builder = null;
        okHttpClient = null;
    }

    public static void inicializar(Context parContexto) {
        boolean generar = false;

        if (parContexto != null) {
            Object valor = Utilidades.obtenerValorPreferencia("servidor_defecto", parContexto);
            if (valor != null && !valor.equals(API_BASE_URL)) {
                API_BASE_URL = valor.toString();
                Log.e("Ip servidor", valor.toString());
                generar = true;
            }
        }

        if (httpClient == null)
            generar = true;

        if (generar) {
            // Si es Https, entonces generar un cliente de este tipo.
            if (API_BASE_URL.toLowerCase().contains("https")) {
                okHttpClient = getUnsafeOkHttpClient();
                Log.v("CREANDO SERVICIO", okHttpClient.toString());
            } else {
                httpClient = new OkHttpClient.Builder();
            }

    if (Patterns.WEB_URL.matcher(API_BASE_URL).matches()) {
        builder = new Retrofit.Builder().baseUrl(API_BASE_URL).addConverterFactory(ScalarsConverterFactory.create()).addConverterFactory(GsonConverterFactory.create());
    } else {
        alert.showAlertDialog(parContexto, "URL no válida", "Inserte una URL válida en la preferencia de servidor por defecto", false);

    }
      }

    }

    public static <T> T createService(Class<T> serviceClass) {
        Retrofit retrofit;

        if ( okHttpClient != null){
            retrofit = builder.client(okHttpClient).build();
        }else {

            retrofit = builder.client(httpClient.build()).build();
        }

        return retrofit.create(serviceClass);
    }

    private static OkHttpClient getUnsafeOkHttpClient() {
        try {
            // Create a trust manager that does not validate certificate chains
            final TrustManager[] trustAllCerts = new TrustManager[] {
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                            return new java.security.cert.X509Certificate[]{};
                        }
                    }
            };

            // Install the all-trusting trust manager
            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
            // Create an ssl socket factory with our all-trusting manager
            final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.sslSocketFactory(sslSocketFactory);
            builder.hostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });

            OkHttpClient okHttpClient = builder.build();
            return okHttpClient;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
