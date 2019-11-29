package com.example.cobahello;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.example.cobahello.Model.Dosen;

import com.example.cobahello.DataDosenService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    DataDosenService service;

    private Call<Dosen> DefaultResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getDosenAll();
        insertDosen();
    }

    private void getDosenAll() {
        Call<ArrayList<Dosen>> call = service.getDosenAll("72170126");
        call.enqueue(new Callback<ArrayList<Dosen>>() {
            @Override
            public void onResponse(Call<ArrayList<Dosen>> call, Response<ArrayList<Dosen>> response) {
                for (Dosen dosen : response.body()) {
                    System.out.println("Nama    : " + dosen.getNamaDosen());
                    System.out.println("NIDN    : " + dosen.getNidn());
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Dosen>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Something wrong...",
                        Toast.LENGTH_LONG).show();

            }
        });
    }

    private void insertDosen() {

        DataDosenService service = RetrofitClient.getRetrofitInstance().create(DataDosenService.class);
        Call<DefaultResult> call = service.insert_dosen("Dendy", "001", "Jogja", "dendy@dendy.com",
                "Skom", "dendy.jpg", "72170126");
        call.enqueue(new Callback<DefaultResult>() {
            @Override
            public void onResponse(Call<DefaultResult> call, Response<DefaultResult> response) {

                System.out.println(response.body().getStatus());
            }

            @Override
            public void onFailure(Call<DefaultResult> call, Throwable t) {
                System.out.println("message:" + t.getMessage());
                Toast.makeText(MainActivity.this, "Seomething Wrong", Toast.LENGTH_SHORT).show();

            }
        });
    }
}