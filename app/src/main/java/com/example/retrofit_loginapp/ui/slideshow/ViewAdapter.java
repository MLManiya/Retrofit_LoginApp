package com.example.retrofit_loginapp.ui.slideshow;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import com.bumptech.glide.Glide;
import com.example.retrofit_loginapp.InstanceClass;
import com.example.retrofit_loginapp.Modal.Productdatum;
import com.example.retrofit_loginapp.Modal.Viewproduct;
import com.example.retrofit_loginapp.R;
import com.example.retrofit_loginapp.Splash_Screen;
import com.example.retrofit_loginapp.TransferDataFragment;
import com.example.retrofit_loginapp.ui.add.Add_Product_Fragment;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewAdapter extends RecyclerView.Adapter<ViewAdapter.adapter> {

    SlideshowFragment slideshowFragment;
    List<Productdatum> productdata;
    TransferDataFragment transferDataFragment;
    public ViewAdapter(SlideshowFragment slideshowFragment, List<Productdatum> productdata, TransferDataFragment transferDataFragment) {
        this.slideshowFragment = slideshowFragment;
        this.productdata = productdata;
        this.transferDataFragment = transferDataFragment;
    }

    @NonNull
    @Override
    public adapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_slideshow_itemfile,parent,false);
        adapter holder = new adapter(view);
        return holder;
    }

    @SuppressLint("RecyclerView")
    @Override
    public void onBindViewHolder(@NonNull adapter holder, int position) {

        holder.item.setText(""+productdata.get(position).getProName());
        holder.price.setText(""+productdata.get(position).getProPrice());
        holder.category.setText(""+productdata.get(position).getProDes());
        Glide.with(slideshowFragment).load("https://hardyecommerce.000webhostapp.com/Register_API/"+productdata.get(position).getProImage()).into(holder.imageView);

        holder.more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu(slideshowFragment.getContext(),view);
                popupMenu.getMenuInflater().inflate(R.menu.menumore, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        if(menuItem.getItemId()==R.id.nav_update)
                        {
//                            Add_Product_Fragment apf = new Add_Product_Fragment();
//                            Bundle args = new Bundle();
//                            apf.setArguments(args);
//                            addfragment(new Add_Product_Fragment());
//                            transferDataFragment.getDataFromFragment(Integer.parseInt(productdata.get(position).getId()),
//                                    productdata.get(position).getProName(),
//                                    productdata.get(position).getProPrice(),
//                                    productdata.get(position).getProDes(),
//                                    productdata.get(position).getProImage());


                            Splash_Screen.editor.putString("from","update");


                            Splash_Screen.editor.putString("id",""+productdata.get(position).getId());
                            Splash_Screen.editor.putString("name",""+productdata.get(position).getProName());
                            Splash_Screen.editor.putString("price",""+productdata.get(position).getProPrice());
                            Splash_Screen.editor.putString("description",""+productdata.get(position).getProDes());
                            Splash_Screen.editor.putString("imagedata",""+productdata.get(position).getProImage());
                            Splash_Screen.editor.putString("imagename",""+productdata.get(position).getProImage());

                            Splash_Screen.editor.commit();

                            Toast.makeText(slideshowFragment.getContext(), "Update pref", Toast.LENGTH_LONG).show();

//                            InstanceClass.CallApi().updateproduct(Splash_Screen.sharedPreferences.getInt("sellerid",0),Splash_Screen.sharedPreferences.getString("pname",null), Splash_Screen.sharedPreferences.getString("pprice",null),Splash_Screen.sharedPreferences.getString("pcategory",null),Splash_Screen.sharedPreferences.getString("imagedata",null)).enqueue(new Callback<UpdateProduct>() {
//                        @Override
//                        public void onResponse(Call<UpdateProduct> call, Response<UpdateProduct> response) {
//                            if(response.body().getConnection()==1){
//                                if(response.body().getResult()==1){
//                                    Log.d("RRR", "onResponse: update result = "+response.body().getResult());
//                                }
//                            }
//                        }
//
//                        @Override
//                        public void onFailure(Call<UpdateProduct> call, Throwable t) {
//
//                        }
//                    });
                            addfragment(new Add_Product_Fragment());
                            Toast.makeText(slideshowFragment.getContext(),"Update successfully",Toast.LENGTH_LONG);
                        }
                        if(menuItem.getItemId()==R.id.nav_delete)
                        {
                            InstanceClass.CallApi().deleteproduct(Integer.parseInt(productdata.get(position).getId())).enqueue(new Callback<Viewproduct>() {
                                @Override
                                public void onResponse(Call<Viewproduct> call, Response<Viewproduct> response) {
                                    if(response.body().getConnection()==1)
                                    {
                                        Toast.makeText(slideshowFragment.getContext(),"Delete successfully",Toast.LENGTH_LONG);
                                        productdata.remove(position);
                                        notifyDataSetChanged();
                                    }
                                    else
                                    {
                                        Toast.makeText(slideshowFragment.getContext(), "Delete Fail", Toast.LENGTH_LONG).show();
                                    }
                                }

                                @Override
                                public void onFailure(Call<Viewproduct> call, Throwable t) {

                                }
                            });
                        }
                        return true;
                    }
                });
                popupMenu.show();
            }
        });
    }

    private void addfragment(Fragment fragment) {
        FragmentManager fm = slideshowFragment.getParentFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.framelayout,fragment);
        transaction.commit();
    }

    @Override
    public int getItemCount() {
        return productdata.size();
    }
    public class adapter extends ViewHolder {
        ImageView imageView,more;
        TextView item,price,category;

        public adapter(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.rec_img);
            item = itemView.findViewById(R.id.rec_itemname);
            price = itemView.findViewById(R.id.rec_price);
            category = itemView.findViewById(R.id.rec_desc);
            more = itemView.findViewById(R.id.rec_more);
        }
    }
    }


