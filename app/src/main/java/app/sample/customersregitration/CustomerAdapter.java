package app.sample.customersregitration;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class CustomerAdapter extends RecyclerView.Adapter<CustomerAdapter.MyViewHolder> {

    private List<Customer> customersList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, user_id, nationid;

        public MyViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.name);
            user_id = (TextView) view.findViewById(R.id.user_id);
            nationid = (TextView) view.findViewById(R.id.nationid);
        }
    }

    public CustomerAdapter(List<Customer> customersList) {
        this.customersList = customersList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.customer_list_row, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Customer customer = customersList.get(position);
        holder.name.setText(customer.getFirstname() + " " +  customer.getOthername());
        holder.user_id.setText(customer.getUser_id());
        holder.nationid.setText(customer.getNationid());
    }

    @Override
    public int getItemCount() {
        return customersList.size();
    }
}
