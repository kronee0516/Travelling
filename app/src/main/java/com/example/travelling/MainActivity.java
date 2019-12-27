package com.example.travelling;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    Calendar calendar = Calendar.getInstance();
    int thisYear = calendar.get(Calendar.YEAR);
    int thisMonth = calendar.get(Calendar.MONTH);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        requestForPermission();

        //loadTxt();


    }



    @Override
    protected  void onActivityResult(int requestCode , int resultCode , Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 1:
            if (requestCode == Activity.RESULT_OK) {
                String result = data.getStringExtra("result");
            } else {

            }
            break;

            case 2:

            break;

            default:
            break;
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mainmenu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        Intent intent;
        switch(item.getItemId()){
            case R.id.slot_1_select_month:
                intent = new Intent(this, SelectMonth.class);
                startActivity(intent);
                break;
                //startActivityForResult(i,1);
            case R.id.slot_2_add_record:
                intent = new Intent(this, AddRecord.class);
            case R.id.slot_3_output:
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

 /*   private void loadTxt(){

        try {
            InputStream ips = this.openFileInput(thisMonth+"_"+thisYear+".txt");
            if (ips != null) {
                InputStreamReader ipsr = new InputStreamReader(ips);
                BufferedReader br = new BufferedReader(ipsr);
                String line;
                LinearLayout linearLayout = (LinearLayout)findViewById(R.id.datacontainer);


                int count=0;
                while ((line = br.readLine()) != null) {
                    TextView tv = new TextView(this);
                    tv.setText(line);
                    linearLayout.addView(tv);
                }
                count=0;
                ips.close();
                ipsr.close();
                //this.finish();
            } else {
                Toast.makeText(this, "Some Errors occur.Can't find/create current month file", Toast.LENGTH_LONG).show();
            }
        }
        catch(IOException ioe){
            ioe.printStackTrace();
        }

    }*/
    private void requestForPermission(){
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            // Permission is not granted
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
            } else {
                // No explanation needed; request the permission
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        1);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        } else {
            // Permission has already been granted
        }
    }

}
