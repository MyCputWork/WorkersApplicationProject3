package com.example.workersapplication;


import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainAdapter extends FirebaseRecyclerAdapter<OrderModel, MainAdapter.myViewHolder> {

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public MainAdapter(@NonNull FirebaseRecyclerOptions<OrderModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, @SuppressLint("RecyclerView") final int position, @NonNull OrderModel model) {
        holder.userEmail.setText(model.getUserEmail());
        holder.product.setText(model.getProduct().get(0));
        if (model.getProduct().size()>1){
            holder.product2.setText(model.getProduct().get(1));
        }



        Glide.with(holder.img.getContext())
                .load(model.getImgUrl())
                .placeholder(R.drawable.profilepic)
                .circleCrop()
                .error(R.drawable.profilepic)
                .into(holder.img);

        holder.btnComplete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(holder.userEmail.getContext());
                builder.setTitle("Is the order completed?");
               builder.setMessage("Completed orders cannot be undone");
                builder.setPositiveButton("Complete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        FirebaseDatabase.getInstance().getReference().child("Orders").child(getRef(position).getKey()).removeValue();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(holder.userEmail.getContext(), "Cancelled", Toast.LENGTH_SHORT).show();

                    }
                });
                builder.show();
            }
        });

    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_item, parent, false);
        return new myViewHolder(view);
    }

    class myViewHolder extends RecyclerView.ViewHolder{
        CircleImageView img;
        TextView userEmail, product, product2;

        Button btnEdit, btnComplete;
        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            img = (CircleImageView) itemView.findViewById(R.id.img1);
            userEmail= (TextView) itemView.findViewById(R.id.nameText);
            product = (TextView) itemView.findViewById(R.id.productText);
            product2 = (TextView) itemView.findViewById(R.id.productText2);
//            product2 = (TextView) itemView.findViewById(R.id.productText2);

            btnComplete = (Button) itemView.findViewById(R.id.btnComplete);
        }
    }


}
