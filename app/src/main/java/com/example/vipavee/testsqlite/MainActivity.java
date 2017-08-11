package com.example.vipavee.testsqlite;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.vipavee.testsqlite.model.Employee;
import com.example.vipavee.testsqlite.until.DBHelper;

import java.util.List;

public class MainActivity extends ListActivity {

    DBHelper mHelper;
    List<String> employee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mHelper = new DBHelper(this);
        employee = mHelper.getEmployeeList();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, employee);
        setListAdapter(adapter);


    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        Intent detail = new Intent(this, DetailActivity.class);
        String listName = employee.get(position);
        int index = listName.indexOf(" ");
        String columnId = listName.substring(0, index);

        detail.putExtra(Employee.Column.ID, columnId);
        startActivity(detail);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_add) {
            Intent addEmployee = new Intent(this, AddEmployeeActivity.class);

            startActivity(addEmployee);
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        }

        return super.onOptionsItemSelected(item);
    }
}

