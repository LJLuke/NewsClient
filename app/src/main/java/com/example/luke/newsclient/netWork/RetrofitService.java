package com.example.luke.newsclient.netWork;

import com.example.luke.newsclient.entity.GanHuo.GhCategory;
import com.example.luke.newsclient.entity.GanHuo.GhNews;
import com.example.luke.newsclient.entity.News;
import com.example.luke.newsclient.entity.Picture;
import com.example.luke.newsclient.entity.Search;
import com.example.luke.newsclient.entity.UserInfo;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface RetrofitService {

    @GET
    Observable<ApiWrapper<ApiWrapper.ResultBean<List<News>>>> getNewsList(@Url String url,@Query("type") String type, @Query("key") String key);

    @GET("category/{category}")
    Observable<GhApiWrapper<List<GhCategory>>> getCategoryList(@Path("category") String category);

    @GET("data/id/{id}/count/{count}/page/{page}")
    Observable<GhApiWrapper<List<GhNews>>> getGhNewsList(@Path("id") String id, @Path("count") int count, @Path("page") int page);

    @GET
    Observable<PictureWrapper<List<Picture>>> getPictureList(@Url String url,@Query("type") int type,@Query("page") int page);

    @GET
    Observable<SearchWrapper<SearchWrapper.DataBean<List<Search>>>> getSearchList(@Url String url, @Query("word") String word);

    @Multipart
    @POST
    Observable<String> postUserInfo(@Url String url, @Part("type") int type, @Part("username") String username, @Part("password") String password, @Part("motto") String motto, @Part MultipartBody.Part file);

    @Multipart
    @POST
    Observable<UserInfo> getUserInfo(@Url String url, @Part("type") int type, @Part("username") String username);
}
