package com.ltdd.vidu2;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.ltdd.vidu2.R;
import com.ltdd.vidu2.thongTinThuoc;
import com.ltdd.vidu2.thuocAdapter;
import com.ltdd.vidu2.thuocDB;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView lvThuoc;
    List<thongTinThuoc> lsData = new ArrayList<thongTinThuoc>();
    thuocAdapter adapter;
    com.ltdd.vidu2.thuocDB thuocDB = new thuocDB(MainActivity.this);

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvThuoc = findViewById(R.id.lvThuoc);
        //tao doi tuong DanhBaDB thuc hien initdata

        thuocDB.initData();
        //select du lieu do vao lsData de hien len listview
        lsData = thuocDB.getAllThuoc();
        //khoi tao adapter load view va data
        adapter = new thuocAdapter(MainActivity.this,lsData);
        //set adapter cho listview
        lvThuoc.setAdapter(adapter);

    }

    public void onInsThuocClick(View view) {
        Intent insIntent = new Intent(this, them.class);
        insIntent.putExtra("ins_upd", "insert");
        insThuoc.launch(insIntent);
    }
    ActivityResultLauncher<Intent> insThuoc= registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    //insert du lieu
                    if(result.getResultCode()==RESULT_OK){
                        Intent data = result.getData();
                        if(data.getStringExtra("action").equals("insert")){
                            //insert du lieu vao database
                            thongTinThuoc db =(thongTinThuoc) data.getExtras().getSerializable("ins");
                            thuocDB.insThuoc(db);
                        }
                    }
                }
            });
}