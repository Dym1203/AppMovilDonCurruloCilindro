package com.example.appmovildoncurrulocilindro.utilidades;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class DateSerializer implements JsonDeserializer<Date>, JsonSerializer<Date> {
        @Override
        public Date deserialize(JsonElement je, Type type, JsonDeserializationContext jdc) throws JsonParseException {
                String date = je. getAsString();
                SimpleDateFormat formateador = new SimpleDateFormat("dd-MM-aaaa" );
                formateador. setTimeZone(TimeZone. getDefault());
                try {
                        return new Date(formateador. parse(date). getTime());
                } catch (ParseException e) {
                        System. err. println("Error al analizar la fecha debido a:" + e. getMessage());
                        return null;
                }
        }

        @Override
        public JsonElement serialize(Date date, Type typeOfSrc, JsonSerializationContext context) {
                SimpleDateFormat formateador = new SimpleDateFormat("dd-MM-yyyy" );
                formateador. setTimeZone(TimeZone. getDefault());
                try {
                        return new JsonPrimitive(formateador. format(date));
                } catch (Exception e) {
                        System. err. println("Error al analizar la fecha debido a:" + e. getMessage());
                        return null;
                }
        }
}
