package com.mylife.faiza.spara.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.mylife.faiza.spara.DetailActivity;
import com.mylife.faiza.spara.Interfaces.ItemClickListener;
import com.mylife.faiza.spara.ModalClasses.ExpenseData;
import com.mylife.faiza.spara.R;

import java.util.ArrayList;
import android.view.View ;
import android.view.LayoutInflater ;

/**
 * Created by faizan on 02/09/2016.
 */
// we will pass view holder class in recycler view adapter
public class RecyclerViewData  extends RecyclerView.Adapter<RecyclerViewHolder>{

    Context c ;
    ArrayList<ExpenseData> expenseDatas ;
// here we have created constructer to call variable from MyHolder class

    public RecyclerViewData(Context ctx, ArrayList<ExpenseData> expenseDatass) {
        this.c = ctx;
        this.expenseDatas = expenseDatass;
    }

    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
      //  View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.expense_retrieve_modal,null);
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_custom_insert_view,null);

        RecyclerViewHolder holder = new RecyclerViewHolder(v);
        return holder ;

    }


    public void onBindViewHolder(RecyclerViewHolder holder, int position) {

        holder.expenseNameRetrieve.setText(expenseDatas.get(position).getExpenseName());
        holder.expenseCostRetrieve.setText(expenseDatas.get(position).getExpenseCost());
   holder.setItemClickListener(new ItemClickListener() {
       @Override
       public void onItemClick(View v, int pos) {
        Intent intentDetail = new Intent(c, DetailActivity.class);
           intentDetail.putExtra("Name", expenseDatas.get(pos).getExpenseName());
           intentDetail.putExtra("Position", expenseDatas.get(pos).getExpenseCost());
           intentDetail.putExtra("ID", expenseDatas.get(pos).getId());
           // start activity in the context
           c.startActivity(intentDetail);
       }
   });
    }


    public int getItemCount() {
        return expenseDatas.size();
    }
}
