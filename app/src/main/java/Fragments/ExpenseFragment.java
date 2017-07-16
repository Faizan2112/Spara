package Fragments;


import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.mylife.faiza.spara.DatabaseClass.DbAdapter;
import com.mylife.faiza.spara.MapsActivity;
import com.mylife.faiza.spara.ModalClasses.ExpenseData;
import com.mylife.faiza.spara.ProfileActivity;
import com.mylife.faiza.spara.R;
import com.mylife.faiza.spara.RecyclerView.RecyclerViewData;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */

// it will we better to create seperate dialog fragment for dialogbox
// it provide more functionality

public class ExpenseFragment extends Fragment implements View.OnClickListener {
    ImageButton fabButton;
    ImageButton fabButton1;
    ImageButton fabButton2;
    ImageButton fabButton3;
    Button mAddExpense, retrieveButton , save;
    EditText mExpenseName;
    EditText mExpenseCost;
    ImageView mExpenseImage;
   // Button mSaveDetail;
    RecyclerView rv;
    RecyclerViewData recyclerViewData;
    ArrayList<ExpenseData> expenseDatas = new ArrayList<>();


    //ImageButton fabButton3 ;
    //ImageButton fabButton3 ;


    public ExpenseFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View viewExpense = inflater.inflate(R.layout.fragment_expense, container, false);
        // Inflate the layout for this fragment
      //  mAddExpense = (ImageButton) viewExpense.findViewById(R.id.expense_add_sourceof_expense_icon);
        mAddExpense = (Button) viewExpense.findViewById(R.id.main_add);

     /*   fabButton = (ImageButton) viewExpense.findViewById(R.id.expense_float_button);
        fabButton1 = (ImageButton) viewExpense.findViewById(R.id.expense_float_button_request);
        fabButton2 = (ImageButton) viewExpense.findViewById(R.id.expense_float_button_send);
        fabButton3 = (ImageButton) viewExpense.findViewById(R.id.expense_float_button_profile);*/
     /*   fabButton.setOnClickListener(this);
        fabButton3.setOnClickListener(this);*/
        rv = (RecyclerView) viewExpense.findViewById(R.id.my_recucler_view);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv.setItemAnimator(new DefaultItemAnimator());
        recyclerViewData = new RecyclerViewData(getContext(), expenseDatas);
        mAddExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });
           retrieve();
        return viewExpense;
    }

    private void retrieve() {
        DbAdapter db = new DbAdapter(getActivity());
        db.openDB();
        expenseDatas.clear();
        Cursor c = db.getallPlayers();
        while(c.moveToNext())
        {
            int id = c.getInt(0);
            String name = c.getString(1);
            String cost = c.getString(2);
            ExpenseData e = new ExpenseData(id,name,cost);
            expenseDatas.add(e);

        }
        if(!(expenseDatas.size()<1))
        {
            rv.setAdapter(recyclerViewData);
        }
    }
    public void onResume() {
        super.onResume();
        retrieve();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
           /* case R.id.expense_add_sourceof_expense_icon:
                showDialog();
                break;
*//*
            case R.id.expense_float_button:
                fabButton1.setVisibility(View.VISIBLE);
                fabButton2.setVisibility(View.VISIBLE);
                fabButton3.setVisibility(View.VISIBLE);
                break;

            case R.id.expense_float_button_send:
                Intent intentProfile = new Intent(getActivity(), MapsActivity.class);
                startActivity(intentProfile);
                break;


            case R.id.expense_float_button_profile:
                Intent intentMap = new Intent(getActivity(), ProfileActivity.class);
                startActivity(intentMap);
                break;*/

        }
    }

    private void showDialog() {

        Dialog dialog = new Dialog(this.getActivity());
        //   dialog.setCancelable(false);
        dialog.setTitle("Insert Info");
        dialog.setContentView(R.layout.activity_user_info_dialog);
        mExpenseImage = (ImageView) dialog.findViewById(R.id.dialog_insert_image);
        mExpenseName = (EditText) dialog.findViewById(R.id.dialog_insert_expenseName);
        mExpenseCost = (EditText) dialog.findViewById(R.id.dialog_insert_expenseCost);
     //   mSaveDetail = (Button) dialog.findViewById(R.id.dialog_save);
      //  mSaveDetail.setOnClickListener(new View.OnClickListener() {
        save = (Button) dialog.findViewById(R.id.dialog_save);
        save.setOnClickListener(new View.OnClickListener() {


        @Override
            public void onClick(View v) {
                save(mExpenseName.getText().toString(), mExpenseCost.getText().toString());
            }
        });
        dialog.show();

    }

    private void save(String name, String cost) {
        DbAdapter db = new DbAdapter(this.getActivity());
        db.openDB();
        long result = db.add(name, cost);
        Log.d("result","" + result);
       // Log.d("result","" + );
        if (result > 0) {
            mExpenseName.setText("");
            mExpenseCost.setText("");
        }
        else

        {
            Toast.makeText(getContext(), "Data not inserted", Toast.LENGTH_LONG).show();

        }
        db.close();
        retrieve();
    }

}
