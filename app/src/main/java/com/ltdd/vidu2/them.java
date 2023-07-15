package com.ltdd.vidu2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class them extends AppCompatActivity {
    EditText eSoLuong, etHSD, etNSX;
    TextView tvID,tvTen;
    Button btnLuu, btnDong;
    String action="";
    private EditText selectedDateEditText1;
    private EditText selectedDateEditText2;
    private Calendar calendar;
    int id=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them);
        getViews();
        //lay ra doi tuong intent duoc truyen sang tu adapter
        Intent data =getIntent();
        action = data.getStringExtra("ins_upd");
        //hien thi du lieu len view
        if(action.equals("update")){
            thongTinThuoc db =(thongTinThuoc) data.getExtras()
                    .getSerializable("thuoc");
            tvID.setText(db.getId());
            tvTen.setText(db.getTen());
            eSoLuong.setText(db.getsoluong());
            etHSD.setText(db.gethsd());
            etNSX.setText(db.getnsx());

        }
        //kiem tra action la insert hay update
        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(action.equals("update")){
                    //cap nhat du lieu moi va truyen ve
                    Intent updIntent= new Intent();
                    updIntent.putExtra("action", action);
                    thongTinThuoc thongTinThuoc = new thongTinThuoc();
                    thongTinThuoc.setTen(tvTen.getText().toString());
                    thongTinThuoc.setId(tvID.getText().toString());
                    thongTinThuoc.setsoluong(Integer.parseInt(eSoLuong.getText().toString()));
                    thongTinThuoc.sethsd(etHSD.getText().toString());
                    thongTinThuoc.setnsx(etNSX.getText().toString());
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("updThuoc", thongTinThuoc);
                    updIntent.putExtras(bundle);
                    //set ket qua tra ve
                    setResult(RESULT_OK,updIntent);
                    finish();
                }else if(action.equals("insert")){
                    Intent insIntent = new Intent();
                    insIntent.putExtra("action", action);
                    thongTinThuoc thongTinThuoc = new thongTinThuoc();
                    thongTinThuoc.setTen(tvTen.getText().toString());
                    thongTinThuoc.setId(tvID.getText().toString());
                    thongTinThuoc.setsoluong(Integer.parseInt(eSoLuong.getText().toString()));
                    thongTinThuoc.sethsd(etHSD.getText().toString());
                    thongTinThuoc.setnsx(etNSX.getText().toString());
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("ins", thongTinThuoc);
                    insIntent.putExtras(bundle);
                    setResult(RESULT_OK, insIntent);
                    finish();

                }
            }
        });
    }

    private void getViews(){
        tvID = findViewById(R.id.tvID);
        tvTen = findViewById(R.id.tvTen);
        eSoLuong = findViewById(R.id.eSoLuong);
        etHSD = findViewById(R.id.etHSD);
        etNSX = findViewById(R.id.etNSX);
        btnLuu = findViewById(R.id.btnLuu);
        btnDong = findViewById(R.id.btnDong);
    }
}