package com.example.fastfoodproject.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.bumptech.glide.Glide;
import com.example.fastfoodproject.R;
import com.example.fastfoodproject.adapter.LoaiMonanAdapter;
import com.example.fastfoodproject.model.LoaiMonan;
import com.example.fastfoodproject.model.LoaiMonanModel;
import com.example.fastfoodproject.retrofit.ApiFastFood;
import com.example.fastfoodproject.retrofit.RetrofitClient;
import com.example.fastfoodproject.utils.Utils;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    ViewFlipper viewFlipper;
    RecyclerView recyclerViewMain;
    NavigationView navigationView;
    ListView listViewMain;
    DrawerLayout drawerLayout;

    LoaiMonanAdapter loaiMonanAdapter;

    List<LoaiMonan> mangloaisp;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    ApiFastFood apiFastFood;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        apiFastFood = RetrofitClient.getInstance(Utils.BASE_URL).create(ApiFastFood.class);

        Anhxa();
        ActionBar();

        if(isConnected(this)){
            Toast.makeText(getApplicationContext(), "ok", Toast.LENGTH_LONG).show();
            ActionViewFlipper();
            getLoaiMonan();
        }else{
            Toast.makeText(getApplicationContext(), "khong co Internet", Toast.LENGTH_LONG).show();
        }
    }

    private void getLoaiMonan() {

    }


    private void Anhxa(){
        toolbar = findViewById(R.id.toolbarMain);
        viewFlipper = findViewById(R.id.viewfliperMain);
        recyclerViewMain = findViewById(R.id.recycleview);
        listViewMain = findViewById(R.id.listviewMain);
        navigationView = findViewById(R.id.navigationview);
        drawerLayout = findViewById(R.id.drawerLayout);
        //tao list
        mangloaisp = new ArrayList<>();
        //tao adapter
        loaiMonanAdapter = new LoaiMonanAdapter(getApplicationContext(), mangloaisp);
        listViewMain.setAdapter(loaiMonanAdapter);
    }

    private  boolean isConnected (Context context){
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifi = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo mobile = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if((wifi != null && wifi.isConnected()) || (mobile != null && mobile.isConnected())){
            return true;
        }else{
            return false;
        }
    }
    private void ActionViewFlipper(){
        List<String> mangquangcao = new ArrayList<>();
        mangquangcao.add("https://intphcm.com/data/upload/banner-la-gi.jpg");
        mangquangcao.add("https://intphcm.com/data/upload/mau-banner-online-tre-em.jpg");
        mangquangcao.add("https://intphcm.com/data/upload/mau-banner-shopee.jpg");
        mangquangcao.add("");
        for (int i =0; i<mangquangcao.size(); i++){
            ImageView imageView = new ImageView(getApplicationContext());
            Glide.with(getApplicationContext()).load(mangquangcao.get(i)).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            viewFlipper.addView(imageView);
        }
        viewFlipper.setFlipInterval(3000);
        viewFlipper.setAutoStart(true);
        Animation slide_in = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_in_right);
        Animation slide_out = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_out_right);
        viewFlipper.setInAnimation(slide_in);
        viewFlipper.setOutAnimation(slide_out);
    }

    private void ActionBar(){
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(android.R.drawable.ic_menu_sort_by_size);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }
}