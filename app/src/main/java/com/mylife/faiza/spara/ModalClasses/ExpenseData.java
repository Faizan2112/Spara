package com.mylife.faiza.spara.ModalClasses;

/**
 * Created by faizan on 02/09/2016.
 */
public class ExpenseData {
    public String getExpenseName() {
        return expenseName;
    }

    public void setExpenseName(String expenseName) {
        this.expenseName = expenseName;
    }

    public String getExpenseCost() {
        return expenseCost;
    }

    public void setExpenseCost(String expenseCost) {
        this.expenseCost = expenseCost;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    String expenseName ;
    String expenseCost ;
    int id ;

    public ExpenseData(int id, String expenseName, String expenseCost ) {
        this.expenseName = expenseName;
        this.expenseCost = expenseCost;
        this.id = id;
    }
}
