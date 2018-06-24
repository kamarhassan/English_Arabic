package com.example.hassan.english_arabic;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class add_word extends AppCompatActivity {
    Word word = new Word(this);
    EditText txt_eng;
    EditText txt_ar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_word);

        txt_ar= (EditText) findViewById(R.id.txt_arabic);
        txt_eng= (EditText) findViewById(R.id.txt_english);

    }
    save_load_data_to_and_file s;

    public void add_word_in_data_base(View view) {
        String english_word = txt_eng.getText().toString();
        String arabic_word  = txt_ar.getText().toString();
        if (english_word.equals("")) {
            Toast.makeText(this, "insert word ENGLISH  please", Toast.LENGTH_SHORT).show();
        } else {
            if (arabic_word.equals("")) {
                Toast.makeText(this, "insert word ARABIC  please", Toast.LENGTH_SHORT).show();
            } else {

                Boolean add =s.string_added(word.getall(),english_word,arabic_word);

                if(add==true){
                    Boolean resu = word.insertdata(english_word, arabic_word);
                    if (resu == true) {
                        Toast.makeText(this, "insert ok", Toast.LENGTH_SHORT).show();
                        txt_ar.setText("");
                        txt_eng.setText("");


                    } else Toast.makeText(this, "insert faild", Toast.LENGTH_SHORT).show();

                }else {
                    Toast.makeText(this, "this word is old added ", Toast.LENGTH_SHORT).show();
                }


//            }else

            }
        }


    }

//    public Boolean compare(ArrayList arr,String text)
//    {
//        if(arr.contains(text))
//            return true ;
//        return false;
//    }




    public void return_to_main(View view) {
        Intent intent = new Intent(add_word.this,MainActivity.class);
        startActivity(intent);
    }
}

