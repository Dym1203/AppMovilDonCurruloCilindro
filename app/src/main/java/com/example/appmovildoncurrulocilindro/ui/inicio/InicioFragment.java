package com.example.appmovildoncurrulocilindro.ui.inicio;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.appmovildoncurrulocilindro.adapter.CategoriaAdaptador;
import com.example.appmovildoncurrulocilindro.R;
import com.example.appmovildoncurrulocilindro.viewmodel.CategoriaViewModel;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class InicioFragment extends Fragment {

    private CategoriaViewModel categoriaViewModel;
    private GridView gvCategorias;
    private CategoriaAdaptador categoriaAdaptador;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_inicio, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
        initAdapter();
        loadData();
    }

    private void init(View v) {
        ViewModelProvider vmp = new ViewModelProvider(this);
        categoriaViewModel = vmp.get(CategoriaViewModel.class);
        gvCategorias = v.findViewById(R.id.gvCategorias);
    }

    private void initAdapter() {
        categoriaAdaptador = new CategoriaAdaptador(getContext(), R.layout.item_categorias, new ArrayList<>());
        gvCategorias.setAdapter(categoriaAdaptador);
    }

    private void loadData() {
        categoriaViewModel.listarCategoriasActivas().observe(getViewLifecycleOwner(), response -> {
            if (response.getRpta() == 1) {
                categoriaAdaptador.clear();
                categoriaAdaptador.addAll(response.getBody());
                categoriaAdaptador.notifyDataSetChanged();
            }
            else {
                System.out.println("Error al obtener las categorías activas");
            }
        });
    }
}