package com.example.sopo_19.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.sopo_19.R;

import org.json.JSONException;
import org.json.JSONObject;

public class KiriFragment extends Fragment {

    TextView tv_Total,tv_Sembuh,tv_Rawat,tv_Meninggal;

    public KiriFragment(){
        //Constructor
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        View tampil = inflater.inflate(R.layout.fragment_kiri, container, false);

        tv_Total = tampil.findViewById(R.id.isi_tv_total);
        tv_Sembuh = tampil.findViewById(R.id.isi_tv_sembuh);
        tv_Rawat = tampil.findViewById(R.id.isi_tv_rawat);
        tv_Meninggal = tampil.findViewById(R.id.isi_tv_meninggal);

        //Mengambil Data Dengan Volley
        getData();

        return tampil;
    }

    private void getData(){
        RequestQueue queue = Volley.newRequestQueue(getActivity());

        String url ="https://indonesia-covid-19.mathdro.id/api";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response.toString());


                    tv_Total.setText(jsonObject.getString("jumlahKasus"));
                    tv_Sembuh.setText(jsonObject.getString("sembuh"));
                    tv_Rawat.setText(jsonObject.getString("perawatan"));
                    tv_Meninggal.setText(jsonObject.getString("meninggal"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Error Response", error.toString());
            }
        });

        queue.add(stringRequest);
    }
}
