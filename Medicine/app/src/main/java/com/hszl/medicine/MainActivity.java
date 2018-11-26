package com.hszl.medicine;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.hszl.medicine.activity.BaseActivity;
import com.hszl.medicine.activity.MedicineInfoActivity;
import com.hszl.medicine.activity.MedicineTypeActivity;
import com.hszl.medicine.activity.StatisticsReportActivity;
import com.hszl.medicine.activity.StockInActivity;
import com.hszl.medicine.activity.StockInfoActivity;
import com.hszl.medicine.activity.SupplierInfoActivity;
import com.hszl.medicine.activity.SupplierTypeActivity;
import com.hszl.medicine.adapter.ImageAdapter;
import com.hszl.medicine.adapter.TextAdapter;
import com.hszl.medicine.entity.MainTest;
import com.hszl.medicine.entity.MainTest2;
import com.hszl.medicine.inerface.MyItemClickListener;
import com.hszl.medicine.utils.ImmersionUtils;
import com.hszl.medicine.view.FixChildLableGroup;
import com.skateboard.zxinglib.CaptureActivity;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends BaseActivity implements MyItemClickListener {

    FixChildLableGroup lgStatistics,fclgSaler,fclgPaymentReceived,fclgStock,fclgBasicData,fclgReport;
    List<FixChildLableGroup> list=new ArrayList<>();
    RelativeLayout rlTopContent;
    LinearLayout llTop;
    ImageView ivTop;
    TextAdapter adapter;
    ImageAdapter imageAdapter;                 //销售adapter
    ImageAdapter imageAdapter1;                //收付款adapter
    ImageAdapter imageAdapter2;                //仓库adapter
    ImageAdapter imageAdapter3;                //基础数据
    ImageAdapter imageAdapter4;                //报表统计
    List<MainTest> list1=new ArrayList<>();   //顶部FixChildLableGroup组件数据源
    List<MainTest2> list2=new ArrayList<>();  //销售FixChildLableGroup组件数据源
    List<MainTest2> list3=new ArrayList<>();  //收付款FixChildLableGroup组件数据源
    List<MainTest2> list4=new ArrayList<>();  //仓库管理FixChildLableGroup组件数据源
    List<MainTest2> list5=new ArrayList<>();  //基础数据FixChildLableGroup组件数据源
    List<MainTest2> list6=new ArrayList<>();  //报表统计FixChildLableGroup组件数据源

    @Override
    public boolean validation() {
        return false;
    }

    private void initAllList()
    {
        MainTest mainTest=new MainTest();
        mainTest.setStr1("0");
        mainTest.setStr2("今日订单");
        list1.add(mainTest);
        MainTest mainTest1=new MainTest();
        mainTest1.setStr1("0");
        mainTest1.setStr2("今日营业额");
        list1.add(mainTest1);
        MainTest mainTest2=new MainTest();
        mainTest2.setStr1("0");
        mainTest2.setStr2("今日报损药品");
        list1.add(mainTest2);
        MainTest mainTest3=new MainTest();
        mainTest3.setStr1("0");
        mainTest3.setStr2("库存总量");
        list1.add(mainTest3);
        MainTest mainTest4=new MainTest();
        mainTest4.setStr1("0");
        mainTest4.setStr2("今日出库数量");
        list1.add(mainTest4);
        MainTest mainTest5=new MainTest();
        mainTest5.setStr1("38");
        mainTest5.setStr2("今日入库数量");
        list1.add(mainTest5);
        MainTest2 mainTest21=new MainTest2();
        mainTest21.setStr1("销售订单明细");
        mainTest21.setResid(R.drawable.one);
        list2.add(mainTest21);
        MainTest2 mainTest22=new MainTest2();
        mainTest22.setStr1("销售退单明细");
        mainTest22.setResid(R.drawable.two);
        list2.add(mainTest22);
        MainTest2 mainTest23=new MainTest2();
        mainTest23.setStr1("收支单管理");
        mainTest23.setResid(R.drawable.three);
        list3.add(mainTest23);
        MainTest2 mainTest24=new MainTest2();
        mainTest24.setStr1("支出单管理");
        mainTest24.setResid(R.drawable.four);
        list3.add(mainTest24);
        MainTest2 mainTest25=new MainTest2();
        mainTest25.setStr1("月末结转损益");
        mainTest25.setResid(R.drawable.five);
        list3.add(mainTest25);
        MainTest2 mainTest26=new MainTest2();
        mainTest26.setStr1("入库单录入");
        mainTest26.setResid(R.drawable.six);
        list4.add(mainTest26);
        MainTest2 mainTest27=new MainTest2();
        mainTest27.setStr1("入库单管理");
        mainTest27.setResid(R.drawable.seven);
        list4.add(mainTest27);
        MainTest2 mainTest28=new MainTest2();
        mainTest28.setStr1("出库单录入");
        mainTest28.setResid(R.drawable.eight);
        list4.add(mainTest28);
        MainTest2 mainTest29=new MainTest2();
        mainTest29.setStr1("出库单管理");
        mainTest29.setResid(R.drawable.nine);
        list4.add(mainTest29);
        MainTest2 mainTest30=new MainTest2();
        mainTest30.setStr1("盘点单录入");
        mainTest30.setResid(R.drawable.ten);
        list4.add(mainTest30);
        MainTest2 mainTest31=new MainTest2();
        mainTest31.setStr1("盘点单管理");
        mainTest31.setResid(R.drawable.eleven);
        list4.add(mainTest31);
        MainTest2 mainTest32=new MainTest2();
        mainTest32.setStr1("报损单录入");
        mainTest32.setResid(R.drawable.twelve);
        list4.add(mainTest32);
        MainTest2 mainTest33=new MainTest2();
        mainTest33.setStr1("报损单管理");
        mainTest33.setResid(R.drawable.thirteen);
        list4.add(mainTest33);
        MainTest2 mainTest34=new MainTest2();
        mainTest34.setStr1("药品库存");
        mainTest34.setResid(R.drawable.fourteen);
        list4.add(mainTest34);
        MainTest2 mainTest35=new MainTest2();
        mainTest35.setStr1("药品有效期");
        mainTest35.setResid(R.drawable.fifteen);
        list4.add(mainTest35);
        MainTest2 mainTest36=new MainTest2();
        mainTest36.setStr1("仓库信息");
        mainTest36.setResid(R.drawable.sixteen);
        list5.add(mainTest36);
        MainTest2 mainTest37=new MainTest2();
        mainTest37.setStr1("供应商分类");
        mainTest37.setResid(R.drawable.seventeen);
        list5.add(mainTest37);
        MainTest2 mainTest38=new MainTest2();
        mainTest38.setStr1("供应商信息");
        mainTest38.setResid(R.drawable.eighteen);
        list5.add(mainTest38);
        MainTest2 mainTest39=new MainTest2();
        mainTest39.setStr1("药品分类");
        mainTest39.setResid(R.drawable.nineteen);
        list5.add(mainTest39);
        MainTest2 mainTest40=new MainTest2();
        mainTest40.setStr1("药品基本信息");
        mainTest40.setResid(R.drawable.twenty);
        list5.add(mainTest40);
        MainTest2 mainTest41=new MainTest2();
        mainTest41.setStr1("图表统计");
        mainTest41.setResid(R.drawable.twentyone);
        list6.add(mainTest41);

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ImmersionUtils.setTrans(this);
        initLayout(R.layout.activity_main);
        hideTop();
        initView();
        initAllList();
        ImmersionUtils.immersionTop(this,rlTopContent);
        lgStatistics=findViewById(R.id.lgStatistics);
        adapter=new TextAdapter(list1);
        adapter.setMyItemClickListener(this);
        initMyView(6,lgStatistics,R.layout.item_main_top);
        lgStatistics.setAdapter(adapter);
        initBodyView();
    }

//    private void initTest2()
//    {
//        for (int i = 0; i <2; i++) {
//            MainTest2 mainTest2=new MainTest2();
//            mainTest2.setResid(R.drawable.own_gray);
//            mainTest2.setStr1("销售"+i);
//            list2.add(mainTest2);
//        }
//    }

//    private void initTest()
//    {
//        for (int i = 0; i < 6; i++) {
//            MainTest mainTest=new MainTest();
//            mainTest.setStr1(String.valueOf(i));
//            mainTest.setStr2("今日内容"+i);
//            list1.add(mainTest);
//        }
//    }

    private void initBodyView() {
        for (int i = 0; i < list.size(); i++) {
            switch (list.get(i).getId())
            {
                case R.id.fclgSaler:
                    initMyView(2,fclgSaler,R.layout.item_main_body);
                    imageAdapter=new ImageAdapter(list2,this);
                    imageAdapter.setMyItemClickListener(this);
                    fclgSaler.setAdapter(imageAdapter);
                    break;
                case R.id.fclgPaymentReceived:
                    initMyView(3,fclgPaymentReceived,R.layout.item_main_body);
                    imageAdapter1=new ImageAdapter(list3,this);
                    imageAdapter1.setMyItemClickListener(this);
                    fclgPaymentReceived.setAdapter(imageAdapter1);
                    break;
                case R.id.fclgStock:
                    initMyView(10,fclgStock,R.layout.item_main_body);
                    imageAdapter2=new ImageAdapter(list4,this);
                    imageAdapter2.setMyItemClickListener(this);
                    fclgStock.setAdapter(imageAdapter2);
                    break;
                case R.id.fclgBasicData:
                    initMyView(5,fclgBasicData,R.layout.item_main_body);
                    imageAdapter3=new ImageAdapter(list5,this);
                    imageAdapter3.setMyItemClickListener(this);
                    fclgBasicData.setAdapter(imageAdapter3);
                    break;
                case R.id.fclgReport:
                    initMyView(1,fclgReport,R.layout.item_main_body);
                    imageAdapter4=new ImageAdapter(list6,this);
                    imageAdapter4.setMyItemClickListener(this);
                    fclgReport.setAdapter(imageAdapter4);
                    break;
            }
        }
    }

//    private void initTopView(int num,ViewGroup viewGroup) {
//        for (int i = 0; i < 6; i++) {
//            View view=creatTopView();
//            viewGroup.addView(view);
//        }
//    }

    private void initMyView(int num,ViewGroup viewGroup,int resid)
    {
        for (int i = 0; i < num; i++) {
            View view=creatView(resid,viewGroup);
            viewGroup.addView(view);
        }
    }

    private void initView() {
        rlTopContent=findViewById(R.id.rlTopContent);
        lgStatistics=findViewById(R.id.lgStatistics);
        fclgSaler=findViewById(R.id.fclgSaler);
        fclgPaymentReceived=findViewById(R.id.fclgPaymentReceived);
        fclgStock=findViewById(R.id.fclgStock);
        fclgBasicData=findViewById(R.id.fclgBasicData);
        fclgReport=findViewById(R.id.fclgReport);
        list.add(fclgSaler);
        list.add(fclgPaymentReceived);
        list.add(fclgStock);
        list.add(fclgBasicData);
        list.add(fclgReport);
    }
    private View creatView(int resid,ViewGroup viewGroup)
    {
        View view =LayoutInflater.from(this).inflate(resid,viewGroup,false);
        return view;
    }

    @Override
    public void ItemClickListener(ViewGroup parent, int position) {
        Intent intent=new Intent();
        switch (parent.getId())
        {
            case R.id.lgStatistics:
                Toast.makeText(this,"我点击的是顶部第"+position+"项",Toast.LENGTH_SHORT).show();
                break;
            case R.id.fclgSaler:
                Toast.makeText(this,"我点击的是销售第"+position+"项",Toast.LENGTH_SHORT).show();
                break;
            case R.id.fclgPaymentReceived:
                Toast.makeText(this,"我点击的是收付款第"+position+"项",Toast.LENGTH_SHORT).show();
                break;
            case R.id.fclgStock:
                switch (position)
                {
                    case 0:
                        intent.setClass(this,StockInActivity.class);
                        startActivity(intent);
                        break;
                }
                Toast.makeText(this,"我点击的是仓库第"+position+"项",Toast.LENGTH_SHORT).show();
                break;
            case R.id.fclgBasicData:
                switch (position)
                {
                    case 0:
                        intent.setClass(this,StockInfoActivity.class);
                        startActivity(intent);
                        break;
                    case 1:
                        intent.setClass(this,SupplierTypeActivity.class);
                        startActivity(intent);
                        break;
                    case 2:
                        intent.setClass(this,SupplierInfoActivity.class);
                        startActivity(intent);
                        break;
                    case 3:
                        intent.setClass(this,MedicineTypeActivity.class);
                        startActivity(intent);
                        break;
                    case 4:
                        intent.setClass(this,MedicineInfoActivity.class);
                        startActivity(intent);
//                        intent.setClass(this,CaptureActivity.class);
//                        startActivityForResult(intent,1001);
                        break;
                }
                Toast.makeText(this,"我点击的是基础数据第"+position+"项",Toast.LENGTH_SHORT).show();
                break;
            case R.id.fclgReport:
                intent.setClass(this,StatisticsReportActivity.class);
                startActivity(intent);
//                Toast.makeText(this,"我点击的是报表统计第"+position+"项",Toast.LENGTH_SHORT).show();
                break;
        }

    }
    //    private View creatTopView() {
//        return LayoutInflater.from(this).inflate(R.layout.item_main_top,lgStatistics,false);
//    }
//
//    private View creatBodyView(ViewGroup viewGroup)
//    {
//        return LayoutInflater.from(this).inflate(R.layout.item_main_body,viewGroup,false);
//    }

}
