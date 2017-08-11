package com.example.vipavee.testsqlite;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.vipavee.testsqlite.model.Employee;
import com.example.vipavee.testsqlite.until.DBHelper;

public class AddEmployeeActivity extends Activity {

    private EditText mFirstName,mLastName,mTel,mEmail, mDescription;
    private Button mButtonOk;
    private DBHelper mhelper;
    private int ID = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_employee);

        mFirstName = (EditText) findViewById(R.id.add_first_name);
        mLastName = (EditText) findViewById(R.id.add_last_name);
        mTel = (EditText) findViewById(R.id.add_tel);
        mEmail = (EditText) findViewById(R.id.add_email);
        mDescription = (EditText) findViewById(R.id.add_description);
        mButtonOk = (Button) findViewById(R.id.button_submit);

        mhelper = new DBHelper(this);
        mButtonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(AddEmployeeActivity.this);
                builder.setTitle((getString(R.string.add_data_title)));
                builder.setMessage(getString(R.string.add_data_message));

                builder.setPositiveButton(getString(android.R.string.ok), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Employee employee = new Employee();
                        employee.setFirstName(mFirstName.getText().toString());
                        employee.setLastName(mLastName.getText().toString());
                        employee.setTel(mTel.getText().toString());
                        employee.setEmail(mEmail.getText().toString());
                        employee.setDescription(mDescription.getText().toString());
                        if (ID == -1) {
                            mhelper.addEmployee(employee);
                        } else {
                            employee.setId(ID);
                        }
                        finish();
                    }
                });
                builder.setNegativeButton(getString(android.R.string.cancel), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                builder.show();
            }
        });
    }
}
