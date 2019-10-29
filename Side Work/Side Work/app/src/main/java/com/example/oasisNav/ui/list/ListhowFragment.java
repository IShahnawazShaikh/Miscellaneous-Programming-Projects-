package com.example.oasisNav.ui.list;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.StringRequest;
import com.example.oasisNav.Items;
import com.example.oasisNav.ItemsAdapter;
import com.example.oasisNav.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ListhowFragment extends Fragment {
    private static String REGISTER_URL = "https://ingratiating-jacks.000webhostapp.com/Oasis/ServiceMan_Recycler.php";
    RecyclerView recyclerView;
    List list;
    RequestQueue requestQueue;
    LinearLayoutManager manager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_list, container, false);
        recyclerView = view.findViewById(R.id.recycler1);
        recyclerView.setHasFixedSize(true);
        manager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(manager);
        list = new ArrayList<>();
        fetchDetails();
        return view;
    }

    private void fetchDetails() {
        Cache cache = new DiskBasedCache(getContext().getCacheDir(), 1024 * 1024 * 5);
        Network network = new BasicNetwork(new HurlStack());
        requestQueue = new RequestQueue(cache, network);
        requestQueue.start();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, REGISTER_URL,
                response -> {
                    try {
                        JSONArray array = new JSONArray(response);
                        for (int i = 0; i < array.length(); i++) {
                            JSONObject detail = array.getJSONObject(i);
                            list.add(new Items(detail.getString("first_name"), detail.getString("last_name"), detail.getString("email"), detail.getString("phone"), detail.getString("id")));
                        }
                        ItemsAdapter adapter = new ItemsAdapter(getContext(), list);
                        recyclerView.setAdapter(adapter);
                        //recyclerView1.setAdapter(adapter);
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(getContext(), "Error" + e.toString(), Toast.LENGTH_SHORT).show();
                    }
                }, error -> Toast.makeText(getContext(), "" + error.toString(), Toast.LENGTH_LONG).show()
        );
        stringRequest.setRetryPolicy(new RetryPolicy() {
            @Override
            public int getCurrentTimeout() {
                return 50000;
            }

            @Override
            public int getCurrentRetryCount() {
                return 50000;
            }

            @Override
            public void retry(VolleyError error) throws VolleyError {
            }
        });
        requestQueue.add(stringRequest);
    }
}

