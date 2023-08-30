package com.example.appmovildoncurrulocilindro.ui;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.appmovildoncurrulocilindro.model.service.Cliente;
import com.example.appmovildoncurrulocilindro.model.service.DocumentoAlmacenado;
import com.example.appmovildoncurrulocilindro.model.service.Usuario;
import com.example.appmovildoncurrulocilindro.R;
import com.example.appmovildoncurrulocilindro.viewmodel.ClienteViewModel;
import com.example.appmovildoncurrulocilindro.viewmodel.DocumentoAlmacenadoViewModel;
import com.example.appmovildoncurrulocilindro.viewmodel.UsuarioViewModel;
import com.google.android.material.textfield.TextInputLayout;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.time.LocalDateTime;

import cn.pedant.SweetAlert.SweetAlertDialog;
import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class RegisterActivity extends AppCompatActivity {
    private File f;
    private ClienteViewModel clienteViewModel;
    private UsuarioViewModel usuarioViewModel;
    private DocumentoAlmacenadoViewModel documentoAlmacenadoViewModel;
    private Button btnSubirImagen, btnGuardarDatos;
    private CircleImageView imageUser;
    private AutoCompleteTextView dropdownTipoDoc,dropdownDistrito;
    private EditText edtNombres, edtApellidos, edtNumDocU, edtDireccion,edtTelefono,edtCorreoE,edtContraseña,edtConfirmac;
    private TextInputLayout txtNombres,txtApellidos,txtInputTipoDoc,txtInputNumeroDocU,txtInputDistrito,txtDireccion,
            txtTelefono,txtCorreo,txtContrasena,txtConfirmarc;
    private final static int LOCATION_REQUEST_CODE = 23;


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        this.init();
        this.initViewModel();
        this.spinners();
    }

    private void spinners() {
        //Lista de tipos de doc
        String[]tipoDoc = getResources().getStringArray(R.array.tipoDoc);
        ArrayAdapter arrayTipoDoc = new ArrayAdapter(this,R.layout.dropdown_item,tipoDoc);
        dropdownTipoDoc.setAdapter(arrayTipoDoc);
        //Lista de distritos
        String[]distitos = getResources().getStringArray(R.array.ditrito);
        ArrayAdapter arrayDistritos = new ArrayAdapter(this,R.layout.dropdown_item,distitos);
        dropdownDistrito.setAdapter(arrayDistritos);
    }

    private void initViewModel() {
        final ViewModelProvider vmp= new ViewModelProvider(this);
        this.clienteViewModel = vmp.get(ClienteViewModel.class);
        this.usuarioViewModel = vmp.get(UsuarioViewModel.class);
        this.documentoAlmacenadoViewModel = vmp.get(DocumentoAlmacenadoViewModel.class);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) !=
                PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    LOCATION_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull @NotNull String[] permissions, @NonNull @NotNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case LOCATION_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "¡Gracias por conceder los permisos para escoger tu foto de perfil!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "¡No se concedió el permiso!", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void init(){
        btnSubirImagen = findViewById(R.id.btnSubirImagen);
        btnGuardarDatos = findViewById(R.id.btnGuardarDatos);
        imageUser = findViewById(R.id.imageUser);
        dropdownTipoDoc = findViewById(R.id.dropdownTipoDoc);
        dropdownDistrito = findViewById(R.id.dropdownDistrito);
        edtNombres = findViewById(R.id.edtNombres);
        edtApellidos = findViewById(R.id.edtApellidos);
        edtNumDocU = findViewById(R.id.edtNumDocU);
        edtDireccion = findViewById(R.id.edtDireccion);
        edtTelefono = findViewById(R.id.edtTelefono);
        edtCorreoE = findViewById(R.id.edtCorreoE);
        edtContraseña = findViewById(R.id.edtContraseña);
        edtConfirmac = findViewById(R.id.edtConfirmac);
        txtNombres = findViewById(R.id.txtNombres);
        txtApellidos = findViewById(R.id.txtApellidos);
        txtInputTipoDoc = findViewById(R.id.txtInputTipoDoc);
        txtInputNumeroDocU = findViewById(R.id.txtInputNumeroDocU);
        txtInputDistrito = findViewById(R.id.txtInputDistrito);
        txtDireccion = findViewById(R.id.txtDireccion);
        txtTelefono = findViewById(R.id.txtTelefono);
        txtCorreo = findViewById(R.id.txtCorreo);
        txtContrasena = findViewById(R.id.txtContrasena);
        txtConfirmarc = findViewById(R.id.txtConfirmarc);
        btnSubirImagen.setOnClickListener(view -> {
            cargarImagen();
        });
        btnGuardarDatos.setOnClickListener(view -> {
            this.guardarDatos();
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void guardarDatos() {
        Cliente c;
        if (validar()) {
            c = new Cliente();
            try {
                c.setNombres(edtNombres.getText().toString());
                c.setApellidos(edtApellidos.getText().toString());
                c.setTipoDocumento(dropdownTipoDoc.getText().toString());
                c.setNumDocumento(edtNumDocU.getText().toString());
                c.setDistrito(dropdownDistrito.getText().toString());
                c.setDireccionEnvio(edtDireccion.getText().toString());
                c.setTelefono(edtTelefono.getText().toString());
                c.setCorreoElectronico(edtCorreoE.getText().toString());
                c.setId_cliente(0);

                LocalDateTime ldt = LocalDateTime.now();
                RequestBody rb = RequestBody.create(f, MediaType.parse("multipart/form-data")),somedata;
                String filename = "" +  ldt.getDayOfMonth()+(ldt.getMonthValue()+1)+ ldt.getYear()+
                                        ldt.getHour()+ldt.getMinute()+ldt.getSecond(); //Asignar un nombre al archivo imagen
                MultipartBody.Part part= MultipartBody.Part.createFormData("file",f.getName(),rb);
                somedata = RequestBody.create("profilePh" + filename, MediaType.parse("txt/plain")); //Enviando name al archivo
                this.documentoAlmacenadoViewModel.save(part,somedata).observe(this, response ->{
                    if(response.getRpta()==1){
                        c.setFoto(new DocumentoAlmacenado());
                        c.getFoto().setId(response.getBody().getId());//Asignamos foto al cliente
                        this.clienteViewModel.guardarCliente(c).observe(this, cResponse -> {
                            if(cResponse.getRpta()==1){
                                Toast.makeText(this, response.getMessage() + "Ahora procederemos a registrar sus credenciales", Toast.LENGTH_SHORT).show();
                                int idc= cResponse.getBody().getId_cliente();//Obtener id del cliente
                                Usuario u= new Usuario();
                                u.setEmail(edtCorreoE.getText().toString());
                                u.setClave(edtContraseña.getText().toString());
                                u.setVigencia(true);
                                u.setCliente(new Cliente(idc));
                                this.usuarioViewModel.save(u).observe(this,uResponse ->{
                                    Toast.makeText(this, uResponse.getMessage(), Toast.LENGTH_SHORT).show();
                                    if (uResponse.getRpta() == 1){
                                        Toast.makeText(this, "¡Sus datos han sido guardados!", Toast.LENGTH_SHORT).show();
                                        this.finish();
                                    } else {
                                        Toast.makeText(this, "¡No se pudo guardar los datos, vuelva a intentar!", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            } else {
                                Toast.makeText(this, "¡No se ha podido guardar los datos, intente de nuevo!", Toast.LENGTH_SHORT).show();
                            }
                        });
                    } else {
                        Toast.makeText(this, "¡Error al guardar los datos, vuelva a intentar!", Toast.LENGTH_SHORT).show();
                    }
                });
            } catch (Exception e) {
                warningMessage("Se ha producido un error" + e.getMessage());
            }
        } else {
            errorMesasgge("¡Por favor complete todos los campos!");
        }
    }

    private void cargarImagen() {
        Intent i= new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        i.setType("image/");
        startActivityForResult(Intent.createChooser(i,"Seleccione la Aplicación"),10);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK){
            Uri uri = data.getData();
            final  String realPath= getRealPathFromURI(uri);
            this.f = new File(realPath);
            this.imageUser.setImageURI(uri);
        }
    }

    private String getRealPathFromURI(Uri contentUri) {
        String result;
        Cursor cursor= getContentResolver().query(contentUri,null,null,null,null);
        if (cursor == null) {
            result = contentUri.getPath();
        } else {
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            result = cursor.getString(idx);
            cursor.close();
        }
        return result;
    }

    private boolean validar(){
        boolean retorno = true;
        String nombres, apellidos, numDoc, telefono, direccion,correo, pass, conpass,
                dropTipodoc, dropDistrito;

        nombres = edtNombres.getText().toString();
        apellidos = edtApellidos.getText().toString();
        numDoc = edtNumDocU.getText().toString();
        telefono = edtTelefono.getText().toString();
        direccion = edtDireccion.getText().toString();
        correo = edtCorreoE.getText().toString();
        pass = edtContraseña.getText().toString();
        conpass = edtConfirmac.getText().toString();
        dropDistrito = dropdownDistrito.getText().toString();
        dropTipodoc = dropdownTipoDoc.getText().toString();

        if(this.f==null){
            errorMesasgge("¡Debe seleccionar una foto de perfil!");
            retorno = false;
        }
        if(nombres.isEmpty()){
            txtNombres.setError("¡Ingrese su nombre!");
            retorno = false;
        }else {
            txtNombres.setErrorEnabled(false);
        }
        if(apellidos.isEmpty()){
            txtApellidos.setError("¡Ingrese sus apellidos!");
            retorno = false;
        }else {
            txtApellidos.setErrorEnabled(false);
        }
        if(numDoc.isEmpty()){
            txtInputNumeroDocU.setError("¡Ingrese número de documento!");
            retorno = false;
        }else {
            txtInputNumeroDocU.setErrorEnabled(false);
        }
        if(telefono.isEmpty()){
            txtTelefono.setError("¡Ingrese su teléfono!");
            retorno = false;
        }else {
            txtTelefono.setErrorEnabled(false);
        }
        if(direccion.isEmpty()){
            txtDireccion.setError("¡Ingrese su dirección!");
            retorno = false;
        }else {
            txtDireccion.setErrorEnabled(false);
        }
        if(correo.isEmpty()){
            txtCorreo.setError("¡Ingrese su correo!");
            retorno = false;
        }else {
            txtCorreo.setErrorEnabled(false);
        }
        if(pass.isEmpty()){
            txtContrasena.setError("¡Ingrese su contraseña!");
            retorno = false;
        }else {
            txtContrasena.setErrorEnabled(false);
        }
        if(conpass.isEmpty()){
            txtConfirmarc.setError("¡Confirme su contraseña!");
            retorno = false;
        }else {
            txtConfirmarc.setErrorEnabled(false);
        }
        if(dropDistrito.isEmpty()){
            txtInputDistrito.setError("¡Seleccione su distrito!");
            retorno = false;
        }else {
            txtInputDistrito.setErrorEnabled(false);
        }
        if(dropTipodoc.isEmpty()){
            txtInputTipoDoc.setError("¡Seleccione tipo de documento!");
            retorno = false;
        }else {
            txtInputTipoDoc.setErrorEnabled(false);
        }


        return retorno;
    }


    public void successMessage(String message){
        new SweetAlertDialog(this,
                SweetAlertDialog.SUCCESS_TYPE).setTitleText("¡Buen trabajo!")
                .setContentText(message).show();
    }

    public void errorMesasgge(String message){
        new SweetAlertDialog(this,
                SweetAlertDialog.ERROR_TYPE).setTitleText("¡Ups!")
                .setContentText(message).show();
    }

    public void warningMessage(String message){
        new SweetAlertDialog(this,
                SweetAlertDialog.WARNING_TYPE).setTitleText("¡Notificación del Sistema!")
                .setContentText(message).setConfirmText("Aceptar").show();
    }
}