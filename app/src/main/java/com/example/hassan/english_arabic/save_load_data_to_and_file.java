package com.example.hassan.english_arabic;

import android.os.Environment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by Hassan on 12/16/2017.
 */

public class save_load_data_to_and_file {


    public static String[] trim_(String string, String key) {
        String[] name1 = string.split(key);

        return name1;
    }

    public static String path = Environment.getExternalStorageDirectory().getAbsolutePath();


    public boolean Export_to_file1(ArrayList rec) throws IOException {
        File file = new File(path + "/Saved_word_data_base_to_file.txt");


//        Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_LONG).show();

        if (Save(file, rec) == true)
            return true;
        else return false;

    }


    public static boolean Save(File file, ArrayList<String> data) throws IOException {
        FileOutputStream fos=new FileOutputStream(file);
        for(int i = 0; i<data.size(); i++) {
           fos.write(data.get(i).trim().getBytes());
           fos.write("\n" .getBytes());
       }


        return false;
    }


    public boolean import_into_file(Word word) {


        File file = new File(path + "/Saved_word_data_base_to_file.txt");

        ArrayList<String> loadWorld = load(file);

        int count = 0;

// /

        for (int i = 0; i < loadWorld.size(); i++) {
            String[] result = trim_(loadWorld.get(i), "->");
//
            word.insertdata(result[0].toString().trim(), result[1].toString().trim());
            count++;
//
        }

        if (count == loadWorld.size())
            return true;
        else
            return false;
    }

    private ArrayList<String> load(File file) {
        ArrayList<String> arr = new ArrayList<>();
        BufferedReader br = null;
        FileReader fr = null;

        try {
            fr = new FileReader(file);
            br = new BufferedReader(fr);

            String read_line;
            while ((read_line = br.readLine()) != null) {
                arr.add(read_line.toString());
            }


        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            try {

                if (br != null)
                    br.close();

                if (fr != null)
                    fr.close();

            } catch (IOException ex) {

                ex.printStackTrace();

            }
        }


        return arr;
    }

    public static boolean  string_added(ArrayList<String> exist, String new_word_ar, String new_word_eng) {

        for(int i=0;i<exist.size();i++) {
            String[] result = trim_(exist.get(i), "->");
            if(result[0].toString().trim().equals(new_word_eng.trim()) )
                if(result[1].toString().trim().equals(new_word_ar.trim()))//|| result[1].toString().trim().equals(new_word_ar) )
                      return  false;
        }
        return true;
    }
}