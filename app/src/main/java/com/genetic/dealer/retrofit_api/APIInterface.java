package com.genetic.dealer.retrofit_api;

import com.genetic.dealer.model.*;

import okhttp3.*;
import retrofit2.*;
import retrofit2.Call;
import retrofit2.http.*;

public interface APIInterface {

    @FormUrlEncoded
    @POST(ServerConfig.LOGIN_API)
    Call<LoginResponse> loginApi(@Field("phone") String phone,
                                 @Field("device_type") String device_type,
                                 @Field("device_token") String device_token);

    @FormUrlEncoded
    @POST(ServerConfig.OTP_API)
    Call<LoginResponse> verifyOtpApi(@Field("phone") String phone,
                                     @Field("otp") String otp);

    @FormUrlEncoded
    @POST(ServerConfig.GET_PRODUCT_CATEGORY)
    Call<ProductCategoryResponse> getProductCategory(@Field("dealer_id") String dealer_id);

    @FormUrlEncoded
    @POST(ServerConfig.GET_PRODUCT_LIST)
    Call<ProductListResponse> getProductList(@Field("dealer_id") String dealer_id,
                                             @Field("category_id") String category_id);

    @FormUrlEncoded
    @POST(ServerConfig.GET_PRODUCT_OPTION_LIST)
    Call<ProductOptionListResponse> getProductOptionList(@Field("dealer_id") String dealer_id,
                                                   @Field("product_id") String product_id);
}
