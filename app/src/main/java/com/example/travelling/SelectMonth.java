package com.example.travelling;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import static java.lang.System.exit;

public class SelectMonth extends AppCompatActivity {

    Calendar calendar = Calendar.getInstance();
    int thisYear = calendar.get(Calendar.YEAR);
    int thisMonth = calendar.get(Calendar.MONTH);

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    ArrayList<DateCard> dcList = new ArrayList<DateCard>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_select_month);


            recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        layoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(layoutManager);

        //String[] myDataset = {"test1","test2","test3","test4","test5","test6","test7","test8","test9"};

        mAdapter = new MyAdapter(this,getMyList());
        recyclerView.setAdapter(mAdapter);

    }



    private ArrayList<DateCard> getMyList(){

        //Code of getting file name from storage
        //And add to the list to show

        File internalStorage = new File(this.getFilesDir(),"files");
        internalStorage.mkdir();
        File[] fileList = internalStorage.listFiles();



        DateCard dc = new DateCard();

        for(int i = 0 ; i<fileList.length ; i++){
            dc = new DateCard();
            dc.setText(fileList[i].getName());
            dcList.add(dc);
        }

        Collections.reverse(dcList);
        /*TextView tv = (TextView)findViewById(R.id.debug);
        for(int j =0 ; j<4;j++) {
            tv.setText(tv.getText()+dcList.get(j).getText());
        }*/

        return dcList;
    }

    /*private void loadDatas(){

        String[] fileList = this.fileList();
        if(fileList.length>0) {
            for (String file : fileList) {
                String[] temp = file.split("\\.");
                datas.add(temp[0]);
            }



        }
        //add way to read file name from a txt file
    }*/

    private void addFile(String fn){

        DateCard dc = new DateCard();
        dc.setText(fn);
        Collections.reverse(dcList);//Return to normal order
        dcList.add(dc);
        Collections.reverse(dcList);//Set reverse order for display
        mAdapter.notifyDataSetChanged();
    }



    private void CreateOrFindFolder(){

        //textView.setText(textView.getText()+"\n"+thisMonth+" "+thisYear);
        try{
            String current_month = thisMonth+"_"+thisYear;
            File internalStorage = new File(this.getFilesDir(),"files");
            internalStorage.mkdir();
            File[] fortest = internalStorage.listFiles();
            String path = internalStorage.getAbsolutePath();
            TextView tv = findViewById(R.id.debug);
            tv.setText(path);
            for(int i =0 ; i<fortest.length ; i++){
                tv.setText(tv.getText()+"\n"+fortest[i].getName());
                if(fortest[i].toString().contains(current_month)){
                    //toast message file already exist

                    DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener(){
                        @Override
                        public void onClick(DialogInterface dialog, int bol){
                            switch(bol){
                                case DialogInterface.BUTTON_POSITIVE:
                                    //return to main and set to current month file
                                    break;
                                case DialogInterface.BUTTON_NEGATIVE:
                                    break;
                            }
                        }

                    };
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setMessage("Current month file has been already created. Return to current record?").
                            setNegativeButton("No",dialogClickListener).
                            setPositiveButton("Yes",dialogClickListener).
                            show();

                    //and exit this method
                    return;
                }

            }
           //tv.setText(path);
            //continue



            File nf = new File(internalStorage,current_month+".txt");
            FileWriter fw = new FileWriter(nf);
            fw.append("aba");
            fw.flush();
            fw.close();
          /*  FileOutputStream fos = getApplicationContext().openFileOutput(nf);
            OutputStreamWriter osw = new OutputStreamWriter(fos);

        //osw.write("test1"+","+"test2"+","+"test3"+"\n"+"2test1"+"2test2");
            osw.close();
            fos.close();*/

            addFile(nf.toString());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
       /* catch(FileNotFoundException fnfe){
            fnfe.printStackTrace();
        }
        catch(IOException io){
            io.printStackTrace();
        }*/




        /*File[] f = sdCardRoot.listFiles();
        for(File a:f){
            textView.setText(textView.getText()+"\n"+a.getPath());
        }*/

    }

    /*private void loadTxt(String selectedDate){

        try {
            InputStream ips = this.openFileInput(selectedTxt+".txt");
            if (ips != null) {
                InputStreamReader ipsr = new InputStreamReader(ips);
                BufferedReader br = new BufferedReader(ipsr);
                String line;
                Intent intent = new Intent(SelectMonth.this,MainActivity.class);
                int count=0;
                while ((line = br.readLine()) != null) {
                    intent.putExtra("record"+count,line);
                    textView.setText(textView.getText()+"\n"+line);
                    count++;
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.selectmonthmenu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.addmonth:
                CreateOrFindFolder();
                break;
            default: break;
        }
        return true;
    }


        private void testforput(){
            try{
                FileOutputStream fos = getApplicationContext().openFileOutput("10_2019.txt",this.MODE_PRIVATE);
                OutputStreamWriter osw = new OutputStreamWriter(fos);

                osw.write("test1"+","+"test2"+","+"test3"+"\n"+"2test1"+"2test2");
                osw.close();
                fos.close();

            }
            catch(FileNotFoundException fnfe){
                fnfe.printStackTrace();
            }
            catch(IOException io){
                io.printStackTrace();
            }
        }
    }

