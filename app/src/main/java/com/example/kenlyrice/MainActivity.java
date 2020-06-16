package com.example.kenlyrice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.Retrofit.APIClient;
import com.example.Retrofit.APIInterface;
import com.example.pojo.Login;
import com.example.viewmodel.KenlyRice_ViewModel;

public class MainActivity extends AppCompatActivity {

    private EditText id_name_ed_text2, id_name_ed_text3;
    private Button id_loginBtn;
    private String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
    private APIInterface apiInterface;
    private ProgressDialog progressDialog;
    private KenlyRice_ViewModel kenlyRice_viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        id_loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = id_name_ed_text2.getText().toString();
                String pass  = id_name_ed_text3.getText().toString();

                if(email.isEmpty()){
                    id_name_ed_text2.requestFocus();
                    id_name_ed_text2.setError("Enter email");
                }else if(!email.matches(regex)){
                    id_name_ed_text2.requestFocus();
                    id_name_ed_text2.setError("Enter valid email");
                }else if(pass.isEmpty()) {
                    id_name_ed_text3.requestFocus();
                    id_name_ed_text3.setError("Enter password");
                }else {
                    login(email, pass);
                }

            }
        });


    }

    private void login(String email, String pass) {
        showDialog(MainActivity.this);

        kenlyRice_viewModel.getLogin(apiInterface, email, pass).observe(this, new Observer<Login>() {
            @Override
            public void onChanged(Login login) {

                dismissDialog();

                if (login.getMessageCode() == 1){

                    Intent intent = new Intent(MainActivity.this, ListActivity.class);
                    intent.putExtra("userId", login.getData().getUserId());
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);

                    Toast.makeText(getApplicationContext(), login.getMessage(), Toast.LENGTH_LONG).show();

                }else {

                    Toast.makeText(getApplicationContext(), login.getMessage(), Toast.LENGTH_LONG).show();

                }
            }
        });


    }


    private void init() {

        id_name_ed_text2  = findViewById(R.id.id_name_ed_text2);
        id_name_ed_text3  = findViewById(R.id.id_name_ed_text3);

        id_loginBtn = findViewById(R.id.id_loginBtn);
        progressDialog = new ProgressDialog(this);
        apiInterface = APIClient.getClient().create(APIInterface.class);

        kenlyRice_viewModel = ViewModelProviders.of(this).get(KenlyRice_ViewModel.class);

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
