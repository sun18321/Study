package com.sun.http;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

// book: "https:api.douban.com/v2/"
//translation:  "http://fy.iciba.com/"

public interface NetService {
    @GET("book/search")
    Call<DataBean> getSearchBook(@Query("q") String name,
                                 @Query("tag") String tag,
                                 @Query("start") int start,
                                 @Query("count") int count);

    @GET("book/search")
    Observable<DataBean> getRXSearchBook(@Query("q") String name,
                                         @Query("tag") String tag,
                                         @Query("start") int start,
                                         @Query("count") int count);

    @GET("ajax.php?a=fy&f=auto&t=auto&w=hi%20register")
    Observable<TranslateRegister> getRegister();

    @GET("ajax.php?a=fy&f=auto&t=auto&w=hi%20login")
    Observable<TranslateLogin> getLogin();
}
