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

    @FormUrlEncoded
    @POST(ServerConfig.GET_ORDER_DETAIL)
    Call<OrderDetailResponse> getOrderDetail(@Field("dealer_id") String dealer_id,
                                             @Field("order_id") String order_id);

    @FormUrlEncoded
    @POST(ServerConfig.GET_ORDER_LIST)
    Call<OrderListResponse> getOrderList(@Field("dealer_id") String dealer_id);

    @FormUrlEncoded
    @POST(ServerConfig.GET_PAYMENT_LIST)
    Call<PaymentListResponse> getPaymentList(@Field("dealer_id") String dealer_id);

    @FormUrlEncoded
    @POST(ServerConfig.GET_PAYMENT_DETAIL)
    Call<PaymentDetailResponse> getPaymentDetail(@Field("dealer_id") String dealer_id,
                                                 @Field("payment_id") String payment_id);

    @FormUrlEncoded
    @POST(ServerConfig.PLACE_ORDER)
    Call<PlaceOrderResponse> placeOrder(@Field("order") String order_json);

    @FormUrlEncoded
    @POST(ServerConfig.GET_TAX_PERCENTAGE)
    Call<PercentageResponse> getTaxPercentage(@Field("dealer_id") String dealer_id);

    @FormUrlEncoded
    @POST(ServerConfig.GET_PROFILE)
    Call<GetProfileResponse> getProfile(@Field("phone") String phone);

    @Multipart
    @POST(ServerConfig.UPDATE_PROFILE)
    Call<UpdateProfileResponse> updateProfile(@Part("dealer_id") RequestBody dealer_id,
                                           @Part("responsible_person_name") RequestBody responsible_person_name,
                                           @Part("whatsappno") RequestBody whatsappno,
                                           @Part("mobileno") RequestBody mobileno,
                                           @Part("dob") RequestBody dob,
                                           @Part MultipartBody.Part image);

    @FormUrlEncoded
    @POST(ServerConfig.GET_DASHBOARD)
    Call<DashboardResponse> getDashboard(@Field("dealer_id") String dealer_id);
}
