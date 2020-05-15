package com.example.sopo_19.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sopo_19.InsertApd;
import com.example.sopo_19.MainActivity;
import com.example.sopo_19.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class TengahFragment extends Fragment {
    private View ApdView;
    private RecyclerView myApdList;
    FloatingActionButton tblSimpan;
    private DatabaseReference ApdRef;

    public TengahFragment(){
        //Constructor
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        ApdView =  inflater.inflate(R.layout.fragment_kanan, container, false);

        myApdList = (RecyclerView) ApdView.findViewById(R.id.apd_list);
        myApdList.setLayoutManager(new LinearLayoutManager(getContext()));

        ApdRef = FirebaseDatabase.getInstance().getReference().child("dataapd");
        tblSimpan = ApdView.findViewById(R.id.fab_kesimpan);
        tblSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), InsertApd.class));
            }
        });
        return ApdView;
    }

    @Override
    public void onStart() {
        super.onStart();

        FirebaseRecyclerOptions options =
                new FirebaseRecyclerOptions.Builder<DataApd>()
                        .setQuery(ApdRef,DataApd.class)
                        .build();

        FirebaseRecyclerAdapter<DataApd,ApdViewHolder> adapter
                = new FirebaseRecyclerAdapter<DataApd, ApdViewHolder>(options) {
            @Override
            protected void onBindViewHolder(final ApdViewHolder apdViewHolder, int i,DataApd dataApd) {
                String Apds = getRef(i).getKey();

                ApdRef.child(Apds).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String namaCmp = dataSnapshot.child("namacmp").getValue().toString();
                        long coverAll = Long.parseLong(dataSnapshot.child("coverall").getValue().toString());
                        long nMask = Long.parseLong(dataSnapshot.child("mask").getValue().toString());
                        String nKota = dataSnapshot.child("kota").getValue().toString();
                        String nKontak = dataSnapshot.child("kontak").getValue().toString();

                        apdViewHolder.compName.setText(namaCmp);
                        apdViewHolder.coverAll.setText(String.valueOf(coverAll));
                        apdViewHolder.surMask.setText(String.valueOf(nMask));
                        apdViewHolder.kotaName.setText(nKota);
                        apdViewHolder.kontakName.setText(nKontak);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }

            @NonNull
            @Override
            public ApdViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
                View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_view, viewGroup,false);
                ApdViewHolder viewHolder = new ApdViewHolder(view);
                return viewHolder;
            }
        };

        myApdList.setAdapter(adapter);
        adapter.startListening();

    }

    public static class ApdViewHolder extends RecyclerView.ViewHolder{

        TextView compName, coverAll, surMask,kotaName,kontakName;

        public ApdViewHolder(@NonNull View itemView) {
            super(itemView);

            compName = itemView.findViewById(R.id.nama_cmp);
            coverAll = itemView.findViewById(R.id.isicoverallsuit);
            surMask = itemView.findViewById(R.id.isisurgicalmask);
            kotaName = itemView.findViewById(R.id.kotaa);
            kontakName = itemView.findViewById(R.id.kontak);
        }
    }

}
