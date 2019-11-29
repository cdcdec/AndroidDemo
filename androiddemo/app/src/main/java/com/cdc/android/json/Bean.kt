package com.cdc.android.json
/*val searchValue: String="",*/
data class LoginBean(
    val product: List<ProductBean>,
    val shop: ShopBean,
    val staff: StaffBean,
    val company: CompanyBean,
    val productPackage: List<ProductPackageBean>,
    val productType: List<ProductTypeBean>,
    val PackageDetails: List<PackageDetailsBean>,
    val productAttribute: List<Any?>,
    val token: String
)

data class ProductBean(
    var searchValue: Any?,
    val createBy: String,
    val createTime: String,
    val updateBy: String,
    var updateTime: Any?,
    val remark: String,
    val productId: String,
    val productShopId: String,
    val productTypeId: String,
    val productName: String,
    val productPrice: Double,
    val productNum: Int,
    val productStatus: Int,
    val productDescription: String,
    var productImage: Any?,
    val productShopName: String,
    val productTypeName: String
)

data class ShopBean(
    var searchValue: Any?,
    val createBy: String,
    val createTime: String,
    val updateBy: String,
    var updateTime: Any?,
    val remark: String,
    val shopId: String,
    val shopName: String,
    val shopCompanyId: String
)

data class StaffBean(
    var searchValue: Any?,
    val createBy: String,
    val createTime: String,
    val updateBy: String,
    val updateTime: String,
    val remark: String,
    val staffId: String,
    val staffName: String,
    val staffRole: Int,
    val staffLoginName: String,
    val staffLoginPassword: String,
    val staffCompanyId: String,
    val staffShopId: String
)

data class CompanyBean(
    var searchValue: Any?,
    val createBy: String,
    val createTime: String,
    val updateBy: String,
    var updateTime: Any?,
    val remark: String,
    val companyId: String,
    val companyName: String,
    val companyContact: String,
    val companyPhone: String,
    var companyLogo: Any?
)

data class ProductPackageBean(
    var searchValue: Any?,
    val createBy: String,
    val createTime: String,
    var updateBy: Any?,
    var updateTime: Any?,
    val remark: String,
    val productPackageId: Int,
    val productPackageShopId: String,
    val productPackageName: String,
    val productPackagePrice: Int,
    val productPackageStatus: Int,
    val productPackageNum: Int,
    val productPackageDescription: String,
    var productPackageImage: Any?
)

data class ProductTypeBean(
    var searchValue: Any?,
    val createBy: String,
    val createTime: String,
    var updateBy: Any?,
    var updateTime: Any?,
    val remark: String,
    val productTypeId: String,
    val productTypeShopId: String,
    val productTypeName: String,
    val productTypeShopName: String
)

data class PackageDetailsBean(
    var searchValue: Any?,
    val createBy: String,
    val createTime: String,
    var updateBy: Any?,
    var updateTime: Any?,
    val remark: String,
    val packageDetailsId: String,
    val detailsShopId: String,
    val detailsPackageId: Int,
    val detailsProductId: String,
    var detailsProductRealPrice: Any?,
    val detailsProductNum: Int,
    val detailsShopName: String,
    val detailsPackageName: String,
    val detailsProductName: String
)

data class User(
    var id:String="",
    var firstname:String="1234",
    var lastname:String=""
)

data class Login(
        val code:Int,
        val msg:String,
        val data:LoginBean
)

data class DataBean<T>(
        val code:Int,
        val msg:String,
        val data:T
)