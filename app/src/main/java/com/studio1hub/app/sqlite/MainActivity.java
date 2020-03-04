package com.studio1hub.app.sqlite;

import android.content.DialogInterface;
import android.database.sqlite.SQLiteException;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DB_controller controller;
    EditText first_name;
    EditText last_name;
    TextView tvlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

      first_name=(EditText)findViewById(R.id.etfname);
      last_name=(EditText)findViewById(R.id.etlname);
      tvlist=(TextView)findViewById(R.id.tvlist);

        controller=new DB_controller(this,"",null,1);

    }

    public void btn_click(View view)
    {
        switch(view.getId())
        {
            case R.id.btnadd:
                try {
                    controller.insert_student(first_name.getText().toString(),last_name.getText().toString());
                }catch (SQLiteException e)
                {
                    Toast.makeText(this, "already exists", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btndelete:
                controller.delete_student(first_name.getText().toString());
                break;
            case R.id.btnupdate:
                AlertDialog.Builder dialog=new AlertDialog.Builder(MainActivity.this);
                dialog.setTitle("Enter new first name");
                final EditText etnewfirstname=new EditText(this);
                dialog.setView(etnewfirstname);
                dialog.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        controller.update_student(first_name.getText().toString(),etnewfirstname.getText().toString());
                    }
                });
                dialog.show();
                break;
            case R.id.btnlist:
                controller.list_all_students(tvlist);
                break;
        }
    }
}
