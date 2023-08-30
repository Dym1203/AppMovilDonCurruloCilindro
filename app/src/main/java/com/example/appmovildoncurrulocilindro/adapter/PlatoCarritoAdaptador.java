package com.example.appmovildoncurrulocilindro.adapter;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appmovildoncurrulocilindro.api.ConfigApi;
import com.example.appmovildoncurrulocilindro.communication.CarritoCommunication;
import com.example.appmovildoncurrulocilindro.model.service.DetallePedido;
import com.example.appmovildoncurrulocilindro.R;
import com.google.android.material.imageview.ShapeableImageView;
import com.squareup.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Locale;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class PlatoCarritoAdaptador extends RecyclerView.Adapter<PlatoCarritoAdaptador.ViewHolder> {

    private final List<DetallePedido> detalles;
    private final CarritoCommunication c;

    public PlatoCarritoAdaptador(List<DetallePedido> detalles, CarritoCommunication c) {
        this.detalles = detalles;
        this.c = c;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_platos_carrito, parent, false);
        return new ViewHolder(v, this.c);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.setItem(this.detalles.get(position));
    }

    @Override
    public int getItemCount() {
        return this.detalles.size();
    }

    protected class ViewHolder extends RecyclerView.ViewHolder {
        private final ShapeableImageView imgPlatoDC;
        private final ImageView btnDecrease, btnAdd, btnEliminarPCarrito;
        private final EditText edtCantidad;
        private final TextView tvNombrePlatoDC, tvPrecioPDC;
        private final CarritoCommunication c;

        public ViewHolder(@NonNull View itemView, CarritoCommunication c) {
            super(itemView);
            this.c = c;
            this.imgPlatoDC = itemView.findViewById(R.id.imgPlatoDC);
            this.btnEliminarPCarrito = itemView.findViewById(R.id.btnEliminarPlCarrito);
            this.btnAdd = itemView.findViewById(R.id.btnAdd);
            this.btnDecrease = itemView.findViewById(R.id.btnDecrease);
            this.edtCantidad = itemView.findViewById(R.id.edtCantidad);
            this.tvNombrePlatoDC = itemView.findViewById(R.id.tvNombrePlatoDC);
            this.tvPrecioPDC = itemView.findViewById(R.id.tvPrecioPDC);
        }

        public void setItem(final DetallePedido dp) {
            this.tvNombrePlatoDC.setText(dp.getPlato().getNombre());
            this.tvPrecioPDC.setText(String.format(Locale.ENGLISH, "S/%.2f", dp.getPrecio()));
            int cant = dp.getCantidad();
            this.edtCantidad.setText(Integer.toString(cant));
            String url = ConfigApi.ipDylan + "/api/documento-almacenado/download/" + dp.getPlato().getFoto().getFileName(); //baseUrlE
            Picasso picasso = new Picasso.Builder(itemView.getContext())
                    .downloader(new OkHttp3Downloader(ConfigApi.getClient()))
                    .build();
            picasso.load(url)
                    .error(R.drawable.image_not_found)
                    .into(this.imgPlatoDC);

            //-------------Actualizar Cantidad del Carrito-------------------------
            btnAdd.setOnClickListener(v -> {
                if (dp.getCantidad() != 10) {
                    dp.addOne();
                    PlatoCarritoAdaptador.this.notifyDataSetChanged();
                }
            });
            btnDecrease.setOnClickListener(v -> {
                if (dp.getCantidad() != 1) {
                    dp.removeOne();
                    PlatoCarritoAdaptador.this.notifyDataSetChanged();
                }
            });

            //------------------Eliminar item del carrito-----------------------
            this.btnEliminarPCarrito.setOnClickListener(v -> {
                showMsg(dp.getPlato().getId_plato());
            });
        }

        public void toastCorrecto(String texto) {
            LayoutInflater layoutInflater = LayoutInflater.from(itemView.getContext());
            View layoutView = layoutInflater.inflate(R.layout.custom_toast_ok, (ViewGroup) itemView.findViewById(R.id.toast_ok));
            TextView textView = layoutView.findViewById(R.id.txtMensajeToast1);
            textView.setText(texto);

            Toast toast = new Toast(itemView.getContext());
            toast.setGravity(Gravity.CENTER_VERTICAL | Gravity.BOTTOM, 0, 200);
            toast.setDuration(Toast.LENGTH_LONG);
            toast.setView(layoutView);
            toast.show();
        }

        private void showMsg(int idplato) {
            new SweetAlertDialog(itemView.getContext(), SweetAlertDialog.WARNING_TYPE).setTitleText("¡Aviso!")
                    .setContentText("¿Estás seguro de eliminar el producto de tu carrito de compras?")
                    .setCancelText("No").setConfirmText("Si")
                    .showCancelButton(true).setCancelClickListener(sDialog -> {
                        sDialog.dismissWithAnimation();
                        new SweetAlertDialog(itemView.getContext(), SweetAlertDialog.ERROR_TYPE).setTitleText("¡Operación cancelada!")
                                .setContentText("¡No eliminaste el producto!")
                                .show();
                    }).setConfirmClickListener(sweetAlertDialog -> {
                        c.eliminarDetalle(idplato);
                        sweetAlertDialog.dismissWithAnimation();
                        new SweetAlertDialog(itemView.getContext(), SweetAlertDialog.SUCCESS_TYPE).setTitleText("¡Buen Trabajo!")
                                .setContentText("¡Producto eliminado de tu carrito de compras!")
                                .show();
                    }).show();
        }

    }
}
