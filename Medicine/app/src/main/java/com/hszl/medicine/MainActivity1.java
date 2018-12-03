package com.hszl.medicine;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
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
import com.hszl.medicine.adapter.AdapterModelTest;
import com.hszl.medicine.adapter.ImageAdapter;
import com.hszl.medicine.adapter.ImageAdapterModelTest;
import com.hszl.medicine.adapter.ImageAdapterModelTest2;
import com.hszl.medicine.adapter.TexTAdapterModelTest1;
import com.hszl.medicine.adapter.TextAdapter;
import com.hszl.medicine.adapter.TextAdapterModelTest;
import com.hszl.medicine.entity.MainTest;
import com.hszl.medicine.entity.MainTest2;
import com.hszl.medicine.inerface.MyItemClickListener;
import com.hszl.medicine.inerface.ViewGroupItemClickListener;
import com.hszl.medicine.utils.ImmersionUtils;
import com.hszl.medicine.utils.ViewGroupImpl;
import com.hszl.medicine.view.FixChildLableGroup;
import com.hszl.medicine.view.ViewGroupTest;

import java.util.ArrayList;
import java.util.List;

//setMyItemClickListener
public class MainActivity1 extends BaseActivity implements ViewGroupItemClickListener {

    ViewGroupTest fclgSaler,fclgPaymentReceived,fclgBasicData;

//    ViewGroupTest lgStatistics, fclgSaler, fclgPaymentReceived, fclgStock, fclgBasicData, fclgReport;
    List<ViewGroupTest> list = new ArrayList<>();
    RelativeLayout rlTopContent;
    LinearLayout llTop;
    ImageView ivTop;
//    TexTAdapterModelTest1 adapter;
    ImageAdapterModelTest2 imageAdapter1, imageAdapter2, imageAdapter3;


    //    TextAdapter adapter;
    //    ImageAdapter imageAdapter;                 //销售adapter
    //    ImageAdapter imageAdapter1;                //收付款adapter
    //    ImageAdapter imageAdapter2;                //仓库adapter
    //    ImageAdapter imageAdapter3;                //基础数据
    //    ImageAdapter imageAdapter4;                //报表统计
//    List<MainTest> list1 = new ArrayList<>();   //顶部FixChildLableGroup组件数据源
    List<MainTest2> list2 = new ArrayList<>();  //销售FixChildLableGroup组件数据源
    List<MainTest2> list3 = new ArrayList<>();  //收付款FixChildLableGroup组件数据源
//    List<MainTest2> list4 = new ArrayList<>();  //仓库管理FixChildLableGroup组件数据源
    List<MainTest2> list5 = new ArrayList<>();  //基础数据FixChildLableGroup组件数据源
//    List<MainTest2> list6 = new ArrayList<>();  //报表统计FixChildLableGroup组件数据源

    @Override
    public boolean validation() {
        return false;
    }

    private void initAllList() {
//        MainTest mainTest = new MainTest();
//        mainTest.setStr1("0");
//        mainTest.setStr2("今日订单");
//        list1.add(mainTest);
//        MainTest mainTest1 = new MainTest();
//        mainTest1.setStr1("0");
//        mainTest1.setStr2("今日营业额");
//        list1.add(mainTest1);
//        MainTest mainTest2 = new MainTest();
//        mainTest2.setStr1("0");
//        mainTest2.setStr2("今日报损药品");
//        list1.add(mainTest2);
//        MainTest mainTest3 = new MainTest();
//        mainTest3.setStr1("0");
//        mainTest3.setStr2("库存总量");
//        list1.add(mainTest3);
//        MainTest mainTest4 = new MainTest();
//        mainTest4.setStr1("0");
//        mainTest4.setStr2("今日出库数量");
//        list1.add(mainTest4);
//        MainTest mainTest5 = new MainTest();
//        mainTest5.setStr1("38");
//        mainTest5.setStr2("今日入库数量");
//        list1.add(mainTest5);
        MainTest2 mainTest21 = new MainTest2();
        mainTest21.setStr1("销售订单");
        mainTest21.setResid(R.drawable.one);
        list2.add(mainTest21);
        MainTest2 mainTest22 = new MainTest2();
        mainTest22.setStr1("销售退单");
        mainTest22.setResid(R.drawable.two);
        list2.add(mainTest22);
        MainTest2 mainTest23 = new MainTest2();
        mainTest23.setStr1("收支单");
        mainTest23.setResid(R.drawable.three);
        list3.add(mainTest23);
        MainTest2 mainTest24 = new MainTest2();
        mainTest24.setStr1("支出单");
        mainTest24.setResid(R.drawable.four);
        list3.add(mainTest24);
        MainTest2 mainTest25 = new MainTest2();
        mainTest25.setStr1("月末结转");
        mainTest25.setResid(R.drawable.five);
        list3.add(mainTest25);
//        MainTest2 mainTest26 = new MainTest2();
//        mainTest26.setStr1("入库单录入");
//        mainTest26.setResid(R.drawable.six);
//        list4.add(mainTest26);
//        MainTest2 mainTest27 = new MainTest2();
//        mainTest27.setStr1("入库单管理");
//        mainTest27.setResid(R.drawable.seven);
//        list4.add(mainTest27);
//        MainTest2 mainTest28 = new MainTest2();
//        mainTest28.setStr1("出库单录入");
//        mainTest28.setResid(R.drawable.eight);
//        list4.add(mainTest28);
//        MainTest2 mainTest29 = new MainTest2();
//        mainTest29.setStr1("出库单管理");
//        mainTest29.setResid(R.drawable.nine);
//        list4.add(mainTest29);
//        MainTest2 mainTest30 = new MainTest2();
//        mainTest30.setStr1("盘点单录入");
//        mainTest30.setResid(R.drawable.ten);
//        list4.add(mainTest30);
//        MainTest2 mainTest31 = new MainTest2();
//        mainTest31.setStr1("盘点单管理");
//        mainTest31.setResid(R.drawable.eleven);
//        list4.add(mainTest31);
//        MainTest2 mainTest32 = new MainTest2();
//        mainTest32.setStr1("报损单录入");
//        mainTest32.setResid(R.drawable.twelve);
//        list4.add(mainTest32);
//        MainTest2 mainTest33 = new MainTest2();
//        mainTest33.setStr1("报损单管理");
//        mainTest33.setResid(R.drawable.thirteen);
//        list4.add(mainTest33);
//        MainTest2 mainTest34 = new MainTest2();
//        mainTest34.setStr1("药品库存");
//        mainTest34.setResid(R.drawable.fourteen);
//        list4.add(mainTest34);
//        MainTest2 mainTest35 = new MainTest2();
//        mainTest35.setStr1("药品有效期");
//        mainTest35.setResid(R.drawable.fifteen);
//        list4.add(mainTest35);
        MainTest2 mainTest36 = new MainTest2();
        mainTest36.setStr1("仓库信息");
        mainTest36.setResid(R.drawable.sixteen);
        list5.add(mainTest36);
        MainTest2 mainTest37 = new MainTest2();
        mainTest37.setStr1("供应商分类");
        mainTest37.setResid(R.drawable.seventeen);
        list5.add(mainTest37);
        MainTest2 mainTest38 = new MainTest2();
        mainTest38.setStr1("供应商");
        mainTest38.setResid(R.drawable.eighteen);
        list5.add(mainTest38);
        MainTest2 mainTest39 = new MainTest2();
        mainTest39.setStr1("药品分类");
        mainTest39.setResid(R.drawable.nineteen);
        list5.add(mainTest39);
        MainTest2 mainTest40 = new MainTest2();
        mainTest40.setStr1("药品信息");
        mainTest40.setResid(R.drawable.twenty);
        list5.add(mainTest40);
        MainTest2 mainTest41=new MainTest2();
        mainTest41.setStr1("图表统计");
        mainTest41.setResid(R.drawable.twentyone);
        list5.add(mainTest41);
//        MainTest2 mainTest41 = new MainTest2();
//        mainTest41.setStr1("图表统计");
//        mainTest41.setResid(R.drawable.twentyone);
//        list6.add(mainTest41);

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ImmersionUtils.setTrans(this);
        initLayout(R.layout.activity_main1);
        initView();
        initAllList();
        ImmersionUtils.immersionTop(this, rlTop);
//        lgStatistics = findViewById(R.id.lgStatistics);
//        adapter = new TexTAdapterModelTest1(this, list1);  //实例化adapter
//        Statistics = new ViewGroupImpl(adapter);                  //实例化观察者并注册
//        Statistics.addView(lgStatistics, adapter, true);   //观察者根据adapter适配器添加子view，子view在适配器里实现数据绑定
//        Statistics.setOnItemClickListener(lgStatistics, this);//观察者对viewgroup添加itemClick事件 必须在addview完成后添加事件否则无效
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
            switch (list.get(i).getId()) {
                case R.id.fclgSaler:
                    imageAdapter1 = new ImageAdapterModelTest2(this, list2);
                    Saler = new ViewGroupImpl(imageAdapter1);
                    Saler.addView(fclgSaler, imageAdapter1, true);
                    Saler.setOnItemClickListener(fclgSaler, this);
                    break;
                case R.id.fclgPaymentReceived:
                    imageAdapter2 = new ImageAdapterModelTest2(this, list3);
                    PaymentReceived = new ViewGroupImpl(imageAdapter2);
                    PaymentReceived.addView(fclgPaymentReceived, imageAdapter2, true);
                    PaymentReceived.setOnItemClickListener(fclgPaymentReceived, this);
                    break;
//                case R.id.fclgStock:
//                    imageAdapter2 = new ImageAdapterModelTest2(this, list4);
//                    Stock = new ViewGroupImpl(imageAdapter2);
//                    Stock.addView(fclgStock, imageAdapter2, true);
//                    Stock.setOnItemClickListener(fclgStock, this);
//                    break;
                case R.id.fclgBasicData:
                    imageAdapter3 = new ImageAdapterModelTest2(this, list5);
                    BasicData = new ViewGroupImpl(imageAdapter3);
                    BasicData.addView(fclgBasicData, imageAdapter3, true);
                    BasicData.setOnItemClickListener(fclgBasicData, this);
                    break;
//                case R.id.fclgReport:
//                    imageAdapter4 = new ImageAdapterModelTest2(this, list6);
//                    Report = new ViewGroupImpl(imageAdapter4);
//                    Report.addView(fclgReport, imageAdapter4, true);
//                    Report.setOnItemClickListener(fclgReport, this);
//                    break;
            }
        }
    }

    //    private void initTopView(int num,ViewGroup viewGroup) {
    //        for (int i = 0; i < 6; i++) {
    //            View view=creatTopView();
    //            viewGroup.addView(view);
    //        }
    //    }

    //    private void initMyView(int num, ViewGroup viewGroup, int resid) {
    //        for (int i = 0; i < num; i++) {
    //            View view = creatView(resid, viewGroup);
    //            viewGroup.addView(view);
    //        }
    //    }

    ViewGroupImpl Statistics, Saler, PaymentReceived, Stock, BasicData, Report; //观察者

    private void initView() {
        rlTopContent = findViewById(R.id.rlTopContent);
//        lgStatistics = findViewById(R.id.lgStatistics);
        fclgSaler = findViewById(R.id.fclgSaler);
        fclgPaymentReceived = findViewById(R.id.fclgPaymentReceived);
//        fclgStock = findViewById(R.id.fclgStock);
        fclgBasicData = findViewById(R.id.fclgBasicData);
//        fclgReport = findViewById(R.id.fclgReport);
        list.add(fclgSaler);
        list.add(fclgPaymentReceived);
//        list.add(fclgStock);
        list.add(fclgBasicData);
//        list.add(fclgReport);
        //        Statistics = new ViewGroupImpl(adapter);
        //        Saler = new ViewGroupImpl();
        //        PaymentReceived = new ViewGroupImpl();
        //        Stock = new ViewGroupImpl();
        //        BasicData = new ViewGroupImpl();
        //        Report = new ViewGroupImpl();
    }

    //    private View creatView(int resid, ViewGroup viewGroup) {
    //        View view = LayoutInflater.from(this).inflate(resid, viewGroup, false);
    //        return view;
    //    }

    @Override
    public void onItemClickListener(ViewGroup parent, View view, int position) {
        Intent intent = new Intent();
        switch (parent.getId()) {
            case R.id.lgStatistics:
                switch (position) {
                    case 0:
                        Toast.makeText(this, "点击的是顶部" + position, Toast.LENGTH_SHORT).show();
                        //                        list1.add(new MainTest("0","今日天气"));
                        //                        adapter.notifyDataSetChange();
                        break;
                    case 1:
                        Toast.makeText(this, "点击的是顶部" + position, Toast.LENGTH_SHORT).show();
                        break;
                    case 2:
                        Toast.makeText(this, "点击的是顶部" + position, Toast.LENGTH_SHORT).show();
                        break;
                    case 3:
                        Toast.makeText(this, "点击的是顶部" + position, Toast.LENGTH_SHORT).show();
                        break;
                    case 4:
                        Toast.makeText(this, "点击的是顶部" + position, Toast.LENGTH_SHORT).show();
                        break;
                    case 5:
                        Toast.makeText(this, "点击的是顶部" + position, Toast.LENGTH_SHORT).show();
                        break;
                }
                break;
            case R.id.fclgSaler:
                switch (position) {
                    case 0:
                        Toast.makeText(this, "点击的是销售" + position, Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        Toast.makeText(this, "点击的是销售" + position, Toast.LENGTH_SHORT).show();
                        break;
                    case 2:
                        Toast.makeText(this, "点击的是销售" + position, Toast.LENGTH_SHORT).show();
                        break;
                    case 3:
                        Toast.makeText(this, "点击的是销售" + position, Toast.LENGTH_SHORT).show();
                        break;
                    case 4:
                        Toast.makeText(this, "点击的是销售" + position, Toast.LENGTH_SHORT).show();
                        break;
                    case 5:
                        Toast.makeText(this, "点击的是销售" + position, Toast.LENGTH_SHORT).show();
                        break;
                }
                break;
            case R.id.fclgPaymentReceived:
                switch (position) {
                    case 0:
                        Toast.makeText(this, "点击的是收付款" + position, Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        Toast.makeText(this, "点击的是收付款" + position, Toast.LENGTH_SHORT).show();
                        break;
                    case 2:
                        Toast.makeText(this, "点击的是收付款" + position, Toast.LENGTH_SHORT).show();
                        break;
                    case 3:
                        Toast.makeText(this, "点击的是收付款" + position, Toast.LENGTH_SHORT).show();
                        break;
                    case 4:
                        Toast.makeText(this, "点击的是收付款" + position, Toast.LENGTH_SHORT).show();
                        break;
                    case 5:
                        Toast.makeText(this, "点击的是收付款" + position, Toast.LENGTH_SHORT).show();
                        break;
                }
                break;
            case R.id.fclgStock:
                switch (position) {
                    case 0:
                        Toast.makeText(this, "点击的是仓库" + position, Toast.LENGTH_SHORT).show();
                        intent.setClass(this, StockInActivity.class);
                        startActivity(intent);
                        break;
                    case 1:
                        Toast.makeText(this, "点击的是仓库" + position, Toast.LENGTH_SHORT).show();
                        break;
                    case 2:
                        Toast.makeText(this, "点击的是仓库" + position, Toast.LENGTH_SHORT).show();
                        break;
                    case 3:
                        Toast.makeText(this, "点击的是仓库" + position, Toast.LENGTH_SHORT).show();
                        break;
                    case 4:
                        Toast.makeText(this, "点击的是仓库" + position, Toast.LENGTH_SHORT).show();
                        break;
                    case 5:
                        Toast.makeText(this, "点击的是仓库" + position, Toast.LENGTH_SHORT).show();
                        break;
                }
                break;
            case R.id.fclgBasicData:
                switch (position) {
                    case 0:
                        intent.setClass(this, StockInfoActivity.class);
                        startActivity(intent);
                        break;
                    case 1:
                        intent.setClass(this, SupplierTypeActivity.class);
                        startActivity(intent);
                        break;
                    case 2:
                        intent.setClass(this, SupplierInfoActivity.class);
                        startActivity(intent);
                        break;
                    case 3:
                        intent.setClass(this, MedicineTypeActivity.class);
                        startActivity(intent);
                        break;
                    case 4:
                        intent.setClass(this, MedicineInfoActivity.class);
                        startActivity(intent);
                        break;
                }
                break;
            case R.id.fclgReport:
                switch (position) {
                    case 0:
                        Toast.makeText(this, "点击的是报表" + position, Toast.LENGTH_SHORT).show();
                        intent.setClass(this, StatisticsReportActivity.class);
                        startActivity(intent);
                        break;
                    case 1:
                        Toast.makeText(this, "点击的是报表" + position, Toast.LENGTH_SHORT).show();
                        break;
                    case 2:
                        Toast.makeText(this, "点击的是报表" + position, Toast.LENGTH_SHORT).show();
                        break;
                    case 3:
                        Toast.makeText(this, "点击的是报表" + position, Toast.LENGTH_SHORT).show();
                        break;
                    case 4:
                        Toast.makeText(this, "点击的是报表" + position, Toast.LENGTH_SHORT).show();
                        break;
                    case 5:
                        Toast.makeText(this, "点击的是报表" + position, Toast.LENGTH_SHORT).show();
                        break;
                }
                break;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
//        adapter.removeObserver(Statistics);
//        imageAdapter.removeObserver(Saler);
        imageAdapter1.removeObserver(PaymentReceived);
        imageAdapter2.removeObserver(Stock);
        imageAdapter3.removeObserver(BasicData);
//        imageAdapter4.removeObserver(Report);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {

    }
}
