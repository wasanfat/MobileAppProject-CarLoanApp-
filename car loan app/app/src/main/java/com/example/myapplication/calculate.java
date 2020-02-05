package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


public class calculate extends AppCompatActivity implements View.OnClickListener, SeekBar.OnSeekBarChangeListener ,AdapterView.OnItemSelectedListener {
    Button back;
    SeekBar setprice,setmonth,setinterest;
    TextView showSetPrice,showSetMonth,showSetInterest,test1,test2,test3,test0;
    Spinner promotionKey;
    double price,interest,percent,month;
    int con;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cal2);
        init();
        promotionKey.setOnItemSelectedListener(this);
        back.setOnClickListener(this);
        setprice.setOnSeekBarChangeListener(this);
        setinterest.setOnSeekBarChangeListener(this);
        setmonth.setOnSeekBarChangeListener(this);

        //set array spinner
        String[] proDetail = getResources().getStringArray(R.array.promotion);
        ArrayAdapter<String> adapterPro = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, proDetail);
        promotionKey.setAdapter(adapterPro);

        //รับค่าจาก Main
        price = Double.parseDouble(getIntent().getExtras().getString("price"));
        interest = Double.parseDouble(getIntent().getExtras().getString("interest"));
        percent = Double.parseDouble(getIntent().getExtras().getString("percent"));
        month = Double.parseDouble(getIntent().getExtras().getString("month"));

        calculate1(price,interest,percent,month);

        setBeginProgress(price,interest,percent,month);

    }

    private void setBeginProgress(double price,double interest,double percent,double month){
        //set begin progress seekbar
        setprice.setProgress((int)((percent*(100/price))/5));
        setmonth.setProgress((int)((month/6)-1));
        setinterest.setProgress((int) (interest*4));

    }

    private void init() {
        back = (Button) findViewById(R.id.back1);
        setprice = (SeekBar) findViewById(R.id.settingPrice);
        setmonth = (SeekBar) findViewById(R.id.settingMonth);
        setinterest = (SeekBar) findViewById(R.id.settingInterest);
        showSetPrice = (TextView) findViewById(R.id.perOfprice);
        showSetMonth = (TextView) findViewById(R.id.timeMonth);
        showSetInterest = (TextView) findViewById(R.id.interestOfdown);
        promotionKey = (Spinner) findViewById(R.id.promotionID);
        test1 = (TextView) findViewById(R.id.test1);
        test2 = (TextView) findViewById(R.id.test2);
        test3 = (TextView) findViewById(R.id.test3);
        test0 = (TextView) findViewById(R.id.test0);
    }

    @Override
    public void onClick (View view){
        Intent intent = null;
        switch (view.getId()){
            case R.id.back1 :
                intent = new Intent(getApplicationContext(),MainActivity.class);
                break;
        }
        startActivity(intent);
    }


    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {



        switch (seekBar.getId()){
            case R.id.settingPrice :
                double pprice = setprice.getProgress() * 5;
                this.percent = price * (pprice/100);
                showSetPrice.setText(pprice+" %");
                calculate1(price,interest,percent,month);

            case R.id.settingMonth :
                double mmonth = (setmonth.getProgress()+1);
                this.month = mmonth*6;
                showSetMonth.setText(this.month+" เดือน");
                calculate1(price,interest,percent,month);

            case R.id.settingInterest :
                double iinterest = setinterest.getProgress();
                this.interest = iinterest * 0.25;
                showSetInterest.setText(this.interest+" %");
                calculate1(price,interest,percent,month);
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {


    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {


    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (position == 0){
            con = 1;
            Toast.makeText(calculate.this, "1 : คำนวณแบบอัตราดอกเบี้ยคงที่" , Toast.LENGTH_SHORT).show();
            calculate1(price,interest,percent,month);
        }
        if (position == 1){
            con = 2;
            calculate2new(price,interest,percent,month);
        }
        if (position == 2){
            Toast.makeText(calculate.this, "2 : คำนวณแบบลดต้นลดดอก" , Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        Toast.makeText(calculate.this, "กรุณาเลือกวิธีคำนวน" , Toast.LENGTH_SHORT).show();
    }


    public void calculate1 (double price , double interest , double percent , double month){

        if(con == 2 ){
            calculate2new(price,interest,percent,month);
        }else{
            double yodjadsue = price - percent;
            double dokbearOfYear = yodjadsue * (interest/100);
            double allOfdokbear = dokbearOfYear * (month/12);
            double totalPrice = yodjadsue + allOfdokbear;
            double rinedeun = totalPrice/month;
            showTextCal( percent, yodjadsue,allOfdokbear, rinedeun);
        }
        //test0.setText(""+(int)percent);
        //test1.setText(""+(int)yodjadsue);
        //test2.setText(""+(int)allOfdokbear);
        //test3.setText(""+(int)rinedeun);
    }

    public void calculate2new (double price , double interest , double percent , double month){
        double yodjadsue = price - percent;
        double doktodeun = ((interest/100)/12);
        double xxxx = yodjadsue * (doktodeun)*Math.pow((1+doktodeun),month);
        double rinedeun = xxxx / (Math.pow((1+doktodeun),month) - 1);
        showTextCal( percent, yodjadsue,-1, rinedeun);
    }

    private void showTextCal (double percent,double yodjadsue,double allOfdokbear,double rinedeun){
            //Integer.toString
            test0.setText(""+((int) percent));
            test1.setText(""+((int)yodjadsue));
            if(allOfdokbear<0){
                test2.setText("--");
            }else{
                test2.setText(""+((int)allOfdokbear));
            }
            test3.setText(""+((int)rinedeun));
    }
}
