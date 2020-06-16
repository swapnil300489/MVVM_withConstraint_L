package com.example.viewmodel;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.Retrofit.APIInterface;
import com.example.pojo.Login;
import com.example.pojo.PastPolicy;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KenlyRice_ViewModel extends ViewModel {

    private MutableLiveData<Login> liveLoginData;

    private MutableLiveData<PastPolicy> livePastPolicyData;

    public LiveData<Login> getLogin(APIInterface apiInterface, String email, String pass){

        if (liveLoginData == null){

            liveLoginData = new MutableLiveData<Login>();

            getLoginData(apiInterface, email, pass);

        }

        return liveLoginData;

    }

    private void getLoginData(APIInterface apiInterface, String email, String pass) {

        Call<Login> loginCall = apiInterface.LOGIN_CALL(email, pass);

        loginCall.enqueue(new Callback<Login>() {
            @Override
            public void onResponse(Call<Login> call, Response<Login> response) {

                liveLoginData.setValue(response.body());

            }

            @Override
            public void onFailure(Call<Login> call, Throwable t) {

            }
        });



    }



    public LiveData<PastPolicy> getPolicyList(APIInterface apiInterface, String userId){

        if (livePastPolicyData == null){
            livePastPolicyData = new MutableLiveData<PastPolicy>();

            getPolicyDetails(apiInterface, userId);
        }

        return livePastPolicyData;

    }

    private void getPolicyDetails(APIInterface apiInterface, String userId) {

        Call<PastPolicy> pastPolicyCall = apiInterface.PAST_POLICY_CALL(userId);

        pastPolicyCall.enqueue(new Callback<PastPolicy>() {
            @Override
            public void onResponse(Call<PastPolicy> call, Response<PastPolicy> response) {


                livePastPolicyData.setValue(response.body());

            }

            @Override
            public void onFailure(Call<PastPolicy> call, Throwable t) {

            }
        });

    }


}
