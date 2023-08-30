package com.example.appmovildoncurrulocilindro.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appmovildoncurrulocilindro.api.ConfigApi;
import com.example.appmovildoncurrulocilindro.communication.Communication;
import com.example.appmovildoncurrulocilindro.communication.MostrarBadgeCommunication;
import com.example.appmovildoncurrulocilindro.ui.DetallePlatoActivity;
import com.example.appmovildoncurrulocilindro.model.service.DetallePedido;
import com.example.appmovildoncurrulocilindro.model.service.Plato;
import com.example.appmovildoncurrulocilindro.R;
import com.example.appmovildoncurrulocilindro.utilidades.DateSerializer;
import com.example.appmovildoncurrulocilindro.utilidades.TimeSerializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.Locale;

public class PlatoPorCategoriaAdaptador extends RecyclerView.Adapter<PlatoPorCategoriaAdaptador.ViewHolder> {
    private List<Plato> listadoPlatoPorCategoria;
    private final Communication communication;
    private final MostrarBadgeCommunication mostrarBadgeCommunication;

    public PlatoPorCategoriaAdaptador(List<Plato> listadoPlatoPorCategoria, Communication communication, MostrarBadgeCommunication mostrarBadgeCommunication) {
        this.listadoPlatoPorCategoria = listadoPlatoPorCategoria;
        this.communication = communication;
        this.mostrarBadgeCommunication = mostrarBadgeCommunication;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_platos_por_categoria, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.setItem(this.listadoPlatoPorCategoria.get(position));
    }

    @Override
    public int getItemCount() {
        return this.listadoPlatoPorCategoria.size();
    }

    public void updateItems(List<Plato> platoByCategoria) {
        this.listadoPlatoPorCategoria.clear();
        this.listadoPlatoPorCategoria.addAll(platoByCategoria);
        this.notifyDataSetChanged();
    }

    protected class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView imgPlatoC;
        private final TextView namePlatoC, tvPrecioPlatoC;
        private final Button btnOrdenarPC;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.imgPlatoC = itemView.findViewById(R.id.imgPlatoC);
            this.namePlatoC = itemView.findViewById(R.id.namePlatoC);
            this.tvPrecioPlatoC = itemView.findViewById(R.id.tvPrecioPlatoC);
            this.btnOrdenarPC = itemView.findViewById(R.id.btnOrdenarPC);
        }

        public void setItem(final Plato p) {
            String url = ConfigApi.ipDylan + "/api/documento-almacenado/download/" + p.getFoto().getFileName(); //baseUrlE

            Picasso picasso = new Picasso.Builder(itemView.getContext())
                    .downloader(new OkHttp3Downloader(ConfigApi.getClient()))
                    .build();
            picasso.load(url)
                    //.networkPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE) //No lo almacena el la caché ni en el disco
                    .error(R.drawable.image_not_found)
                    .into(imgPlatoC);
            namePlatoC.setText(p.getNombre());
            tvPrecioPlatoC.setText(String.format(Locale.ENGLISH, "S/%.2f", p.getPrecio()));
            btnOrdenarPC.setOnClickListener(v -> {
                DetallePedido detallePedido = new DetallePedido();
                detallePedido.setPlato(p);
                detallePedido.setCantidad(1);
                detallePedido.setPrecio(p.getPrecio());
                mostrarBadgeCommunication.add(detallePedido);
            });

            //Inicializar la vista del detalle del plato
            itemView.setOnClickListener(v -> {
                final Intent i = new Intent(itemView.getContext(), DetallePlatoActivity.class);
                final Gson g = new GsonBuilder()
                        .registerTypeAdapter(Date.class, new DateSerializer())
                        .registerTypeAdapter(Time.class, new TimeSerializer())
                        .create();
                i.putExtra("detallePlato", g.toJson(p));
                communication.showDetails(i);
            });

        }
    }
}