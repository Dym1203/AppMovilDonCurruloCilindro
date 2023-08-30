package com.example.appmovildoncurrulocilindro.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appmovildoncurrulocilindro.communication.AnularPedidoCommunication;
import com.example.appmovildoncurrulocilindro.communication.Communication;
import com.example.appmovildoncurrulocilindro.model.service.dto.PedidoConDetallesDTO;
import com.example.appmovildoncurrulocilindro.R;
import com.example.appmovildoncurrulocilindro.utilidades.DateSerializer;
import com.example.appmovildoncurrulocilindro.utilidades.TimeSerializer;
import com.example.appmovildoncurrulocilindro.ui.DetalleMisComprasActivity;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class MisComprasAdaptador extends RecyclerView.Adapter<MisComprasAdaptador.ViewHolder> {

    private final List<PedidoConDetallesDTO> pedidos;
    private final Communication communication;
    private final AnularPedidoCommunication anularPedidoCommunication;

    public MisComprasAdaptador(List<PedidoConDetallesDTO> pedidos, Communication communication, AnularPedidoCommunication anularPedidoCommunication) {
        this.pedidos = pedidos;
        this.communication = communication;
        this.anularPedidoCommunication = anularPedidoCommunication;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_mis_compras, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.setItem(this.pedidos.get(position));
    }

    @Override
    public int getItemCount() {
        return this.pedidos.size();
    }

    public void updateItems(List<PedidoConDetallesDTO> pedido){
        this.pedidos.clear();
        this.pedidos.addAll(pedido);
        this.notifyDataSetChanged();
    }

    protected class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        public void setItem(final PedidoConDetallesDTO dto) {
            final TextView txtValueCodPurchases = this.itemView.findViewById(R.id.txtValueCodPurchases),
                    txtValueDatePurchases = this.itemView.findViewById(R.id.txtValueDatePurchases),
                    txtValueAmount = this.itemView.findViewById(R.id.txtValueAmount),
                    txtValueOrder = this.itemView.findViewById(R.id.txtValueOrder);
            final Button btnExportInvoice = this.itemView.findViewById(R.id.btnExportInvoice);
            txtValueCodPurchases.setText("P0000" + Integer.toString(dto.getPedido().getId_pedido()));
            /**/
            Date fechaSql = dto.getPedido().getFecha();
            if (fechaSql != null) {
                String fechaString = fechaSql.toString();
                SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy/MM/dd");
                try {
                    Date fecha = (Date) formatoFecha.parse(fechaString);
                    SimpleDateFormat formatoFechaLegible = new SimpleDateFormat("dd/MM/yyyy");
                    String fechaFormateada = formatoFechaLegible.format(fecha);
                    txtValueDatePurchases.setText(fechaFormateada);
                } catch (ParseException e) {
                    System.err.println("Error al analizar la fecha debido a: " + e.getMessage());
                }
            } else {
                System.err.println("La fecha es nula");
            }
            /**/
            txtValueAmount.setText(String.format(Locale.ENGLISH, "S/%.2f", dto.getPedido().getTotal()));
            txtValueOrder.setText(dto.getPedido().isAnularPedido() ? "¡Pedido cancelado!" : "¡En camino!");

            itemView.setOnClickListener(v -> {
                final Intent i = new Intent(itemView.getContext(), DetalleMisComprasActivity.class);
                final Gson g = new GsonBuilder()
                        .registerTypeAdapter(Date.class, new DateSerializer())
                        .registerTypeAdapter(Time.class, new TimeSerializer())
                        .create();
                i.putExtra("detailsPurchases", g.toJson(dto.getDetallePedido()));
                communication.showDetails(i);//Esto es solo para dar una animación.
            });
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    anularPedido(dto.getPedido().getId_pedido());
                    return true;
                }
            });
            btnExportInvoice.setOnClickListener(view -> {
                int idCli = dto.getPedido().getCliente().getId_cliente();
                int idPed = dto.getPedido().getId_pedido();
                int idOrden = dto.getPedido().getId_pedido();
                String fileName = "Boleta de Venta" + " 00" + dto.getPedido().getId_pedido() + ".pdf";
                communication.exportInvoice(idCli, idPed, idOrden, fileName);
            });
        }

        private void anularPedido(int id) {
            new SweetAlertDialog(itemView.getContext(), SweetAlertDialog.WARNING_TYPE).setTitleText("¡Aviso!")
                    .setContentText("¿Estás seguro de cancelar el pedido solicitado?")
                    .setCancelText("No").setConfirmText("Si")
                    .showCancelButton(true)
                    .setConfirmClickListener(sDialog -> {
                        sDialog.dismissWithAnimation();
                        new SweetAlertDialog(itemView.getContext(), SweetAlertDialog.SUCCESS_TYPE).setTitleText("¡Buen Trabajo!")
                                .setContentText(anularPedidoCommunication.anularPedido(id))
                                .show();
                    }).setCancelClickListener(sDialog -> {
                        sDialog.dismissWithAnimation();
                        new SweetAlertDialog(itemView.getContext(), SweetAlertDialog.ERROR_TYPE).setTitleText("¡Operación Cancelada!")
                                .setContentText("¡No cancelaste ningún pedido!")
                                .show();
                    }).show();
        }
    }
}