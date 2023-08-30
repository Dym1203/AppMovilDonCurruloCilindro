package com.example.appmovildoncurrulocilindro.communication;

import android.content.Intent;

public interface Communication {
    void showDetails(Intent i);
    void exportInvoice(int idCli, int idPed, int idOrden, String fileName);
}