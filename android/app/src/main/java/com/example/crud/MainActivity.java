package com.example.crud;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.crud.adapter.MainRVAdapter;
import com.example.crud.client.GithubClient;
import com.example.crud.db.BarangDB;
import com.example.crud.entity.Barang;
import com.example.crud.repo.GithubRepo;
import com.example.crud.service.generator.RetrofitServiceGenerator;
import com.orm.SugarContext;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MAIN";
    private Context context;
    private Activity activity;

    private RecyclerView recyclerView;

    private MainRVAdapter adapter;
    private ArrayList<GithubRepo> data = new ArrayList<>();

    private GithubClient githubClient;

    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.context = this;
        this.activity = this;

        SugarContext.init(context);

        this.adapter = new MainRVAdapter(this.context, this.data);

        this.recyclerView = findViewById(R.id.recyclerview);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(this.context));
        this.recyclerView.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL));

        this.recyclerView.setAdapter(this.adapter);

        this.githubClient = RetrofitServiceGenerator.create(GithubClient.class);

        db = new BarangDB(context).getReadableDatabase();
    }

    @Override
    protected void onResume() {
        super.onResume();

        Barang bar = new Barang(null, "Apel", 5000.0);
        bar.save();

        List<Barang> b = Barang.listAll(Barang.class);

        data.clear();

        for (Barang ba :
                b) {
            GithubRepo item = new GithubRepo();
            item.setName(ba.getNama());
            item.setId(ba.getId().intValue());

            data.add(item);
        }

        adapter.setData(data);
        adapter.notifyDataSetChanged();


//        Call<List<GithubRepo>> call = this.githubClient.reposForUser("iam-adty");
//
//        call.enqueue(new Callback<List<GithubRepo>>() {
//            @Override
//            public void onResponse(Call<List<GithubRepo>> call, Response<List<GithubRepo>> response) {
//                if (response.isSuccessful()) {
//                    if (response.body() != null) {
//                        data.clear();
//                        data.addAll(response.body());
//                        adapter.setData(data);
//                        adapter.notifyDataSetChanged();
//                    } else {
//                        Toast.makeText(activity, "Response body null", Toast.LENGTH_LONG).show();
//                    }
//                } else {
//                    Toast.makeText(activity, response.message(), Toast.LENGTH_LONG).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<GithubRepo>> call, Throwable t) {
//                Toast.makeText(activity, t.getMessage(), Toast.LENGTH_LONG).show();
//            }
//        });
    }

    @Override
    protected void onDestroy() {
        SugarContext.terminate();

        super.onDestroy();
    }
}
