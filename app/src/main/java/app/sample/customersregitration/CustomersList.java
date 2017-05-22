package app.sample.customersregitration;

import android.content.Intent;
import android.graphics.Movie;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static app.sample.customersregitration.R.id.progressBar;

/**
 * Created by Geoffrey Koros on 5/21/2017.
 */
public class CustomersList extends AppCompatActivity {

    private static final String TAG = "CustomersList";
    private static final String CUSTOMERS_LIST_URL = "https://komaxwel.000webhostapp.com/retrieval_test.php";
    RecyclerView mRecyclerView;

    private List<Customer> customersList = new ArrayList<>();
    private CustomerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customerslist);

        mRecyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        mAdapter = new CustomerAdapter(customersList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mAdapter);

    }

    @Override
    protected void onResume() {
        super.onResume();
        showProgress();
        getCustomersList(CUSTOMERS_LIST_URL);
    }

    /**
     * Method to make json array request where response starts with [
     * */
    private void getCustomersList(String url) {

        JsonArrayRequest req = new JsonArrayRequest(url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG, response.toString());
                        List<Customer> list = new ArrayList<>();
                        try {
                            // Parsing json array response
                            // loop through each json object
                            for (int i = 0; i < response.length(); i++) {

                                JSONObject customer = (JSONObject) response.get(i);
                                String user_id = customer.getString("user_id");
                                String firstname = customer.getString("firstname");
                                String othername = customer.getString("othername");
                                String address = customer.getString("address");
                                String customid = customer.getString("customid");
                                String nationid = customer.getString("nationid");
                                String mobile = customer.getString("mobile");

                                Customer c = new Customer(user_id, firstname, othername, address, customid, nationid, mobile);
                                list.add(c);
                            }

                            customersList.clear();
                            customersList.addAll(list);
                            //hideProgress();
                            mAdapter.notifyDataSetChanged();

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(), "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        // Adding request to request queue
        RequestQueue queue = Volley.newRequestQueue(CustomersList.this);
        queue.add(req);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_add) {
            Intent intent = new Intent(CustomersList.this, RegisterActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void showProgress(){
        //mProgressBar.setVisibility(View.VISIBLE);
    }

    private void hideProgress(){
        //mProgressBar.setVisibility(View.GONE);
    }
}
