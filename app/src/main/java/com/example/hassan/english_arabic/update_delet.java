package com.example.hassan.english_arabic;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class update_delet extends AppCompatActivity {
    String value;
    EditText t;
    EditText t1;
    EditText  t2;
    String id;
    Word word = new Word(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_delet);
        t= (EditText) findViewById(R.id.txt_a);;
        t1= (EditText) findViewById(R.id.txt_e);
//         t2= (EditText) findViewById(R.id.txtnote);


        TextView v = (TextView) findViewById(R.id.txtname_up_del);
        value = getIntent().getStringExtra("StrValue").toString();


        v.setText(value .toString( ));


        String[] result= trim_(value,"->");

        v.setText(result[0].toString()+" -> "+result[1].toString());

        t.setText(result[1]);
        t1.setText(result[0]);

    }
    public String[] trim_(String string,String key)
    {
        String [] name1 =string.split(key);

        return name1;
    }


    public void update_data(View view) {
        t= (EditText) findViewById(R.id.txt_a);;
        t1= (EditText) findViewById(R.id.txt_e);
        String ar = t.getText().toString();
        String en = t1.getText().toString();
        String[] result= trim_(value,"->");

        String[] deleted = word.select(result[0].toString().trim());

        int resultat= word.update(deleted[0],en,ar);
        if(resultat > 0)
        {
            Toast.makeText(this, " update ssuceful", Toast.LENGTH_SHORT).show();
        }  else Toast.makeText(this,  "   update faile", Toast.LENGTH_SHORT).show();

    }

    public void delet_data(View view) {

        String en = t.getText().toString();
        String[] result= trim_(value,"->");
        String[] deleted = word.select(result[0].toString().trim());
        int res = word.delet(deleted[0]);
        if(res > 0)
        {
            Toast.makeText(this, " delet ssuceful", Toast.LENGTH_SHORT).show();
        }  else Toast.makeText(this, "delet faile", Toast.LENGTH_SHORT).show();

    }


    public void return_to_main(View view) {
        Intent intent = new Intent(update_delet.this,MainActivity.class);
        startActivity(intent);
    }


}
