package com.programadoresperuanos.www.ejemploandroid_may2018_permisos_riesgosos;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static android.Manifest.permission.ADD_VOICEMAIL;
import static android.Manifest.permission.BODY_SENSORS;
import static android.Manifest.permission.CALL_PHONE;
import static android.Manifest.permission.CAMERA;
import static android.Manifest.permission.GET_ACCOUNTS;
import static android.Manifest.permission.PROCESS_OUTGOING_CALLS;
import static android.Manifest.permission.READ_CALENDAR;
import static android.Manifest.permission.READ_CALL_LOG;
import static android.Manifest.permission.READ_CONTACTS;
import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.READ_PHONE_STATE;
import static android.Manifest.permission.READ_SMS;
import static android.Manifest.permission.RECEIVE_MMS;
import static android.Manifest.permission.RECEIVE_SMS;
import static android.Manifest.permission.RECEIVE_WAP_PUSH;
import static android.Manifest.permission.RECORD_AUDIO;
import static android.Manifest.permission.SEND_SMS;
import static android.Manifest.permission.USE_SIP;
import static android.Manifest.permission.WRITE_CALENDAR;
import static android.Manifest.permission.WRITE_CALL_LOG;
import static android.Manifest.permission.WRITE_CONTACTS;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class MainActivity extends AppCompatActivity {

    RelativeLayout l;
    Snackbar snak1, snak2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        l = findViewById(R.id.milayout);
        snak1 = Snackbar.make(l,"Todos los permisos ya fueron aceptados.",Snackbar.LENGTH_INDEFINITE);
        snak2 = Snackbar.make(l,"Algunos permisos no fueron aceptados",Snackbar.LENGTH_INDEFINITE);
        snak2.setActionTextColor(getResources().getColor(R.color.colorProgramadoresPeruanosPrimary));
        snak2.setAction("Solicitar", new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View view) {
                requerirPermisos();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mimenu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.opcion1:
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                return true;
            case R.id.opcion2:
                startActivity(new Intent(getApplicationContext(),Main2Activity.class));
                return true;
            case R.id.opcion3:
                solicitarTodos();
                return true;
            case R.id.opcion4:
                configurarAplicacion();
                return true;
            case R.id.opcion5:
                startActivity(new Intent(getApplicationContext(),Main3Activity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case 1:
                if(grantResults[0] == PackageManager.PERMISSION_GRANTED &&
                    grantResults[1] == PackageManager.PERMISSION_GRANTED &&
                    grantResults[2] == PackageManager.PERMISSION_GRANTED &&
                    grantResults[3] == PackageManager.PERMISSION_GRANTED &&
                    grantResults[4] == PackageManager.PERMISSION_GRANTED &&
                    grantResults[5] == PackageManager.PERMISSION_GRANTED &&
                    grantResults[6] == PackageManager.PERMISSION_GRANTED &&
                    grantResults[7] == PackageManager.PERMISSION_GRANTED &&
                    grantResults[8] == PackageManager.PERMISSION_GRANTED &&
                    grantResults[9] == PackageManager.PERMISSION_GRANTED &&
                    grantResults[10] == PackageManager.PERMISSION_GRANTED &&
                    grantResults[11] == PackageManager.PERMISSION_GRANTED &&
                    grantResults[12] == PackageManager.PERMISSION_GRANTED &&
                    grantResults[13] == PackageManager.PERMISSION_GRANTED &&
                    grantResults[14] == PackageManager.PERMISSION_GRANTED &&
                    grantResults[15] == PackageManager.PERMISSION_GRANTED &&
                    grantResults[16] == PackageManager.PERMISSION_GRANTED &&
                    grantResults[17] == PackageManager.PERMISSION_GRANTED &&
                    grantResults[18] == PackageManager.PERMISSION_GRANTED &&
                    grantResults[19] == PackageManager.PERMISSION_GRANTED &&
                    grantResults[20] == PackageManager.PERMISSION_GRANTED &&
                    grantResults[21] == PackageManager.PERMISSION_GRANTED &&
                    grantResults[22] == PackageManager.PERMISSION_GRANTED &&
                    grantResults[23] == PackageManager.PERMISSION_GRANTED){
                    snak1.show();
                }else{
                    snak2.show();
                }
                break;
            default:
                break;
        }
    }

    private void requerirPermisos() {
        ActivityCompat.requestPermissions(MainActivity.this,new String[]{
                READ_CALENDAR,WRITE_CALENDAR,CAMERA,READ_CONTACTS,
                WRITE_CONTACTS,GET_ACCOUNTS,ACCESS_FINE_LOCATION,ACCESS_COARSE_LOCATION,
                RECORD_AUDIO,READ_PHONE_STATE,CALL_PHONE,READ_CALL_LOG,
                WRITE_CALL_LOG,ADD_VOICEMAIL,USE_SIP,PROCESS_OUTGOING_CALLS,
                BODY_SENSORS,SEND_SMS,RECEIVE_SMS,READ_SMS,
                RECEIVE_WAP_PUSH,RECEIVE_MMS,READ_EXTERNAL_STORAGE,WRITE_EXTERNAL_STORAGE
        },1);
    }

    private void configurarAplicacion() {
        Uri uri = Uri.fromParts("package",getPackageName(),null);
        Intent configuracion = new Intent();
        configuracion.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        configuracion.setData(uri);
        startActivity(configuracion);
    }

    private void solicitarTodos() {
        if(verificarPermisos()){
            iniciarApp();
        }else{
            justificarPermisos();
        }
    }

    private void justificarPermisos() {
        if(ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,READ_CALENDAR) ||
            ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,WRITE_CALENDAR) ||
            ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,CAMERA) ||
            ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,READ_CONTACTS) ||
            ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,WRITE_CONTACTS) ||
            ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,GET_ACCOUNTS) ||
            ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,ACCESS_FINE_LOCATION) ||
            ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,ACCESS_COARSE_LOCATION) ||
            ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,RECORD_AUDIO) ||
            ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,READ_PHONE_STATE) ||
            ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,CALL_PHONE) ||
            ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,READ_CALL_LOG) ||
            ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,WRITE_CALL_LOG) ||
            ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,ADD_VOICEMAIL) ||
            ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,USE_SIP) ||
            ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,PROCESS_OUTGOING_CALLS) ||
            ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,BODY_SENSORS) ||
            ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,SEND_SMS) ||
            ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,RECEIVE_SMS) ||
            ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,RECEIVE_WAP_PUSH) ||
            ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,RECEIVE_MMS) ||
            ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,READ_EXTERNAL_STORAGE) ||
            ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,WRITE_EXTERNAL_STORAGE)){
            snak2.show();
        }else{
            requerirPermisos();
        }
    }

    private void iniciarApp() {
        snak1.show();
    }

    private boolean verificarPermisos() {
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.M){
            return true;
        }
        if(ActivityCompat.checkSelfPermission(getApplicationContext(),READ_CALENDAR) == PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(getApplicationContext(),WRITE_CALENDAR) == PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(getApplicationContext(),CAMERA) == PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(getApplicationContext(),READ_CONTACTS) == PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(getApplicationContext(),WRITE_CONTACTS) == PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(getApplicationContext(),GET_ACCOUNTS) == PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(getApplicationContext(),ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(getApplicationContext(),ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(getApplicationContext(),RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(getApplicationContext(),READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(getApplicationContext(),CALL_PHONE) == PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(getApplicationContext(),READ_CALL_LOG) == PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(getApplicationContext(),WRITE_CALL_LOG) == PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(getApplicationContext(),ADD_VOICEMAIL) == PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(getApplicationContext(),USE_SIP) == PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(getApplicationContext(),PROCESS_OUTGOING_CALLS) == PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(getApplicationContext(),BODY_SENSORS) == PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(getApplicationContext(),SEND_SMS) == PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(getApplicationContext(),RECEIVE_SMS) == PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(getApplicationContext(),READ_SMS) == PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(getApplicationContext(),RECEIVE_WAP_PUSH) == PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(getApplicationContext(),RECEIVE_MMS) == PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(getApplicationContext(),READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(getApplicationContext(),WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
            return true;
        }
        return false;
    }
}
