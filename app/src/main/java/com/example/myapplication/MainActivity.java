package com.example.myapplication;

public class MainActivity {
    private static final String BASE_URL = "https://example.com/api/";
    private static Retrofit retrofit;

    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public interface ApiInterface {
        @GET("posts")
        Call<List<Post>> getPosts();

        @GET("users/{id}")
        Call<User> getUser(@Path("id") int userId);
    }
}
