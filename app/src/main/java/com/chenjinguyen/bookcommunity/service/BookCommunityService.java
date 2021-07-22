package com.chenjinguyen.bookcommunity.service;

import com.chenjinguyen.bookcommunity.model.Response.AuthResponse;
import com.chenjinguyen.bookcommunity.model.Response.BookResponse;
import com.chenjinguyen.bookcommunity.model.Response.BooksResponse;
import com.chenjinguyen.bookcommunity.model.Response.CommentResponse;
import com.chenjinguyen.bookcommunity.model.Response.CommentsResponse;
import com.chenjinguyen.bookcommunity.model.Response.EpisodeReponse;
import com.chenjinguyen.bookcommunity.model.Response.EpisodesReponse;
import com.chenjinguyen.bookcommunity.model.Response.LikeResponse;
import com.chenjinguyen.bookcommunity.model.Response.PointResponse;

import java.util.Date;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface BookCommunityService {
    @GET("book/")
    Call<BooksResponse> allBook();

    @GET("book/")
    Call<BookResponse> detailBook(@Query("id") int id);

    @POST("signin/")
    @FormUrlEncoded
    Call<AuthResponse> signin(@Field("username") String username, @Field("password") String password, @Field("remember") boolean remember);

    @POST("signup/")
    @FormUrlEncoded
    Call<AuthResponse> signup(@Field("username") String username, @Field("email") String email, @Field("password") String password, @Field("name") String name, @Field("birthday") String birthday, @Field("gender") String gender);

    @GET("me/")
    Call<AuthResponse> me(@Header("Authorization") String bearer);

    @GET("me/book/")
    Call<BooksResponse> meBook(@Header("Authorization") String bearer);

    @GET("me/point")
    Call<PointResponse> mePoint(@Header("Authorization") String bearer);

    @GET("book/search")
    Call<BooksResponse> searchBook(@Query("title")String keyword);

    @GET("book/{book}/episode")
    Call<EpisodesReponse> getEpisodeOfBook(@Path("book") int id);

    @GET("episode/{episode}")
    Call<EpisodeReponse> getEpisode(@Path("episode") int id);

    @GET("book/category/{category}")
    Call<BooksResponse> getBookCategory(@Path("category") String category);

    @GET("book/{book}/comment/")
    Call<CommentsResponse> getCommentBook(@Path("book") int id);

    @POST("comment/")
    @FormUrlEncoded
    Call<CommentResponse> postcommentBook(@Header("Authorization") String bearer,@Field("idbook") int idbook, @Field("content") String content);

    @GET("me/book/favorite/")
    Call<BooksResponse> getfavorite(@Header("Authorization") String bearer);

    @POST("me/book/favorite/")
    @FormUrlEncoded
    Call<LikeResponse> postFavoriteBook(@Header("Authorization") String bearer,@Field("idbook") int idbook);

    @GET("me/book/{idbook}/favorite/")
    Call<LikeResponse> TestFavorite(@Header("Authorization") String bearer, @Path("idbook") int idbook);



}