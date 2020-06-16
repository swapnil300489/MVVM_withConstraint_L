package com.example.kenlyrice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.Retrofit.APIClient;
import com.example.Retrofit.APIInterface;
import com.example.adapter.PolicyAdapter;
import com.example.pojo.PastPolicy;
import com.example.viewmodel.KenlyRice_ViewModel;

import java.util.List;

public class ListActivity extends AppCompatActivity {

    private RecyclerView id_RCList;
    private KenlyRice_ViewModel kenlyRiceViewModel;
    private APIInterface apiInterface;
    private ProgressDialog progressDialog;
    private PolicyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        init();


    }

    private void init() {

        id_RCList = findViewById(R.id.id_RCList);
        id_RCList.setLayoutManager(new LinearLayoutManager(this));
        apiInterface = APIClient.getClient().create(APIInterface.class);
        progressDialog = new ProgressDialog(this);

        String userId = getIntent().getStringExtra("userId");

        kenlyRiceViewModel = ViewModelProviders.of(this).get(KenlyRice_ViewModel.class);
        Log.e("PolicyList_userId==>", userId);
        getPolicyList(userId);

    }

    private void getPolicyList(String userId) {
            showDialog(ListActivity.this);
        kenlyRiceViewModel.getPolicyList(apiInterface, userId).observe(this, new Observer<PastPolicy>() {
            @Override
            public void onChanged(PastPolicy pastPolicy) {

                dismissDialog();

                if (pastPolicy.getMessageCode() == 1){


                    List<PastPolicy.Datum> data= pastPolicy.getData();

                    Log.e("PolicyList==>", data.toString());

                    adapter = new PolicyAdapter(ListActivity.this, pastPolicy.getData());
                    id_RCList.setAdapter(adapter);
                }else {
                    id_RCList.setVisibility(View.GONE);
                }

            }
        });



    }

    public void showDialog(Context context){

        progressDialog = new ProgressDialog(context);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Please wait....");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();
    }

    public void dismissDialog(){

        progressDialog.dismiss();
    }
}
