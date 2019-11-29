package com.cdc.android.json;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.androidnetworking.interfaces.StringRequestListener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class MainActivity extends AppCompatActivity {

    String s1="{\n" +
            "      'code': 0,\n" +
            "      'msg': '[登录成功],职员登录成功!',\n" +
            "      'data': {\n" +
            "            'product': [\n" +
            "                  {\n" +
            "                        'searchValue': null,\n" +
            "                        'createBy': '',\n" +
            "                        'createTime': '2019-11-21 12:02:49',\n" +
            "                        'updateBy': '',\n" +
            "                        'updateTime': null,\n" +
            "                        'remark': '',\n" +
            "                        'params': {\n" +
            "                              \n" +
            "                        },\n" +
            "                        'productId': 'fb21a8cb9b3e4da8882b9a95d0063071',\n" +
            "                        'productShopId': 'ec8ac144349f4e15917c3b379e145993',\n" +
            "                        'productTypeId': '9',\n" +
            "                        'productName': '米饭',\n" +
            "                        'productPrice': 2,\n" +
            "                        'productNum': 1000,\n" +
            "                        'productStatus': 1,\n" +
            "                        'productDescription': '<p><br></p>',\n" +
            "                        'productImage': null,\n" +
            "                        'productShopName': 'APP测试商家',\n" +
            "                        'productTypeName': '主食'\n" +
            "                  },\n" +
            "                  {\n" +
            "                        'searchValue': null,\n" +
            "                        'createBy': '',\n" +
            "                        'createTime': '2019-11-21 12:03:17',\n" +
            "                        'updateBy': '',\n" +
            "                        'updateTime': null,\n" +
            "                        'remark': '',\n" +
            "                        'params': {\n" +
            "                              \n" +
            "                        },\n" +
            "                        'productId': 'ff363f92d9514e589f6b1935e9f7dc27',\n" +
            "                        'productShopId': 'ec8ac144349f4e15917c3b379e145993',\n" +
            "                        'productTypeId': '9',\n" +
            "                        'productName': '面条',\n" +
            "                        'productPrice': 1.5,\n" +
            "                        'productNum': 1000,\n" +
            "                        'productStatus': 1,\n" +
            "                        'productDescription': '<p><br></p>',\n" +
            "                        'productImage': null,\n" +
            "                        'productShopName': 'APP测试商家',\n" +
            "                        'productTypeName': '主食'\n" +
            "                  },\n" +
            "                  {\n" +
            "                        'searchValue': null,\n" +
            "                        'createBy': '',\n" +
            "                        'createTime': '2019-11-21 12:03:37',\n" +
            "                        'updateBy': '',\n" +
            "                        'updateTime': null,\n" +
            "                        'remark': '',\n" +
            "                        'params': {\n" +
            "                              \n" +
            "                        },\n" +
            "                        'productId': 'cf58270ee10a40aa9a8d27af7b98450a',\n" +
            "                        'productShopId': 'ec8ac144349f4e15917c3b379e145993',\n" +
            "                        'productTypeId': '8',\n" +
            "                        'productName': '花生米',\n" +
            "                        'productPrice': 10,\n" +
            "                        'productNum': 1000,\n" +
            "                        'productStatus': 1,\n" +
            "                        'productDescription': '<p><br></p>',\n" +
            "                        'productImage': null,\n" +
            "                        'productShopName': 'APP测试商家',\n" +
            "                        'productTypeName': '凉菜'\n" +
            "                  },\n" +
            "                  {\n" +
            "                        'searchValue': null,\n" +
            "                        'createBy': '',\n" +
            "                        'createTime': '2019-11-21 12:04:00',\n" +
            "                        'updateBy': '',\n" +
            "                        'updateTime': null,\n" +
            "                        'remark': '',\n" +
            "                        'params': {\n" +
            "                              \n" +
            "                        },\n" +
            "                        'productId': '1b2fe63535394053b98e1af14a592efc',\n" +
            "                        'productShopId': 'ec8ac144349f4e15917c3b379e145993',\n" +
            "                        'productTypeId': '8',\n" +
            "                        'productName': '素菜拼盘',\n" +
            "                        'productPrice': 10,\n" +
            "                        'productNum': 1000,\n" +
            "                        'productStatus': 1,\n" +
            "                        'productDescription': '<p><br></p>',\n" +
            "                        'productImage': null,\n" +
            "                        'productShopName': 'APP测试商家',\n" +
            "                        'productTypeName': '凉菜'\n" +
            "                  },\n" +
            "                  {\n" +
            "                        'searchValue': null,\n" +
            "                        'createBy': '',\n" +
            "                        'createTime': '2019-11-21 12:04:23',\n" +
            "                        'updateBy': '',\n" +
            "                        'updateTime': '2019-11-21 12:05:09',\n" +
            "                        'remark': '',\n" +
            "                        'params': {\n" +
            "                              \n" +
            "                        },\n" +
            "                        'productId': '8c96a7f4e87f470299c62f8772268ac4',\n" +
            "                        'productShopId': 'ec8ac144349f4e15917c3b379e145993',\n" +
            "                        'productTypeId': '7',\n" +
            "                        'productName': '宫保鸡丁',\n" +
            "                        'productPrice': 20,\n" +
            "                        'productNum': 1000,\n" +
            "                        'productStatus': 1,\n" +
            "                        'productDescription': '<p><br></p>',\n" +
            "                        'productImage': null,\n" +
            "                        'productShopName': 'APP测试商家',\n" +
            "                        'productTypeName': '炒菜'\n" +
            "                  },\n" +
            "                  {\n" +
            "                        'searchValue': null,\n" +
            "                        'createBy': '',\n" +
            "                        'createTime': '2019-11-21 12:05:01',\n" +
            "                        'updateBy': '',\n" +
            "                        'updateTime': null,\n" +
            "                        'remark': '',\n" +
            "                        'params': {\n" +
            "                              \n" +
            "                        },\n" +
            "                        'productId': '5903772ded6e4a7aa57d65097b68cef7',\n" +
            "                        'productShopId': 'ec8ac144349f4e15917c3b379e145993',\n" +
            "                        'productTypeId': '7',\n" +
            "                        'productName': '鱼香肉丝',\n" +
            "                        'productPrice': 15,\n" +
            "                        'productNum': 1000,\n" +
            "                        'productStatus': 1,\n" +
            "                        'productDescription': '<p><br></p>',\n" +
            "                        'productImage': null,\n" +
            "                        'productShopName': 'APP测试商家',\n" +
            "                        'productTypeName': '炒菜'\n" +
            "                  }\n" +
            "            ],\n" +
            "            'shop': {\n" +
            "                  'searchValue': null,\n" +
            "                  'createBy': '',\n" +
            "                  'createTime': '2019-11-20 16:24:36',\n" +
            "                  'updateBy': '',\n" +
            "                  'updateTime': null,\n" +
            "                  'remark': '18903958133',\n" +
            "                  'params': {\n" +
            "                        \n" +
            "                  },\n" +
            "                  'shopId': 'ec8ac144349f4e15917c3b379e145993',\n" +
            "                  'shopName': 'APP测试商家',\n" +
            "                  'shopCompanyId': 'df46fd5233e5463098391cc84e68a04a'\n" +
            "            },\n" +
            "            'staff': {\n" +
            "                  'searchValue': null,\n" +
            "                  'createBy': '',\n" +
            "                  'createTime': '2019-11-20 16:24:55',\n" +
            "                  'updateBy': '',\n" +
            "                  'updateTime': '2019-11-20 16:52:21',\n" +
            "                  'remark': '',\n" +
            "                  'params': {\n" +
            "                        \n" +
            "                  },\n" +
            "                  'staffId': 'f833556555d64b0ab42191b51d7be89e',\n" +
            "                  'staffName': 'APP测试职员',\n" +
            "                  'staffRole': 1,\n" +
            "                  'staffLoginName': '18903958133',\n" +
            "                  'staffLoginPassword': 'e10adc3949ba59abbe56e057f20f883e',\n" +
            "                  'staffCompanyId': 'df46fd5233e5463098391cc84e68a04a',\n" +
            "                  'staffShopId': 'ec8ac144349f4e15917c3b379e145993'\n" +
            "            },\n" +
            "            'company': {\n" +
            "                  'searchValue': null,\n" +
            "                  'createBy': '',\n" +
            "                  'createTime': '2019-11-20 16:23:35',\n" +
            "                  'updateBy': '',\n" +
            "                  'updateTime': null,\n" +
            "                  'remark': '18903958133',\n" +
            "                  'params': {\n" +
            "                        \n" +
            "                  },\n" +
            "                  'companyId': 'df46fd5233e5463098391cc84e68a04a',\n" +
            "                  'companyName': 'APP测试公司',\n" +
            "                  'companyContact': '陈',\n" +
            "                  'companyPhone': '18903958133',\n" +
            "                  'companyLogo': null\n" +
            "            },\n" +
            "            'productPackage': [\n" +
            "                  {\n" +
            "                        'searchValue': null,\n" +
            "                        'createBy': '',\n" +
            "                        'createTime': '2019-11-21 12:05:41',\n" +
            "                        'updateBy': null,\n" +
            "                        'updateTime': null,\n" +
            "                        'remark': '',\n" +
            "                        'params': {\n" +
            "                              \n" +
            "                        },\n" +
            "                        'productPackageId': 3,\n" +
            "                        'productPackageShopId': 'ec8ac144349f4e15917c3b379e145993',\n" +
            "                        'productPackageName': '三人管饱',\n" +
            "                        'productPackagePrice': 35,\n" +
            "                        'productPackageStatus': 1,\n" +
            "                        'productPackageNum': 1000,\n" +
            "                        'productPackageDescription': '<p><br></p>',\n" +
            "                        'productPackageImage': null\n" +
            "                  }\n" +
            "            ],\n" +
            "            'productType': [\n" +
            "                  {\n" +
            "                        'searchValue': null,\n" +
            "                        'createBy': '',\n" +
            "                        'createTime': '2019-11-21 12:01:52',\n" +
            "                        'updateBy': null,\n" +
            "                        'updateTime': null,\n" +
            "                        'remark': '',\n" +
            "                        'params': {\n" +
            "                              \n" +
            "                        },\n" +
            "                        'productTypeId': '7',\n" +
            "                        'productTypeShopId': 'ec8ac144349f4e15917c3b379e145993',\n" +
            "                        'productTypeName': '炒菜',\n" +
            "                        'productTypeShopName': 'APP测试商家'\n" +
            "                  },\n" +
            "                  {\n" +
            "                        'searchValue': null,\n" +
            "                        'createBy': '',\n" +
            "                        'createTime': '2019-11-21 12:02:09',\n" +
            "                        'updateBy': null,\n" +
            "                        'updateTime': null,\n" +
            "                        'remark': '',\n" +
            "                        'params': {\n" +
            "                              \n" +
            "                        },\n" +
            "                        'productTypeId': '8',\n" +
            "                        'productTypeShopId': 'ec8ac144349f4e15917c3b379e145993',\n" +
            "                        'productTypeName': '凉菜',\n" +
            "                        'productTypeShopName': 'APP测试商家'\n" +
            "                  },\n" +
            "                  {\n" +
            "                        'searchValue': null,\n" +
            "                        'createBy': '',\n" +
            "                        'createTime': '2019-11-21 12:02:19',\n" +
            "                        'updateBy': null,\n" +
            "                        'updateTime': null,\n" +
            "                        'remark': '',\n" +
            "                        'params': {\n" +
            "                              \n" +
            "                        },\n" +
            "                        'productTypeId': '9',\n" +
            "                        'productTypeShopId': 'ec8ac144349f4e15917c3b379e145993',\n" +
            "                        'productTypeName': '主食',\n" +
            "                        'productTypeShopName': 'APP测试商家'\n" +
            "                  }\n" +
            "            ],\n" +
            "            'PackageDetails': [\n" +
            "                  {\n" +
            "                        'searchValue': null,\n" +
            "                        'createBy': '',\n" +
            "                        'createTime': '2019-11-21 12:06:06',\n" +
            "                        'updateBy': null,\n" +
            "                        'updateTime': null,\n" +
            "                        'remark': '',\n" +
            "                        'params': {\n" +
            "                              \n" +
            "                        },\n" +
            "                        'packageDetailsId': '4',\n" +
            "                        'detailsShopId': 'ec8ac144349f4e15917c3b379e145993',\n" +
            "                        'detailsPackageId': 3,\n" +
            "                        'detailsProductId': 'fb21a8cb9b3e4da8882b9a95d0063071',\n" +
            "                        'detailsProductRealPrice': null,\n" +
            "                        'detailsProductNum': 3,\n" +
            "                        'detailsShopName': 'APP测试商家',\n" +
            "                        'detailsPackageName': '三人管饱',\n" +
            "                        'detailsProductName': '米饭'\n" +
            "                  },\n" +
            "                  {\n" +
            "                        'searchValue': null,\n" +
            "                        'createBy': '',\n" +
            "                        'createTime': '2019-11-21 12:06:15',\n" +
            "                        'updateBy': null,\n" +
            "                        'updateTime': null,\n" +
            "                        'remark': '',\n" +
            "                        'params': {\n" +
            "                              \n" +
            "                        },\n" +
            "                        'packageDetailsId': '5',\n" +
            "                        'detailsShopId': 'ec8ac144349f4e15917c3b379e145993',\n" +
            "                        'detailsPackageId': 3,\n" +
            "                        'detailsProductId': '5903772ded6e4a7aa57d65097b68cef7',\n" +
            "                        'detailsProductRealPrice': null,\n" +
            "                        'detailsProductNum': 1,\n" +
            "                        'detailsShopName': 'APP测试商家',\n" +
            "                        'detailsPackageName': '三人管饱',\n" +
            "                        'detailsProductName': '鱼香肉丝'\n" +
            "                  },\n" +
            "                  {\n" +
            "                        'searchValue': null,\n" +
            "                        'createBy': '',\n" +
            "                        'createTime': '2019-11-21 12:06:24',\n" +
            "                        'updateBy': null,\n" +
            "                        'updateTime': null,\n" +
            "                        'remark': '',\n" +
            "                        'params': {\n" +
            "                              \n" +
            "                        },\n" +
            "                        'packageDetailsId': '6',\n" +
            "                        'detailsShopId': 'ec8ac144349f4e15917c3b379e145993',\n" +
            "                        'detailsPackageId': 3,\n" +
            "                        'detailsProductId': '8c96a7f4e87f470299c62f8772268ac4',\n" +
            "                        'detailsProductRealPrice': null,\n" +
            "                        'detailsProductNum': 1,\n" +
            "                        'detailsShopName': 'APP测试商家',\n" +
            "                        'detailsPackageName': '三人管饱',\n" +
            "                        'detailsProductName': '宫保鸡丁'\n" +
            "                  }\n" +
            "            ],\n" +
            "            'productAttribute': [],\n" +
            "            'token': '4b2e0a79ac1846cf8e41d35135dd92a5'\n" +
            "      }\n" +
            "}";

    String s2="{'id':'1','firstName':'Amit','lastName':'Shekhar'}";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


       /* parseS2();
        parseS1();*/
        //getAsStringParseJson();

        getAsGson();

    }

    private void parseS1(){
        Gson gson=new Gson();
        Login user=gson.fromJson(s1, Login.class);

        Log.e("abc",user.getMsg()+"====");

    }

    private void parseS2(){
        Gson gson=new Gson();
        UserUser user=gson.fromJson(s2, UserUser.class);

        Log.e("abc",user.getFirstName()+"====");
        Log.e("abc",user.toString()+"====");
    }


    private void getAsGson(){
        AndroidNetworking.post("http://49.234.224.238/android/noLog/login/login")
                .addBodyParameter("staffLoginName","18903958133")
                .addBodyParameter("staffLoginPassword","e10adc3949ba59abbe56e057f20f883e")
                .addBodyParameter("version","11234")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsObject(DataBean.class, new ParsedRequestListener() {
                    @Override
                    public void onResponse(Object response) {
                        DataBean<LoginBean>  dataBean= (DataBean<LoginBean>) response;
                        Log.e("kk",dataBean.getMsg());
                        Log.e("kk",dataBean.getData().getToken());
                    }

                    @Override
                    public void onError(ANError anError) {

                    }
                });

    }

    private void getAsString(){
        AndroidNetworking.post("http://49.234.224.238/android/noLog/login/login")
                .addBodyParameter("staffLoginName","18903958133")
                .addBodyParameter("staffLoginPassword","e10adc3949ba59abbe56e057f20f883e")
                .addBodyParameter("version","11234")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsString(new StringRequestListener() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("kk",response);
                    }

                    @Override
                    public void onError(ANError anError) {

                    }
                });
    }


    private void getAsStringParseJson(){
        AndroidNetworking.post("http://49.234.224.238/android/noLog/login/login")
                .addBodyParameter("staffLoginName","18903958133")
                .addBodyParameter("staffLoginPassword","e10adc3949ba59abbe56e057f20f883e")
                .addBodyParameter("version","11234")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsString(new StringRequestListener() {
                    @Override
                    public void onResponse(String response) {
                        Gson gson=new Gson();
                        Type type=new TypeToken<DataBean<LoginBean>>(){}.getType();
                        DataBean<LoginBean> loginBean=gson.fromJson(response, type);
                        Log.e("kk",loginBean.getData().getToken());
                        Log.e("kk",loginBean.getMsg());

                    }

                    @Override
                    public void onError(ANError anError) {

                    }
                });
    }
}
