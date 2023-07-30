package com.example.retrofit_loginapp.ui.add;

import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.retrofit_loginapp.InstanceClass;
import com.example.retrofit_loginapp.Modal.AddProduct;
import com.example.retrofit_loginapp.Modal.UpdateProduct;
import com.example.retrofit_loginapp.R;
import com.example.retrofit_loginapp.Splash_Screen;
import com.example.retrofit_loginapp.ui.home.HomeFragment;
import com.theartofdev.edmodo.cropper.CropImage;

import java.io.ByteArrayOutputStream;
import java.util.Base64;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Add_Product_Fragment extends Fragment {

    ImageView imageView;
    EditText pname,pprice,pdescription;
    Button submitbutton;

    int SELECT_PICTURE = 200;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_addproduct,container,false);

        imageView = view.findViewById(R.id.productimage);
        pname=view.findViewById(R.id.productname);
        pprice=view.findViewById(R.id.productprice);
        pdescription=view.findViewById(R.id.productdescription);
        submitbutton = view.findViewById(R.id.submitproduct);
        String from = Splash_Screen.sharedPreferences.getString("from",null);

        if(getArguments()!=null) {

            pname.setText(""+getArguments().getString("name"));
            pprice.setText(""+getArguments().getString("price"));
            pdescription.setText(""+getArguments().getString("category"));
            Glide.with(Add_Product_Fragment.this)
                    .load("https://hardyecommerce.000webhostapp.com/Register_API/"+getArguments().getString("img"))
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .skipMemoryCache(true)
                    .into(imageView);
            Log.d("NNN", "onCreateView: Name="+getArguments().getString("name"));
        }


        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                imageChooser();
            }
        });

        if(from.equals("update")){

            Toast.makeText(getContext(), "Update pref", Toast.LENGTH_LONG).show();

            Glide.with(Add_Product_Fragment.this).load("https://hardyecommerce.000webhostapp.com/Register_API/"+Splash_Screen.sharedPreferences.getString("pimage",null)).into(imageView);
            pname.setText(Splash_Screen.sharedPreferences.getString("pname",null));
            pprice.setText(Splash_Screen.sharedPreferences.getString("pprice",null));
            pdescription.setText(Splash_Screen.sharedPreferences.getString("pcategory",null));
            //imageView.setImageResource(Integer.parseInt(Splash_Screen.sharedPreferences.getString("pimage",null)));
        }

        submitbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bitmap bitmap= ((BitmapDrawable) imageView.getDrawable()).getBitmap();
                // initialize byte stream
                ByteArrayOutputStream stream=new ByteArrayOutputStream();
                // compress Bitmap
                bitmap.compress(Bitmap.CompressFormat.JPEG,20,stream);
                // Initialize byte array
                byte[] Imageinbytes=stream.toByteArray();
                // get base64 encoded string
                String imagedata = null;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    imagedata = Base64.getEncoder().encodeToString(Imageinbytes);
                }
                // set encoded text on textview
                addFragment(new HomeFragment());

                if(from.equals("add"))
                {
                    InstanceClass.CallApi().addproduct(Splash_Screen.sharedPreferences.getInt("sellerid",0),pname.getText().toString(),pprice.getText(),pdescription.getText().toString(),imagedata).enqueue(new Callback<AddProduct>() {
                        @Override
                        public void onResponse(Call<AddProduct> call, Response<AddProduct> response) {
                            if(response.body().getConnection()==1)
                            {
                                if(response.body().getProductaddd()==1)
                                {
                                    Log.d("TTT", "onResponse: Connection"+response.body().getConnection());
                                }else
                                {
                                    Log.d("TTT", "onResponse: NOT Connection"+response.body().getConnection());
                                }
                            }else
                            {
                                Log.d("TTT", "onResponse: something Connection"+response.body().getConnection());

                            }
                        }

                        @Override
                        public void onFailure(Call<AddProduct> call, Throwable t) {
                            Log.e("TTT", "onResponse: failes Connection"+t.getLocalizedMessage());
                        }
                    });
                }
                if(from.equals("update")){

                    Glide.with(Add_Product_Fragment.this).load("https://hardyecommerce.000webhostapp.com/Register_API/"+Splash_Screen.sharedPreferences.getString("pimage",null)).into(imageView);
                    pname.setText(Splash_Screen.sharedPreferences.getString("pname",null));
                    pprice.setText(Splash_Screen.sharedPreferences.getString("pprice",null));
                    pdescription.setText(Splash_Screen.sharedPreferences.getString("pcategory",null));

                    InstanceClass.CallApi().updateproduct((Splash_Screen.sharedPreferences.getInt("pid",0)),Splash_Screen.sharedPreferences.getString("pname",null), Splash_Screen.sharedPreferences.getString("pprice",null),Splash_Screen.sharedPreferences.getString("pcategory",null),Splash_Screen.sharedPreferences.getString("imagedata",null)).enqueue(new Callback<UpdateProduct>() {
                        @Override
                        public void onResponse(Call<UpdateProduct> call, Response<UpdateProduct> response) {
                            if(response.body().getConnection()==1){
                                if(response.body().getResult()==1){
                                    Log.d("RRR", "onResponse: update result = "+response.body().getResult());
                                    Toast.makeText(getContext(), "Update pref", Toast.LENGTH_LONG).show();
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<UpdateProduct> call, Throwable t) {

                        }
                    });
                }
            }
        });

        return view;
    }

    private void addFragment(Fragment fragment) {
        FragmentManager fm = getParentFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.framelayout,fragment);
        transaction.commit();
    }
    void imageChooser() {
        CropImage.activity()
                .start(getContext(), this);
    }
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                Uri resultUri = result.getUri();
                imageView.setImageURI(resultUri);
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
            }
        }

    }
}