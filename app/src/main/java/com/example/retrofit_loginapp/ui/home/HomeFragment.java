package com.example.retrofit_loginapp.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.retrofit_loginapp.AllProductAdapter;
import com.example.retrofit_loginapp.InstanceClass;
import com.example.retrofit_loginapp.Modal.Viewproduct;
import com.example.retrofit_loginapp.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {
    ImageView img1;
    int prodata= 0;
    RecyclerView recyclerView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.home_fragment,container,false);
        img1 = view.findViewById(R.id.img1);

        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InstanceClass.CallApi().allproduct(String.valueOf(prodata)).enqueue(new Callback<Viewproduct>() {
                    @Override
                    public void onResponse(Call<Viewproduct> call, Response<Viewproduct> response) {
                       if(response.body().getConnection()==1)
                       {
                           if(response.body().getResult()==1)
                           {
                               AllProductAdapter adapter = new AllProductAdapter(HomeFragment.this,response.body().getProductdata());
                               recyclerView=view.findViewById(R.id.home_recycleview);
                               LinearLayoutManager manager = new LinearLayoutManager(getContext());
                               manager.setOrientation(RecyclerView.VERTICAL);
                               recyclerView.setLayoutManager(manager);
                               recyclerView.setAdapter(adapter);
                           }
                           else {
                               Log.d("QQQ", "onResponse: result = "+response.body().getResult());
                           }
                       }
                    }

                    @Override
                    public void onFailure(Call<Viewproduct> call, Throwable t) {

                    }
                });
            }
        });
        return view;
    }
    private void addfragment(Fragment fragment)
    {
        FragmentManager fm = getParentFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.framelayout,fragment);
        transaction.commit();
    }
}