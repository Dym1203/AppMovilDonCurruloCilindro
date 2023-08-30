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

import cn.pedant.SweetAlert.SweetAlertDialog;

public class PlatoRecomendadoAdaptador extends RecyclerView.Adapter<PlatoRecomendadoAdaptador.ViewHolder> {
    private List<Plato> platos;
    private final Communication communication;
    private final MostrarBadgeCommunication mostrarBadgeCommunication;

    public PlatoRecomendadoAdaptador(List<Plato> platos, Communication communication, MostrarBadgeCommunication mostrarBadgeCommunication) {
        this.platos = platos;
        this.communication = communication;
        this.mostrarBadgeCommunication = mostrarBadgeCommunication;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_platos, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.setItem(this.platos.get(position));
    }

    @Override
    public int getItemCount() {
        return this.platos.size();
    }

    public void updateItems(List<Plato> plato) {
        this.platos.clear();
        this.platos.addAll(plato);
        this.notifyDataSetChanged();
    }

    protected class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        public void setItem(final Plato p) {
            ImageView imgPlato = itemView.findViewById(R.id.imgPlato);
            TextView namePlato = itemView.findViewById(R.id.namePlato);
            TextView precioPlato = itemView.findViewById(R.id.precioPlato);
            Button btnOrdenar = itemView.findViewById(R.id.btnOrdenar);

            String url = ConfigApi.ipDylan + "/api/documento-almacenado/download/" + p.getFoto().getFileName(); //baseUrlE

            Picasso picasso = new Picasso.Builder(itemView.getContext())
                    .downloader(new OkHttp3Downloader(ConfigApi.getClient()))
                    .build();
            picasso.load(url)
            //.networkPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
                    .error(R.drawable.image_not_found)
                    .into(imgPlato);
            namePlato.setText(p.getNombre());
            precioPlato.setText(String.format(Locale.ENGLISH, "S/%.2f", p.getPrecio()));
            btnOrdenar.setOnClickListener(v -> {
                DetallePedido detallePedido = new DetallePedido();
                detallePedido.setPlato(p);
                detallePedido.setCantidad(1); //Predeterminado
                detallePedido.setPrecio(p.getPrecio());
                mostrarBadgeCommunication.add(detallePedido);
                //successMessage(Carrito.agregarPlatos(detallePedido));
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

        public void successMessage(String message) {
            new SweetAlertDialog(itemView.getContext(),
                SweetAlertDialog.SUCCESS_TYPE).setTitleText("¡Buen Trabajo!")
                    .setContentText(message).show();
        }

    }
}