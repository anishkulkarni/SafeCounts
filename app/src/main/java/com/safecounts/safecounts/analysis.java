package com.safecounts.safecounts;

import android.app.AlertDialog;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import java.text.DecimalFormat;


public class analysis extends AppCompatActivity {

    MainActivity forDb=new MainActivity();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analysis);
        Cursor res = forDb.myDb.getAllData();
        if(res.getCount() == 0) {
            showMessage("Error","Nothing found");
            return;
        }
        float totalSpeed= new Float(0);
        float currentSpeed = new Float(0);
        float maxSpeed=new Float(0);
        float minSpeed = new Float(100);
        int counter= new Integer(0);
        while (res.moveToNext()) {
            currentSpeed=Float.parseFloat(res.getString(2));
            totalSpeed+=currentSpeed;
            if(currentSpeed>maxSpeed)
                maxSpeed=currentSpeed;
            if(minSpeed>currentSpeed)
                minSpeed=currentSpeed;
            counter++;
        }
        DecimalFormat df = new DecimalFormat("#.##");
        TextView avg_speed = (TextView) this.findViewById(R.id.avg_speed);
        avg_speed.setText("Average Speed : "+df.format((totalSpeed*5)/(counter*18))+" kmph");
        TextView max_speed = (TextView) this.findViewById(R.id.max_speed);
        max_speed.setText("Maximum Speed : "+df.format(maxSpeed*5/18)+" kmph");
        TextView min_speed = (TextView) this.findViewById(R.id.min_speed);
        min_speed.setText("Minimum Speed : "+df.format(minSpeed*5/18)+" kmph");
        TextView data_set = (TextView) this.findViewById(R.id.data_set);
        data_set.setText("Data Samples generated : "+Integer.toString(counter));

    }

    public void showMessage(String title,String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
}
