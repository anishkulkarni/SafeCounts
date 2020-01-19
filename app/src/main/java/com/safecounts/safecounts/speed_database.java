package com.safecounts.safecounts;

import android.app.AlertDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class speed_database extends AppCompatActivity {

    MainActivity forDb=new MainActivity();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speed_database);
        Cursor res = forDb.myDb.getAllData();
        if(res.getCount() == 0) {
            showMessage("Error","Nothing found");
            return;
        }
        StringBuffer buffer = new StringBuffer();
        while (res.moveToNext()) {
            buffer.append("Time : "+ res.getString(1)+"\n");
            buffer.append("Speed : "+ res.getString(2)+" m/s\n");
            buffer.append("Latitude : "+ res.getString(3)+"\n");
            buffer.append("Longitude : "+ res.getString(4)+"\n\n");
        }
        TextView scroll = (TextView) this.findViewById(R.id.scroll);
        scroll.setText(buffer.toString());
    }

    public void showMessage(String title,String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
}
