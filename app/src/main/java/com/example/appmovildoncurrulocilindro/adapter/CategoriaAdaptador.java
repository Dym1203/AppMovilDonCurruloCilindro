package com.example.appmovildoncurrulocilindro.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.appmovildoncurrulocilindro.api.ConfigApi;
import com.example.appmovildoncurrulocilindro.model.service.Categoria;
import com.example.appmovildoncurrulocilindro.ui.ListarPlatosPorCategoriaActivity;
import com.example.appmovildoncurrulocilindro.R;
import com.google.android.material.imageview.ShapeableImageView;
import com.squareup.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CategoriaAdaptador extends ArrayAdapter<Categoria> {

    private final String url = ConfigApi.ipDylan + "/api/documento-almacenado/download/"; //baseUrlE

    public CategoriaAdaptador(@NonNull Context context, int resource, @NonNull List<Categoria> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_categorias, parent, false);
        }
        Categoria c = this.getItem(position);
        ShapeableImageView imgCategoria = convertView.findViewById(R.id.imgCategoria);
        TextView tvNombreCategoria = convertView.findViewById(R.id.tvNombreCategoria);

        Picasso picasso = new Picasso.Builder(convertView.getContext())
                .downloader(new OkHttp3Downloader(ConfigApi.getClient()))
                .build();
        picasso.load(url + c.getFoto().getFileName())
                //.networkPolicy(MemoryPolice.NO_CACHE, MemoryPolice.NO_STORE)
                .error(R.drawable.image_not_found)
                .into(imgCategoria);
        tvNombreCategoria.setText(c.getNombre());
        convertView.setOnClickListener(v -> {
            Intent i = new Intent(getContext(), ListarPlatosPorCategoriaActivity.class);
            i.putExtra("idC", c.getId_categoria()); //Obtenemos el id de la Categoria
            i.putExtra("nomC", c.getNombre()); //Obtenemos el nombre de la Categoria
            getContext().startActivity(i);
        });
        return convertView;
    }
}