package cn.geq.ahgf.demo5_sdcader;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaRouter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity  implements View.OnClickListener {
    EditText edname;
    EditText edpass;
    CheckBox chbox;
    Button bulogin;
    Button buselect;
    ListView lv;
    MyOpenHelper myopenHelper = new MyOpenHelper(this,"user",null,1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edname = findViewById(R.id.ed_name);
        edpass = findViewById(R.id.ed_pass);
        chbox = findViewById(R.id.ch_box);
        bulogin = findViewById(R.id.bu_login);
        buselect = findViewById(R.id.bu_select);
        lv = findViewById(R.id.lv);
        bulogin.setOnClickListener(this);

        buselect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase   db =  myopenHelper.getWritableDatabase();
               Cursor cursor =  db.rawQuery("SELECT * FROM user",null);
                if(cursor!=null&&cursor.getCount()>1){
                    while(cursor.moveToNext()){
                        String name = cursor.getString(2);
                       // String pass =  cursor.getString(3);
                        Toast.makeText(MainActivity.this,name,Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        String name = edname.getText().toString().trim();
        String pass = edpass.getText().toString().trim();
        if(TextUtils.isEmpty(name)||TextUtils.isEmpty(pass)){
            Toast.makeText(this, "用户名密码不能为空！", Toast.LENGTH_SHORT).show();
        }else{
            SQLiteDatabase   db =  myopenHelper.getWritableDatabase();


            db.execSQL("INSERT INTO user (name,pass) VALUES (?,?) ",new Object[]{name,pass});
            db.close();
            if(chbox.isChecked()){
                boolean flag = UserInfoUtils.saveinfo(MainActivity.this,name,pass);
                if(flag){
                    Toast.makeText(this, "已保存", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(this, "未保存", Toast.LENGTH_SHORT).show();
                }

            }else{
                Toast.makeText(this, "未勾选", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private  class MyAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return 0;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            return null;
        }
    }
}
