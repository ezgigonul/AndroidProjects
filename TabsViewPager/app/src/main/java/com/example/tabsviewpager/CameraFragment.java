package com.example.tabsviewpager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import static android.app.Activity.RESULT_OK;

public class CameraFragment extends Fragment {

    FloatingActionButton fab;
    ImageView image;
    private static final int TAKE_PICTURE = 1;

    public CameraFragment() {
        // super();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_camera, container, false);
        final Context ctx=getContext();
        image=(ImageView)view.findViewById(R.id.image);
        fab = view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("WrongConstant")
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, TAKE_PICTURE);

                /*final Snackbar snackbar = Snackbar.make(view, "Clicked FAB", Snackbar.LENGTH_LONG);
                snackbar.setAction("Undo", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        snackbar.dismiss();
                        // Geri Al Butonu Tıklandığında yapılacak işlemler
                        Toast.makeText(ctx,"Undo button is clicked.",Toast.LENGTH_LONG).show();
                    }
                });
                snackbar.show();*/
            }
        });
        return view;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == TAKE_PICTURE) {
            if (resultCode == RESULT_OK) {
                Bitmap bmp = (Bitmap) data.getExtras().get("data");
                image.setImageBitmap(bmp);
            }
        }
    }


}
