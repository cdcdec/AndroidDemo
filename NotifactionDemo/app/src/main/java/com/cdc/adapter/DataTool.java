package com.cdc.adapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cdc on 2018/3/9.
 */

public class DataTool {

    public static People[] peoples={new People("张飞",20,"阆中"),new People("关羽",21,"荆州"),new People("刘备",22,"成都")};
    public static List<People> listPeoples=new ArrayList<>();
    static{
        listPeoples.add(new People("孙悟空",3000,"花果山"));
        listPeoples.add(new People("猪悟能",2000,"云桟洞"));
        listPeoples.add(new People("沙悟净",1800,"流沙河"));
    }
}
