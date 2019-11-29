package com.cdc.num;

import com.google.gson.Gson;
public class Gener {


    public static void main(String[] args) {

        String s="{\n" +
                "\t\t\t\"createBy\": \"\",\n" +
                "\t\t\t\"createTime\": \"2019-11-20 16:24:36\",\n" +
                "\t\t\t\"updateBy\": \"\",\n" +
                "\t\t\t\"remark\": \"18903958133\",\n" +
                "\t\t\t\"shopId\": \"ec8ac144349f4e15917c3b379e145993\",\n" +
                "\t\t\t\"shopName\": \"APP测试商家\",\n" +
                "\t\t\t\"shopCompanyId\": \"df46fd5233e5463098391cc84e68a04a\"\n" +
                "\t\t}";
        /*Gson g=new Gson();
        Shop shop=g.fromJson(s,Shop.class);
        System.out.println(shop.getShopId());*/
    }


    public class Shop{
        private String createBy;
        private String createTime;
        private String updateBy;
        private String remark;
        private String shopId;
        private String shopName;
        private String shopCompanyId;

        public String getCreateBy() {
            return createBy;
        }

        public void setCreateBy(String createBy) {
            this.createBy = createBy;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getUpdateBy() {
            return updateBy;
        }

        public void setUpdateBy(String updateBy) {
            this.updateBy = updateBy;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getShopId() {
            return shopId;
        }

        public void setShopId(String shopId) {
            this.shopId = shopId;
        }

        public String getShopName() {
            return shopName;
        }

        public void setShopName(String shopName) {
            this.shopName = shopName;
        }

        public String getShopCompanyId() {
            return shopCompanyId;
        }

        public void setShopCompanyId(String shopCompanyId) {
            this.shopCompanyId = shopCompanyId;
        }
    }


}
