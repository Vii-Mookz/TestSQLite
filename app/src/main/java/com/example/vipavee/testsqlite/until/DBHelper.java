package com.example.vipavee.testsqlite.until;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.vipavee.testsqlite.model.Employee;

import java.util.ArrayList;
import java.util.List;


public class DBHelper extends SQLiteOpenHelper {

    private final String TAG = getClass().getSimpleName();

    private SQLiteDatabase sqLiteDatabase;

    public DBHelper(Context context) {
        super(context, Employee.DATABASE_NAME, null, Employee.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_Employee_TABLE = String.format("CREATE TABLE %s " +
                        "(%s INTEGER PRIMARY KEY  AUTOINCREMENT, %s TEXT, %s TEXT, %s TEXT, %s TEXT, %s TEXT)",
                Employee.TABLE,
                Employee.Column.ID,
                Employee.Column.FIRST_NAME,
                Employee.Column.LAST_NAME,
                Employee.Column.TEL,
                Employee.Column.EMAIL,
                Employee.Column.DESCRIPTION);

        Log.i(TAG, CREATE_Employee_TABLE);

        // create employee table
        db.execSQL(CREATE_Employee_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String DROP_FRIEND_TABLE = "DROP TABLE IF EXISTS " + Employee.TABLE;

        db.execSQL(DROP_FRIEND_TABLE);

        Log.i(TAG, "Upgrade Database from " + oldVersion + " to " + newVersion);

        onCreate(db);
    }

    public List<String> getEmployeeList() {
        List<String> employee = new ArrayList<String>();

        sqLiteDatabase = this.getWritableDatabase();

        Cursor cursor = sqLiteDatabase.query
                (Employee.TABLE, null, null, null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }
        while (!cursor.isAfterLast()) {
            employee.add(cursor.getLong(0) + " " +
                    cursor.getString(1) + " " +
                    cursor.getString(2));
            cursor.moveToNext();
        }
        sqLiteDatabase.close();

        return employee;
    }

    public void addEmployee(Employee employee) {
        sqLiteDatabase = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        //value.put(Emplyee.Column.ID,friend.getId());
        values.put(Employee.Column.FIRST_NAME, employee.getFirstName());
        values.put(Employee.Column.LAST_NAME, employee.getLastName());
        values.put(Employee.Column.TEL, employee.getTel());
        values.put(Employee.Column.EMAIL, employee.getEmail());
        values.put(Employee.Column.DESCRIPTION, employee.getDescription());

        sqLiteDatabase.insert(Employee.TABLE, null, values);

        sqLiteDatabase.close();
    }

    public Employee getEmployee(String id) {
        sqLiteDatabase = this.getReadableDatabase();

        Cursor cursor = sqLiteDatabase.query(Employee.TABLE,//Teble Name
                null,// column same Select *
                Employee.Column.ID + " =? ", //Condition where id =?
                new String[]{id},//argument ที่ใส่แทน ? เป็น  เลข id
                null, // groupBy
                null,// Having
                null);// orderBy

        if (cursor != null) {
            cursor.moveToFirst();
        }
        Employee employee = new Employee();

        employee.setId((int) cursor.getLong(0));
        employee.setFirstName(cursor.getString(1));
        employee.setLastName(cursor.getString(2));
        employee.setTel(cursor.getString(3));
        employee.setEmail(cursor.getString(4));
        employee.setEmail(cursor.getString(5));

        return employee;
    }

    public void updateEmployee(Employee employee) {
        sqLiteDatabase = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Employee.Column.ID, employee.getId());
        values.put(Employee.Column.FIRST_NAME, employee.getFirstName());
        values.put(Employee.Column.LAST_NAME, employee.getLastName());
        values.put(Employee.Column.EMAIL, employee.getEmail());
        values.put(Employee.Column.DESCRIPTION, employee.getDescription());

        int row = sqLiteDatabase.update(Employee.TABLE,
                values,
                Employee.Column.ID + " = ?",
                new String[]{String.valueOf(employee.getId())});

        sqLiteDatabase.close();


}


    public void deleteEmployee(String id) {

        sqLiteDatabase = this.getWritableDatabase();

    /*sqLiteDatabase.delete(Friend.TABLE, Friend.Column.ID + " = ? ",
            new String[] { String.valueOf(friend.getId()) });*/
        sqLiteDatabase.delete(Employee.TABLE, Employee.Column.ID + " = " + id, null);

        sqLiteDatabase.close();
    }
}
