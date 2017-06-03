package com.example.uiwidgettest;

import android.support.annotation.IntegerRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText editText_totalProfit;      // 组总利润
    private EditText editText_personalProfit;   // 个人利润
    private EditText editText_numberOfMember;   // 成员数目
    private EditText editText_rankRatio;        // 名次倍率
    private EditText editText_averageAward;     // 平均奖金
    private EditText editText_yourAward;        // 您的奖金
    private CheckBox checkBox_leader;           // 我是组长

    int total = 240000, personal = 60000, count = 4;
    double rankRatio = 1.0, leaderRatio = 1.3;
    int average = 0, yours = 0;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.test_item:
                Toast.makeText(MainActivity.this, "You clicked Test", Toast.LENGTH_SHORT).show();
                break;
            case R.id.save_item:
                Toast.makeText(MainActivity.this, "You clicked Save", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button)findViewById(R.id.ok_button);
        editText_totalProfit = (EditText)findViewById(R.id.editText_1);
        editText_personalProfit = (EditText)findViewById(R.id.editText_2);
        editText_numberOfMember = (EditText)findViewById(R.id.editText_3);
        editText_rankRatio = (EditText)findViewById(R.id.editText_4);
        editText_averageAward = (EditText)findViewById(R.id.editText_6);
        editText_yourAward = (EditText)findViewById(R.id.editText_7);
        checkBox_leader = (CheckBox)findViewById(R.id.checkBox_1);



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // do something
                //String inputText = editText.getText().toString();
                //Log.d("MainActivity", "ok_button is clicked");
                //Toast.makeText(MainActivity.this, inputText, Toast.LENGTH_SHORT).show();

                if(editText_totalProfit.getText().toString().isEmpty()
                        || editText_personalProfit.getText().toString().isEmpty()
                        || editText_numberOfMember.getText().toString().isEmpty()
                        || editText_rankRatio.getText().toString().isEmpty()) {

                    Toast.makeText(MainActivity.this, "请输入数据", Toast.LENGTH_SHORT).show();
                    editText_totalProfit.setText(String.valueOf(total));
                    editText_personalProfit.setText(String.valueOf(personal));
                    editText_numberOfMember.setText(String.valueOf(count));
                    editText_rankRatio.setText(String.valueOf(rankRatio));
                }
                else {
                    total = Integer.parseInt(editText_totalProfit.getText().toString());
                    personal = Integer.parseInt(editText_personalProfit.getText().toString());
                    count = Integer.parseInt(editText_numberOfMember.getText().toString());
                    rankRatio = Double.parseDouble(editText_rankRatio.getText().toString());
                }

                average = total / count;

                if(average >= 100000){
                    average = (int)((average - 100000) * 0.08 + 1400 + 1200 + 1500 + 300);
                }
                else if(average >= 80000) {
                    average = (int)((average - 80000) * 0.07 + 1200 + 1500 + 300);
                }
                else if(average >= 60000) {
                    average = (int)((average - 60000) * 0.06 + 1500 + 300);
                }
                else if(average >= 30000) {
                    average = (int)((average - 30000) * 0.05 + 300);
                }
                else if(average >=0) {
                    average = (int)(average * 0.01);
                }
                else {
                    average = 0;
                }

                average = (int)(average * rankRatio);

                if(checkBox_leader.isChecked()) {
                    yours = (int)(average * leaderRatio);
                }
                else {
                    yours = (int)(average * count * ((double)personal / total));
                }

                // if average > 8w

                editText_averageAward.setText(String.valueOf(average));
                editText_yourAward.setText(String.valueOf(yours));
            }
        });
    }
}
