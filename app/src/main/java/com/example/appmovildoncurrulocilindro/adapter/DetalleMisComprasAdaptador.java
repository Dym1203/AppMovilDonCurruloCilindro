package com.example.appmovildoncurrulocilindro.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appmovildoncurrulocilindro.api.ConfigApi;
import com.example.appmovildoncurrulocilindro.model.service.DetallePedido;
import com.example.appmovildoncurrulocilindro.R;
import com.google.android.material.imageview.ShapeableImageView;
import com.squareup.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Locale;

public class DetalleMisComprasAdaptador extends RecyclerView.Adapter<DetalleMisComprasAdaptador.ViewHolder> {
    private final List<DetallePedido> detalles;

    public DetalleMisComprasAdaptador(List<DetallePedido> detalles) {
        this.detalles = detalles;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_detalle_mis_compras, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.setItem(this.detalles.get(position));
    }

    @Override
    public int getItemCount() {
        return detalles.size();
    }

    public void updateItems(List<DetallePedido> detalles){
        this.detalles.clear();
        this.detalles.addAll(detalles);
        this.notifyDataSetChanged();
    }

    protected class ViewHolder extends RecyclerView.ViewHolder {
        private ShapeableImageView imgProduct;
        private TextView txtValueCodDetailPurchases, txtValuePlatillo, txtValueQuantity, txtValuePrecioPlatillo;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.imgProduct = itemView.findViewById(R.id.imgProduct);
            this.txtValueCodDetailPurchases = itemView.findViewById(R.id.txtValueCodDetailPurchases);
            this.txtValuePlatillo = itemView.findViewById(R.id.txtValuePlatillo);
            this.txtValueQuantity = itemView.findViewById(R.id.txtValueQuantity);
            this.txtValuePrecioPlatillo = itemView.findViewById(R.id.txtValuePrecioPlatillo);
        }

        public void setItem(final DetallePedido detalle) {
            String url = ConfigApi.ipDylan + "/api/documento-almacenado/download/" + detalle.getPlato().getFoto().getFileName();

            Picasso picasso = new Picasso.Builder(itemView.getContext())
                    .downloader(new OkHttp3Downloader(ConfigApi.getClient()))
                    .build();
            picasso.load(url)
                    //.networkPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
                    .error(R.drawable.image_not_found)
                    .into(imgProduct);
            txtValueCodDetailPurchases.setText("P0000" + Integer.toString(detalle.getPedido().getId_pedido()));
            txtValuePlatillo.setText(detalle.getPlato().getNombre());
            txtValueQuantity.setText(Integer.toString(detalle.getCantidad()));
            txtValuePrecioPlatillo.setText(String.format(Locale.ENGLISH, "S/%.2f", detalle.getPrecio()));
        }
    }
}
