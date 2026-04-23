package com.example.chatapp.network;

import com.example.chatapp.model.ChatMessage;

import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ChatApi {

    // ================= CHAT =================

    @GET("messages/chats/{userId}")
    Call<List<ChatMessage>> getChatList(@Path("userId") Long userId);

    @GET("messages/{user1}/{user2}")
    Call<List<ChatMessage>> getMessages(
            @Path("user1") Long user1,
            @Path("user2") Long user2,
            @Query("page") int page,
            @Query("size") int size
    );

    @PUT("messages/seen/{id}")
    Call<Void> markSeen(@Path("id") String id);

    // ================= ONLINE STATUS =================

    @GET("messages/status/{userId}")
    Call<Boolean> getUserStatus(@Path("userId") Long userId);

    // ================= MEDIA UPLOAD (🔥 IMPORTANT) =================

    @Multipart
    @POST("media/upload")
    Call<String> uploadImage(@Part MultipartBody.Part file);
}