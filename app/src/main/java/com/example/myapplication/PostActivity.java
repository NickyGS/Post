package com.example.myapplication;

import android.os.Bundle;
import android.telecom.Call;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import javax.security.auth.callback.Callback;

public class PostActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private PostAdapter postAdapter;
    private ApiClient.ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        postAdapter = new PostAdapter();
        recyclerView.setAdapter(postAdapter);

        apiInterface = ApiClient.getRetrofitInstance().create(ApiClient.ApiInterface.class);
        // Запрос на получение списка постов
        Call getPostsCall = apiInterface.getPosts();
        getPostsCall.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                List<Post> posts = response.body();
                postAdapter.setPosts(posts);
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                Toast.makeText(PostActivity.this, "Ошибка при получении постов", Toast.LENGTH_SHORT).show();
            }
        });

        // Обработчик клика на посте
        postAdapter.setOnItemClickListener(new PostAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Post post) {
                openUserInfoForm(post.getUserId());
            }
        });
    }

    private void openUserInfoForm(int userId) {
        // Код для открытия формы с информацией о пользователе
        // Используйте полученный userId для получения данных о пользователе с помощью Retrofit
        // apiInterface.getUser(userId) - это пример запроса на получение информации о пользователе по его ID
        // Обработайте полученные данные и отобразите их на новом экране или в диалоговом окне
    }
}