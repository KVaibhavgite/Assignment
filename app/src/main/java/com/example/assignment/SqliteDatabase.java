package com.example.assignment;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
public class SqliteDatabase extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 101;
    private static final String DATABASE_NAME = "MemberDetails";
    private static final String TABLE_Member = "Memberlist";
    private static final String COLUMN_ID = "_id";


    private static final String COL_username = "EmployeeId";
    private static final String COL_firstName = "FirstName";
    private static final String COL_lastName = "LastName";
    private static final String COL_number = "MobileNo";
   // private static final String COL_address = "Address";

    SqliteDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_MEMBER_TABLE = "CREATE TABLE "
                + TABLE_Member + "(" + COLUMN_ID + " INTEGER PRIMARY KEY,"
                + COL_username + " TEXT,"
                + COL_firstName + " TEXT,"
                + COL_lastName + " TEXT,"
                + COL_number + " INTEGER" +")";
        db.execSQL(CREATE_MEMBER_TABLE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_Member);
        onCreate(db);
    }
    ArrayList<Member> listMember() {
        String sql = "select * from " + TABLE_Member;
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Member> storeMember = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {
                int id = Integer.parseInt(cursor.getString(0));
                String username = cursor.getString(1);
                String firstName = cursor.getString(2);
                String lastName = cursor.getString(3);
                String number = cursor.getString(4);
                //String address = cursor.getString(5);

                storeMember.add(new Member(id,username,firstName,lastName,number));
            }
            while (cursor.moveToNext());
        }
        cursor.close();
        return storeMember;
    }
    void addMembers(Member member) {
        ContentValues values = new ContentValues();
        values.put(COL_username, member.getEmployeeId());
        values.put(COL_firstName, member.getFirstName());
        values.put(COL_lastName, member.getLastName());
        values.put(COL_number, member.getMobileNo());
       // values.put(COL_address, member.getAddress());
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_Member,null, values);
    }
    void updateMembers(Member member) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID,member.getId());
        values.put(COL_username, member.getEmployeeId());
        values.put(COL_firstName, member.getFirstName());
        values.put(COL_lastName, member.getLastName());
        values.put(COL_number, member.getMobileNo());
        //values.put(COL_address, member.getAddress());
        SQLiteDatabase db = this.getWritableDatabase();
        db.update(TABLE_Member, values, COLUMN_ID + " = ?", new String[]{String.valueOf(member.getId())});
    }
    void deleteMember(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_Member, COLUMN_ID + " = ?", new String[]{String.valueOf(id)});
    }
}