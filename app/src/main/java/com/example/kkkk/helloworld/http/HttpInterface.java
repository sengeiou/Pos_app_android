package com.example.kkkk.helloworld.http;


import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * author：Anumbrella
 * Date：16/5/29 下午3:52
 */

public interface HttpInterface {
    /**
     * 登录
     *
     * @param requestBody
     * @return
     */
    //192.168.2.251:50000/expand/login
    @Headers({"Content-Type:application/json", "Accept:application/json"})
    @POST("user/login")
    Call<ResponseBody> login(@Body RequestBody requestBody);

    /**
     * 查询明星员工
     *
     * @return
     */
    @Headers({"Content-Type:application/json", "Accept:application/json"})
    @GET("user/superStar")
    Call<ResponseBody> StarStaffList();

    /**
     * 紧急通知
     *
     * @return
     */
    @Headers({"Content-Type:application/json", "Accept:application/json"})
    @GET("note/urgency/list")
    Call<ResponseBody> WarringMsg();

    /**
     * 紧急通知
     *
     * @return
     */
    @Headers({"Content-Type:application/json", "Accept:application/json"})
    @GET("note/list")
    Call<ResponseBody> ReadList(@Header("USER-TOKEN") String token,@Query("isRead") String isRead);
}
