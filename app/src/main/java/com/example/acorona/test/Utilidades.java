package com.example.acorona.test;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.preference.PreferenceManager;
import android.provider.BaseColumns;
import android.util.Base64;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.nio.charset.Charset;
import java.util.Map;

public class Utilidades {

    public static Object obtenerValorPreferencia(String parkey, Context parContexto)
    {
        String valor = "";
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(parContexto);

        Map<String, Object> valoresPreferencias = (Map<String, Object>) pref.getAll();

        return valoresPreferencias.get(parkey);
    }


}
