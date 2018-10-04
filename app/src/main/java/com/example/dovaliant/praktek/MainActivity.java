package com.example.dovaliant.praktek;
import com.example.dovaliant.praktek.ApiService;
import com.example.dovaliant.praktek.Image;

import com.example.dovaliant.praktek.RetroClient;

import android.app.ProgressDialog;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Observable;
import rx.Observer;


import java.util.List;


public class MainActivity extends AppCompatActivity {


    private ProgressDialog pDialog;
    private RecyclerView recyclerView;
    private ImageAdapter eAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pDialog = new ProgressDialog(MainActivity.this);
        pDialog.setMessage("Loading Data.. Please wait...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();

        /*Create handle for the RetrofitInstance interface*/
        ApiService service = RetroClient.getRetrofitInstance().create(ApiService.class);
        Call<List<Image>> call = service.getAllImage();
        call.enqueue(new Callback<List<Image>>() {
            @Override
            public void onResponse(Call<List<Image>> call, Response<List<Image>> response) {
                pDialog.dismiss();
                generateDataList(response.body());
            }

            @Override
            public void onFailure(Call<List<Image>> call, Throwable t) {
                pDialog.dismiss();
                Toast.makeText(MainActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /*Method to generate List of data using RecyclerView with custom adapter*/
    private void generateDataList(List<Image> photoList) {
        recyclerView = findViewById(R.id.imageIcon);
        eAdapter = new ImageAdapter(this,photoList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(eAdapter);
    }

    /*
    private void creatingObserable(){
        final Observable<List<String>> listObserable= Observable.interval(6,12,a);
        listObserable.subscribe(new Observer<List<String>>() {
            @Override
            public void onCompleted() {
                recyclerView.clearOnChildAttachStateChangeListeners();
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(List<String> data) {

            }
        });
    }
*/

    public void  getImageId(List<Image> photoList) {



    }
}

