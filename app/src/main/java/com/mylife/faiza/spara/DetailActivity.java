package com.mylife.faiza.spara;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.mylife.faiza.spara.DatabaseClass.DbAdapter;

public class DetailActivity  extends AppCompatActivity {
    ImageView img ;
    EditText nameTxt , costText ;
    Button btnUpdate ,btnDelete ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
       /*
        Bundle extra = getIntent().getExtras();
        String title = extra.getString("title");
        TextView txtitem = (TextView) findViewById(R.id.txtitemdetailstitle);
        txtitem.setText(title);
*/


        Intent i = getIntent();
        //    Bundle extra = getIntent().getExtras();
        //  String name = extra.getString("Name");

        final String name = i.getExtras().getString("Name");
        final String cost = i.getExtras().getString("Position");
        final int id = i.getExtras().getInt("ID");

        // final int id = extra.getInt("ID");

/*

        final String name = i.getExtras().getString("Name").toString();
         it will give error because id ids null..
         it will take all string
        final String cost = i.getExtras().getString("Position").toString();

        final int id = i.getExtras().getInt("ID");
*/

        btnUpdate=(Button)findViewById(R.id.detail_update);
        btnDelete=(Button)findViewById(R.id.detail_delete);
        nameTxt = (EditText)findViewById(R.id.detail_name);
        costText= (EditText) findViewById(R.id.detail_cost);
        nameTxt.setText(name);
        costText.setText(cost);
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                update(id, nameTxt.getText().toString(), costText.getText().toString());
                //update(id,);
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delete(id);
            }
        });

    }

    public void update (int id ,String newName , String newPos )
    {
        DbAdapter db = new DbAdapter(this);
        db.openDB();
        long result = db.update(id,newName,newPos);
        if(result>0)
        {
            nameTxt.setText(newName);
            costText.setText(newPos);
            Toast.makeText(getApplicationContext(), "change occured", Toast.LENGTH_LONG).show();

        }else
        {
            Toast.makeText(getApplicationContext(),"unable to update",Toast.LENGTH_LONG).show();
        }
        db.close();

    }
    public void delete (int id)
    {
        DbAdapter db = new DbAdapter(this);
        db.openDB();
        long result = db.delete(id);
        if(result>0)
        {
            this.finish();
        }else
        {
            Toast.makeText(getApplicationContext(),"unable to update",Toast.LENGTH_LONG).show();
        }
        db.close();

    }


}
