package com.mylife.faiza.spara.RecyclerView;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.mylife.faiza.spara.Interfaces.ItemClickListener;
import com.mylife.faiza.spara.R;
//import com.mylife.faiza.spara.R;

/**
 * Created by faizan on 02/09/2016.
 */
// for holding we will use  view holder extend view holder
// and implement on click listener on separate iterface class
    // holding the objects of retrive layout
    // this layout will we insertes into reccler view
    // we have to get this layout in ViewBinder
public class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
   TextView expenseNameRetrieve ;
    TextView expenseCostRetrieve ;
    TextView expenseDateRetrieve ;
    TextView expenseTimeRetrieve ;
    ItemClickListener itemClickListener ;




    public RecyclerViewHolder(View itemView) {
        super(itemView);
  //    expenseNameRetrieve = (TextView) itemView.findViewById(R.id.retrieve_expense_name);
  //      expenseCostRetrieve = (TextView)itemView.findViewById(R.id.retrieve_expense_cost);
        expenseNameRetrieve = (TextView) itemView.findViewById(R.id.insert_expenseName);
        expenseCostRetrieve = (TextView)itemView.findViewById(R.id.insert_expenseCost);


       itemView.setOnClickListener(this);
   /*
        expenseDateRetrieve = (TextView)itemView.findViewById(R.id.retrieve_expense_date);
        expenseTimeRetrieve = (TextView)itemView.findViewById(R.id.retrieve_expense_time);
        itemView.setOnClickListener(this);*/
    }

    @Override
    public void onClick(View v) {
        this.itemClickListener.onItemClick(v, getLayoutPosition());

    }

    public void setItemClickListener(ItemClickListener ic)
    {
        this.itemClickListener = ic ;
    }
}
