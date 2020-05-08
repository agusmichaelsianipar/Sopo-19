package com.example.sopo_19.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.sopo_19.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class KananFragment extends Fragment {
    private View RsView;
    private RecyclerView myRsList;

    private DatabaseReference RsRef;

    public KananFragment(){
        //Constructor
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        RsView = inflater.inflate(R.layout.fragment_tengah, container, false);

        myRsList = (RecyclerView) RsView.findViewById(R.id.rs_list);
        myRsList.setLayoutManager(new LinearLayoutManager(getContext()));

        RsRef = FirebaseDatabase.getInstance().getReference().child("datars");

        return RsView;
    }

    @Override
    public void onStart() {
        super.onStart();

        FirebaseRecyclerOptions options =
                new FirebaseRecyclerOptions.Builder<DataRs>()
                        .setQuery(RsRef,DataRs.class)
                        .build();

        FirebaseRecyclerAdapter<DataRs, RsViewHolder> adapter
                = new FirebaseRecyclerAdapter<DataRs, RsViewHolder>(options) {
            @Override
            protected void onBindViewHolder(final RsViewHolder rsViewHolder, int i, DataRs dataRS) {
                String Rss = getRef(i).getKey();

                RsRef.child(Rss).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String namaRs = dataSnapshot.child("namars").getValue().toString();
                        long coverAll = Long.parseLong(dataSnapshot.child("coverall").getValue().toString());
                        long nMask = Long.parseLong(dataSnapshot.child("mask").getValue().toString());
                        String nKota = dataSnapshot.child("kota").getValue().toString();

                        rsViewHolder.rsName.setText(namaRs);
                        rsViewHolder.coverAll.setText(String.valueOf(coverAll));
                        rsViewHolder.surMask.setText(String.valueOf(nMask));
                        rsViewHolder.kotaName.setText(nKota);
                        rsViewHolder.lRs.setImageResource(R.drawable.icon_rs);

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }

            @NonNull
            @Override
            public RsViewHolder onCreateViewHolder(ViewGroup viewGroup,int viewType){
                View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_view,viewGroup,false);
                RsViewHolder viewHolder = new RsViewHolder(view);
                return viewHolder;
            }
        };

        myRsList.setAdapter(adapter);
        adapter.startListening();

    }


    public static class RsViewHolder extends RecyclerView.ViewHolder{

        TextView rsName, coverAll, surMask,kotaName;
        ImageView lRs;

        public RsViewHolder(@NonNull View itemView) {
            super(itemView);

            rsName = itemView.findViewById(R.id.nama_cmp);
            coverAll = itemView.findViewById(R.id.isicoverallsuit);
            surMask = itemView.findViewById(R.id.isisurgicalmask);
            kotaName = itemView.findViewById(R.id.kotaa);
            lRs = itemView.findViewById(R.id.icon);
        }
    }

}
