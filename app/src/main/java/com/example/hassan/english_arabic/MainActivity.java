package com.example.hassan.english_arabic;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//import static com.example.hassan.english_arabic.R.id.textView3;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    MaterialSearchView searchView;
    ListView lst ;
    Word word =new Word(this);
    TextView count_data;
    public String path = Environment.getExternalStorageDirectory().getAbsolutePath() ;

    ArrayList<String> rec= new ArrayList<>();

    public void show_liste(ArrayList arrayList1 ) {

        ArrayAdapter arrayAdapter = new ArrayAdapter(this,R.layout.divider,R.id.txtstring, arrayList1);
        lst.setAdapter(arrayAdapter);
    }





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        rec = word.getall();
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Arabic <-> English");

        String s = String.valueOf( word.count_());
        count_data = (TextView) findViewById(R.id.txtcount);
        count_data.setText( s);


        lst = (ListView) findViewById(R.id.listshow);
//        show_liste(rec);
        if (word.count_()!=0)
        {
            show_liste(rec);
        }
        searchView = (MaterialSearchView) findViewById(R.id.search_view);
        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if(newText != null&& !newText.isEmpty()){
                    List<String> isFound =new ArrayList<String>();

                    for(String item:rec)
                    {
                        if(item.contains(newText))
                            isFound.add(item);
                    }
                    show_liste((ArrayList) isFound);
//                    ArrayAdapter arrayAdapter = new ArrayAdapter(MainActivity.this,R.layout.divider,R.id.txtstring, rec);
//                    lst.setAdapter(arrayAdapter);

                }else {
                    show_liste(rec);
//                    ArrayAdapter arrayAdapter = new ArrayAdapter(MainActivity.this,R.layout.divider,R.id.txtstring, rec);
//                    lst.setAdapter(arrayAdapter);
                }


                return false;
            }
        });
        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int position,
                                    long id) {
                // TODO Auto-generated method stub
                Object o = adapter.getItemAtPosition(position);
                String tes =o.toString();
                Intent intent = new Intent(MainActivity.this,update_delet.class);
                intent.putExtra("StrValue", tes);
                startActivity(intent);

            }

        });
        searchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
            @Override
            public void onSearchViewShown() {

            }

            @Override
            public void onSearchViewClosed() {
//                lst = (ListView) findViewById(R.id.listshow);
                show_liste(rec);
//                ArrayAdapter arrayAdapter = new ArrayAdapter(MainActivity.this,R.layout.divider,R.id.txtstring, rec);
//                lst.setAdapter(arrayAdapter);
            }
        });




        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it;
                it = new Intent(MainActivity.this,add_word.class);
                startActivity(it);


//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);

        MenuItem item = menu.findItem(R.id.action_search);

        searchView.setMenuItem(item);

        return true;
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    public String[] trim_(String string,String key)
    {
        String [] name1 =string.split(key);

        return name1;
    }

    /***********************
     *
     *
     *          MEN HEDA LSATER LA TA7ET KELO 3AN SAVE DATABSE  TO TEXT W LOAD
     *
     *
     * **************/
//




    public void Export_to_file_(MenuItem item) throws IOException {

        save_load_data_to_and_file s = new save_load_data_to_and_file();

        if( s.Export_to_file1(rec)==true)
            Snackbar.make(getWindow().getDecorView(),"Export File Sucess", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
        else
            Snackbar.make(getWindow().getDecorView(), "Export  File Faild", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();


    }

    public void import_into_file_(MenuItem item) {
        save_load_data_to_and_file s = new save_load_data_to_and_file();
        if(s.import_into_file(word)==true)
            Snackbar.make(getWindow().getDecorView(),"Import File Sucess", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        else
            Snackbar.make(getWindow().getDecorView(), "Import  File Faild", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();


    }
/***********************
 *
 *
 *         HON BYENTEHE  SAVE DATABSE  TO TEXT W LOAD
 *
 *
 * **************/




}
