package com.example.travelling;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;



import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static java.lang.System.exit;

public class SelectMonth extends AppCompatActivity {
    List<String> fileSet = new ArrayList<>();
    List<String> datas = new ArrayList<>();
    //TextView textView;

    String selectedTxt;
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
            //testforput();
            //nitViews();
            //loadDatas();

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

        DateCard dc = new DateCard();


        dc.setText("Test1");
        dcList.add(dc);

        dc.setText("Test2");
        dcList.add(dc);

        dc.setText("Test3");
        dcList.add(dc);

        dc.setText("Test4");
        dcList.add(dc);

        return dcList;
    }

    /*rivate void loadDatas(){

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
        dcList.add(dc);
        mAdapter.notifyDataSetChanged();
    }



    private void CreateOrFindFolder(){

        //textView.setText(textView.getText()+"\n"+thisMonth+" "+thisYear);
        try{
            String current_month = thisMonth+"_"+thisYear;
            File internalStorage = new File(Environment.getExternalStorageState());
            internalStorage.mkdir();
            String path = internalStorage.getAbsolutePath();
            TextView tv = findViewById(R.id.debug);
            tv.setText("path");
            //continue
            if(path.contains(current_month)){
                //toast message file already exist
                Toast.makeText(this, "The file already exist", Toast.LENGTH_SHORT).show();

                //and exit this method
                return;
            }



            FileOutputStream fos = getApplicationContext().openFileOutput(current_month+".txt",this.MODE_PRIVATE);
            OutputStreamWriter osw = new OutputStreamWriter(fos);

        //osw.write("test1"+","+"test2"+","+"test3"+"\n"+"2test1"+"2test2");
            osw.close();
            fos.close();

            addFile(current_month);
        }
        catch(FileNotFoundException fnfe){
            fnfe.printStackTrace();
        }
        catch(IOException io){
            io.printStackTrace();
        }




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

