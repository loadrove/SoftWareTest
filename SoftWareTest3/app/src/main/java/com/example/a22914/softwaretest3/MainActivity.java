package com.example.a22914.softwaretest3;

import android.os.Handler;
import android.os.Message;
import android.support.annotation.FractionRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a22914.softwaretest3.util.*;

import java.io.IOException;
import java.util.ArrayList;

import static java.lang.Thread.sleep;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btn_ok,btn_start;
    private TextView tv_topics,tv_time,tv_number;
    private EditText et_answer;
    private ReadFile readFile;
    private Calculate calculate;
    private ArrayList<String>list;
    private boolean isStartTime=false;
    private int time=0;//已经作答的题目

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private Handler myHandle=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch(msg.what){
                case 1:
                    reFlushTime();
                    break;
            }
        }
    };


    private void init(){
        calculate=Calculate.getElement();
        list=new ArrayList<String>();
        btn_start= (Button) findViewById(R.id.btn_start);
        btn_ok = (Button) findViewById(R.id.btn_ok);
        tv_number= (TextView) findViewById(R.id.tv_number);
        tv_time= (TextView) findViewById(R.id.tv_time);
        tv_topics= (TextView) findViewById(R.id.tv_topic);
        et_answer= (EditText) findViewById(R.id.et_answer);
        btn_start.setOnClickListener(this);
        btn_ok.setOnClickListener(this);
        list.add("12+84-5");
        list.add("12+8-5");
        list.add("1+84-5");
        list.add("12-4-5");
        list.add("1/2+1/5");
        list.add("12-88");
        list.add("12+82-5");
        list.add("12+45-5");
        list.add("12+8");
        list.add("1/2+3/8");
        list.add("1/2+4/5");
        list.add("1/2+2/5");
        list.add("12+34-5");
        list.add("14-5");
        list.add("14-5");
        list.add("44-5");
        list.add("12+23-5");
        tv_topics.setText(list.get((int) Math.random()*list.size())+"=");
        tv_time.setText("已用时间为:0");
    }


    /*
    * 用于更新题目
    * */
    private void flushTopic() {
        int x= (int) (Math.random()*list.size());
        tv_topics.setText(list.get(x)+"=");
        Log.d("x:",x+"");
        time++;
    }

    /*
    * 用于设置某个View的文本信息
    * */
    private void setText(View view,String context){
        view=(TextView)view;
        ((TextView) view).setText(context);
    }

    private void addNumber(){
        String str= (String) tv_number.getText();
        str=str.substring(str.indexOf(':')+1);
        int newNumebr=Integer.parseInt(str)+20;
        setText(tv_number,"当前分数:"+newNumebr);
    }


    /*
    * 用于获取某个空间的文本信息
    * */
    private String getString(View view){
        view=(TextView)view;
        return (String) ((TextView) view).getText();
    }

    /*
    * 传进来算数式子，计算将结果返回
    * */
    private String count(String str){
        str=str.substring(0,str.indexOf('='));
        String result=calculate.getResult(str);
        return result;
    }

    /*
    * 用于刷新计时器的时间信息
    * */
    private void reFlushTime(){
        String str1= (String) tv_time.getText();
        String str= str1.substring(str1.indexOf(":")+1);
        int time=Integer.parseInt(str)+1;
        tv_time.setText("已用时间为:"+time);
    }

    private boolean isTure(){
        String str=getString(tv_topics);
        String result=count(str);
        Log.d("int isTure,the result:",result);
        String  user_answer= String.valueOf(et_answer.getText());
        Log.d("int isTure,the user:",user_answer);
        return user_answer.equals(result);
    }


    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.btn_start:
                this.isStartTime=true;
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while(isStartTime&&time<5){
                            try {
                                Message ms=myHandle.obtainMessage();
                                ms.what=1;
                                myHandle.sendMessage(ms);
                                sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        }
                }).start();
                break;
            case R.id.btn_ok:
                if(time<5){
                    if(et_answer.getText()!=null||!et_answer.getText().equals("")){
                        if(isTure()){
                            Toast.makeText(this,"恭喜你，答对一题",Toast.LENGTH_SHORT).show();
                            addNumber();
                            et_answer.setText("");
                            flushTopic();
                        }else{
                            Toast.makeText(this,"很遗憾，答错了",Toast.LENGTH_SHORT).show();
                            et_answer.setText("");
                            flushTopic();
                        }
                    }else{
                        Toast.makeText(this,"请先输入答案再提交！",Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(this,"本次考试结束，请重新作答",Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
