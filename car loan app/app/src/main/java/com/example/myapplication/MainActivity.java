package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    Button calculate;
    EditText eprice,einterest,epercent,emonth;
    //double price,interest,percent,month;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        calculate.setOnClickListener(this);
    }

    private void init() {
        calculate = (Button) findViewById(R.id.Calculate);
        eprice = (EditText) findViewById(R.id.priceID);
        einterest = (EditText) findViewById(R.id.interastID);
        epercent = (EditText) findViewById(R.id.percentID);
        emonth = (EditText) findViewById(R.id.monthID);
    }

    @Override
    public void onClick( View view ) {

        if(eprice.getText().toString().matches("") ){
            Toast.makeText(MainActivity.this, "กรุณากรอกข้อมูลให้ครบถ้วน" , Toast.LENGTH_SHORT).show();
            return;
        }else if(einterest.getText().toString().matches("")){
            Toast.makeText(MainActivity.this, "กรุณากรอกข้อมูลให้ครบถ้วน" , Toast.LENGTH_SHORT).show();
            return;
        }else if(epercent.getText().toString().matches("")){
            Toast.makeText(MainActivity.this, "กรุณากรอกข้อมูลให้ครบถ้วน" , Toast.LENGTH_SHORT).show();
            return;
        }else if(emonth.getText().toString().matches("")){
            Toast.makeText(MainActivity.this, "กรุณากรอกข้อมูลให้ครบถ้วน" , Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intent = null;
        switch (view.getId()){
            case R.id.Calculate :
                intent = new Intent(getApplicationContext(),calculate.class);

                intent.putExtra("price",eprice.getText().toString());

                intent.putExtra("interest",einterest.getText().toString());

                intent.putExtra("percent",epercent.getText().toString());

                intent.putExtra("month",emonth.getText().toString());


                break;
        }
        startActivity(intent);
    }

    /* คิดต่อว่า เอ่า intent = new Intent(getApplicationContext(),calculate.class);
               break;
       ไปใส่ใน cal1 แล้ว cal1 รับค่า เดือน.....ดอกเบี้ย แล้วก้อรับ con ด้วย อาจจะใช้ if เช็ค con แล้วเอาไปคิดวิธีคำนวนอีกที
    */



}
