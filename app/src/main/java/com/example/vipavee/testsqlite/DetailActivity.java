package com.example.vipavee.testsqlite;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vipavee.testsqlite.model.Employee;
import com.example.vipavee.testsqlite.until.DBHelper;

public class DetailActivity extends Activity {
    DBHelper mHelper;

    private TextView mFirstName,mLastName,mTel,mEmail, mDescription;
    private String id = "";

    private Button mButtonEdit, mButtonDelete;
    private Employee employee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mHelper = new DBHelper(this);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            id = bundle.getString(Employee.Column.ID);
        }
        setContentView(R.layout.activity_detail);
        mFirstName = (TextView) findViewById(R.id.detail_first_name);
        mLastName = (TextView) findViewById(R.id.detail_last_name);
        mTel = (TextView) findViewById(R.id.detail_tel);
        mEmail = (TextView) findViewById(R.id.detail_email);
        mDescription = (TextView) findViewById(R.id.detail_description);
        mButtonEdit = (Button) findViewById(R.id.btn_edit);
        mButtonDelete = (Button) findViewById(R.id.btn_delete);

        mButtonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder =
                        new AlertDialog.Builder(DetailActivity.this);
                builder.setTitle(getString(R.string.alert_title));
                builder.setMessage(getString(R.string.alert_message));

                builder.setPositiveButton(getString(android.R.string.ok),
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                mHelper.deleteEmployee(id);

                                Toast.makeText(getApplication(),
                                        "Deleted", Toast.LENGTH_LONG).show();
                                finish();
                            }
                        });

                builder.setNegativeButton(getString(android.R.string.cancel), null);

                builder.show();

            }
        });

        mButtonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent updateIntent = new Intent(DetailActivity.this, AddEmployeeActivity.class);

                updateIntent.putExtra(Employee.Column.ID, employee.getId());
                updateIntent.putExtra(Employee.Column.FIRST_NAME, employee.getFirstName());
                updateIntent.putExtra(Employee.Column.LAST_NAME, employee.getLastName());
                updateIntent.putExtra(Employee.Column.TEL, employee.getTel());
                updateIntent.putExtra(Employee.Column.EMAIL, employee.getEmail());
                updateIntent.putExtra(Employee.Column.DESCRIPTION, employee.getDescription());

                startActivity(updateIntent);
                overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
            }
        });
        initialData();
    }
    @Override
    protected void onResume() {
        super.onResume();
        initialData();
    }

    private void initialData() {
        employee = mHelper.getEmployee(id);
        mFirstName.setText(employee.getFirstName());
        mLastName.setText(employee.getLastName());
        mTel.setText(employee.getTel());
        mEmail.setText(employee.getEmail());
        mDescription.setText(employee.getDescription());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        overridePendingTransition(android.R.anim.fade_in,
                android.R.anim.fade_out);

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(android.R.anim.fade_in,
                android.R.anim.fade_out);
    }
}
