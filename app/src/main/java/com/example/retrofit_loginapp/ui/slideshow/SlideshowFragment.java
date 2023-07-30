package com.example.retrofit_loginapp.ui.slideshow;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.retrofit_loginapp.InstanceClass;
import com.example.retrofit_loginapp.Modal.Viewproduct;
import com.example.retrofit_loginapp.R;
import com.example.retrofit_loginapp.Splash_Screen;
import com.example.retrofit_loginapp.TransferDataFragment;
import com.example.retrofit_loginapp.ui.add.Add_Product_Fragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SlideshowFragment extends Fragment {

    ViewAdapter adapter;
    RecyclerView recyclerView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_slideshow,container,false);

        InstanceClass.CallApi().viewproduct(Splash_Screen.sharedPreferences.getInt("sellerid",0)).enqueue(new Callback<Viewproduct>() {
            @Override
            public void onResponse(Call<Viewproduct> call, Response<Viewproduct> response) {
                if(response.body().getConnection()==1)
                {
                    if(response.body().getResult()==1)
                    {
                        adapter=new ViewAdapter(SlideshowFragment.this,response.body().getProductdata(), new TransferDataFragment() {
                            @Override
                            public void getDataFromFragment(int parseInt, String proName, String proPrice, String proDes, String proImage) {
                                Add_Product_Fragment add_product_fragment=new Add_Product_Fragment();
                                Bundle args = new Bundle();
                                args.putString("id", String.valueOf(parseInt));
                                args.putString("name", proName);
                                args.putString("price", proPrice);
                               // args.putString("stock", stock);
                                args.putString("category", proDes);
                                args.putString("img",proImage);
                                add_product_fragment.setArguments(args);

                                FragmentManager fm = getActivity().getSupportFragmentManager();
                                FragmentTransaction transaction = fm.beginTransaction();
                                transaction.replace(R.id.framelayout, add_product_fragment);
                                transaction.commit();
                            }

//                            @Override
//                            public void getDataFromFragment(int id, String name, String price, String stock, String category,String imgeData) {
//                                Add_Product_Fragment add_product_fragment=new Add_Product_Fragment();
//                                Bundle args = new Bundle();
//                                args.putString("id", String.valueOf(id));
//                                args.putString("name", name);
//                                args.putString("price", price);
//                                args.putString("stock", stock);
//                                args.putString("category", category);
//                                args.putString("img",imgeData);
//                                add_product_fragment.setArguments(args);
//
//
//                                FragmentManager fm = getActivity().getSupportFragmentManager();
//                                FragmentTransaction transaction = fm.beginTransaction();
//                                transaction.replace(R.id.framelayout, add_product_fragment);
//                                transaction.commit();
//
//
//                            }
                        });
                        recyclerView=view.findViewById(R.id.recycleview);
                        LinearLayoutManager manager = new LinearLayoutManager(getContext());
                        manager.setOrientation(RecyclerView.VERTICAL);
                        recyclerView.setLayoutManager(manager);
                        recyclerView.setAdapter(adapter);
                        Log.d("TTT", "onResponse: Connection"+response.body().getConnection());
                    }
                    else
                    {
                        Log.d("TTT", "onResponse: NOT Connection"+response.body().getConnection());
                    }
                }
                else
                {
                    Log.d("TTT", "onResponse: SOMETHING Connection"+response.body().getConnection());
                }
            }

            @Override
            public void onFailure(Call<Viewproduct> call, Throwable t) {

            }
        });
        return view;
    }
}
