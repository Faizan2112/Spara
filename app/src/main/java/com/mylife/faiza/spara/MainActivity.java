package com.mylife.faiza.spara;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.mylife.faiza.spara.TabLayout.TabFragment;

public class MainActivity extends AppCompatActivity  {

    Toolbar toolbar ;
    DrawerLayout drawerLayout ;
    NavigationView navigationView ;
    ActionBarDrawerToggle actionBarDrawerToggle ;

   /* private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private ListView navList;
    private android.support.v4.app.FragmentManager fragmentManager;
    private android.support.v4.app.FragmentTransaction fragmentTransaction;
    SupportMapFragment supportMapFragment ;
    @Override*/
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
// setting up toolbar
        toolbar = (Toolbar)findViewById(R.id.main_toolbar);
        toolbar.setTitle("Spara");

        setSupportActionBar(toolbar);
// setting up drawer

        drawerLayout = (DrawerLayout)findViewById(R.id.main_drawerLayout);
       drawerLayout.setDrawerListener(actionBarDrawerToggle);
        navigationView = (NavigationView)findViewById(R.id.main_nav);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.app_name,R.string.app_name);
        actionBarDrawerToggle.syncState();

        // now creating fragment manager

        final FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.main_fragmentHolder, new TabFragment()).commit();

        // now implenting on click litener on navigation menu drawer

    navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(MenuItem item) {

            drawerLayout.closeDrawers();

            if (item.getItemId() == R.id.nav_weekly) {
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.main_fragmentHolder,new MonthlyFragment()).commit();

            }

            return false;
        }
    });


    }
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.home:
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                this.startActivity(intent);
                //Toast.makeText(getApplicationContext(),"Item 1 Selected",Toast.LENGTH_LONG).show();
                break;
            case R.id.Maps:
                Intent intentMaps = new Intent(getApplicationContext(), MapsActivity.class);
                this.startActivity(intentMaps);
                //Toast.makeText(getApplicationContext(),"Item 1 Selected",Toast.LENGTH_LONG).show();
                break;

            default:
                return super.onOptionsItemSelected(item);
        }
        return true ;
    }}


     /*   supportMapFragment = SupportMapFragment.newInstance();
        drawerLayout = (DrawerLayout)findViewById(R.id.drawerLayout);
        navList = (ListView)findViewById(R.id.navList);
        ArrayList<String> navArray = new ArrayList<String>();
        navArray.add("home");
        navArray.add("Expense");
        navArray.add("Wallet");
        navArray.add("Maps");



        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_activated_1,navArray);
        navList.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        // amy use multiple

        navList.setAdapter(adapter);
        navList.setOnItemClickListener(this);

        // neeed event listenet to listnene click view
        //drawer layout should have a listener
        actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,R.string.opendrawer,R.string.closedrawer);
        drawerLayout.setDrawerListener(actionBarDrawerToggle);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        if (actionBar != null) {

            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

        loadSelection(0);
        supportMapFragment.getMapAsync(this);
    }

    private void loadSelection(int i) {
        navList.setItemChecked(i,true);
     *//*  if (i == 1)
       {
           // initialize  your fragment
           FeedActivity feedActivity = new FeedActivity();
           // now we have to replace this fragment if any other fragment is present
           // look it is replacing object
           fragmentTransaction.replace(R.id.fragmentHolder ,feedActivity);
           fragmentTransaction.commit();

       }*//*
        switch (i)
        {
            case 0 :
                HomeFragment homeFragment = new HomeFragment();
                // now we have to replace this fragment if any other fragment is present
                // look it is replacing object
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragmentHolder ,homeFragment);
                fragmentTransaction.commit();
                break;
            case 1 :
                // initialize  your fragment
                ExpenseFragment expenseFragment = new ExpenseFragment();
                // now we have to replace this fragment if any other fragment is present
                // look it is replacing object
                // every time transaction load must be there
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragmentHolder ,expenseFragment);
                fragmentTransaction.commit();
                break;
            case 2 :
                WalletFragment walletFragment = new WalletFragment();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragmentHolder ,walletFragment);
                fragmentTransaction.commit();

                break;
            case 3 :

                MapsFragment mapsFragment = new MapsFragment();
                fragmentTransaction = fragmentManager.beginTransaction().add(R.id.map,supportMapFragment);
                fragmentTransaction.replace(R.id.fragmentHolder , mapsFragment);
                fragmentTransaction.commit();
                break;

        }
        drawerLayout.closeDrawer(navList);
    }

    @Override
    public void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        actionBarDrawerToggle.syncState();
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        if(id == android.R.id.home)
        {
            // when the drawer is open it will open navlist
            if (drawerLayout.isDrawerOpen(navList))
            {
                drawerLayout.closeDrawer(navList);
            }
            else{
                drawerLayout.openDrawer(navList);
            }
        }
        return super.onOptionsItemSelected(item);
    }


    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        loadSelection(position);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

    }
}
*/

      /*  drawerLayout =(DrawerLayout)findViewById(R.id.spara_drawer_layout);
        drawerLayout.setDrawerListener(actionBarDrawerToggle);
        navList = (ListView)findViewById(R.id.spara_nav_list);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,R.string.opendrawer,R.string.closedrawer);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowHomeEnabled(true);

            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }
public void onPostCreate(Bundle savedInstanceState)
{
    super.onPostCreate(savedInstanceState);
    actionBarDrawerToggle.syncState();
}
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        if(id == android.R.id.home)
        {
            // when the drawer is open it will open navlist
            if (drawerLayout.isDrawerOpen(navList))
            {
                drawerLayout.closeDrawer(navList);
            }
            else{
                drawerLayout.openDrawer(navList);
            }
        }
        return super.onOptionsItemSelected(item);
    }

}*/