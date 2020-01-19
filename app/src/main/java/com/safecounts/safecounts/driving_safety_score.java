package com.safecounts.safecounts;

import android.app.AlertDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.text.DecimalFormat;

import static java.lang.StrictMath.abs;

public class driving_safety_score extends AppCompatActivity {

    MainActivity forDb =new MainActivity();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driving_safety_score);
        int higher=0,lower=0,counter=0;
        float currentSpeed=0,totalSpeed=0;
        Cursor res = forDb.myDb.getAllData();
        if(res.getCount() == 0) {
            showMessage("Error","Nothing found");
            return;
        }
        while (res.moveToNext()) {
            currentSpeed=Float.parseFloat(res.getString(2));
            totalSpeed+=currentSpeed;
            if(currentSpeed>(30.0*18.0/5.0))
                higher++;
            if(currentSpeed<=(30.0*18.0/5.0))
                lower++;
            counter++;
        }
        TextView tv =(TextView) this.findViewById(R.id.score_average_speed);
        DecimalFormat df = new DecimalFormat("#.##");
        tv.setText("Average Speed : "+df.format((totalSpeed*5)/(counter*18))+" kmph");
        tv = (TextView) this.findViewById(R.id.score_higher);
        tv.setText("Speed captures higher than speed limit : "+Integer.toString(higher)+" out of "+Integer.toString(counter));
        tv=(TextView)this.findViewById(R.id.score_lower);
        tv.setText("Speed captures lower than speed limit : "+Integer.toString(lower)+" out of "+Integer.toString(counter));
        float safetyScore=0;
        safetyScore+=(float)(lower/counter*100.0);
        //safetyScore+=(abs(30-(totalSpeed*5)/(counter*18))*10);
        tv=(TextView)this.findViewById(R.id.safety_score_value);
        tv.setText(Float.toString(safetyScore));
    }

    public void showMessage(String title,String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }

}
