package com.example.appmovildoncurrulocilindro.utilidades;

import com.example.appmovildoncurrulocilindro.model.service.DetallePedido;

import java.util.ArrayList;

public class Carrito {
    //Creamos un ArrayList de la clase DetallePedido
    private static final ArrayList<DetallePedido> detallePedidos = new ArrayList<>();

    //Método para agregar productos al carrito (bolsa)
    public static String agregarPlatos(DetallePedido detallepedido)
    {
        int index = 0;
        boolean b = false;
        for (DetallePedido dp : detallePedidos) {
            if (dp.getPlato().getId_plato() == detallepedido.getPlato().getId_plato()) {
                detallePedidos.set(index, detallepedido);
                b = true;
                return "¡El plato ha sido agregado al carrito, se actualizará la cantidad";
            }
            index++;
        }
        if (!b) {
            detallePedidos.add(detallepedido);
            return "¡El plato ha sido agregado al carrito con éxito!";
        }
        return ". . . . ";
    }

    //Método para eliminar un plato del carrito (bolsa)
    public static void eliminar(final int idp)
    {
        DetallePedido dpE = null;
        for (DetallePedido dp : detallePedidos) {
            if (dp.getPlato().getId_plato() == idp) {
                dpE = dp;
                break;
            }
        }
        if (dpE != null) {
            detallePedidos.remove(dpE);
            System.out.println("¡Se eliminó el plato del detalle de pedido!");
        }
    }

    //Método para conseguir los detalles del pedido
    public static ArrayList<DetallePedido> getDetallePedidos() {
        return detallePedidos;
    }

    //Método para limpiar
    public static void limpiar() {
        detallePedidos.clear();
    }
}