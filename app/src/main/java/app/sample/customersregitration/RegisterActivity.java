package app.sample.customersregitration;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;


public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final EditText firstname = (EditText) findViewById(R.id.firstname);
        final EditText othername = (EditText) findViewById(R.id.othername);
        final EditText address = (EditText) findViewById(R.id.address);
        final EditText customerid = (EditText) findViewById(R.id.customerid);
        final EditText nationalid = (EditText) findViewById(R.id.nationalid);
        final EditText mobile = (EditText) findViewById(R.id.mobile);



        final Button bRegister = (Button) findViewById(R.id.bRegister);

        bRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String fname = firstname.getText().toString();
                final String oname = othername.getText().toString();
                final String adres = address.getText().toString();
                final int cid = Integer.parseInt(customerid.getText().toString());
                final int nid = Integer.parseInt(nationalid.getText().toString());
                final int mob = Integer.parseInt(mobile.getText().toString());




                Response.Listener<String> responseListener = new Response.Listener<String>(){


                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");

                            if(success){

                                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                RegisterActivity.this.startActivity(intent);
                            }
                            else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                                builder.setMessage("Registration Failed")
                                        .setNegativeButton("Retry", null)
                                        .create()
                                         .show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };

                RegisterRequest registerRequest = new RegisterRequest(fname, oname, adres, cid, nid, mob, responseListener);
                RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);
                queue.add(registerRequest);
            }
        });
    }
}
