package com.sust.bup_project.loan;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.sust.bup_project.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShowLoanAdsFragment extends Fragment {

    OrganizationOffersAdapter.OnItemClickListener listener = new OrganizationOffersAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(OrganizationOffers offer) {
            Intent i = new Intent(getContext(),ShowPost.class);
            i.putExtra("offer",offer);
            startActivity(i);
        }
    };


    public ShowLoanAdsFragment() {
        // Required empty public constructor
    }

    RecyclerView showPostRecyclerView;
    OrganizationOffersAdapter adapter;

    private ArrayList<OrganizationOffers> offersArrayList;

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_show_loan_ads, container, false);

        showPostRecyclerView = view.findViewById(R.id.showLoanAds);
        showPostRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        offersArrayList = new ArrayList<>();
        adapter = new OrganizationOffersAdapter(listener,offersArrayList,getContext());
        showPostRecyclerView.setAdapter(adapter);

        DatabaseReference database = FirebaseDatabase.getInstance().getReference("user/organization");

        database.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                for(DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    if(snapshot.getKey().equals("loan")) {
                        for(DataSnapshot childSnapshot : snapshot.getChildren()) {
                            OrganizationOffers offer = childSnapshot.getValue(OrganizationOffers.class);
                            offersArrayList.add(offer);
                        }
                    }
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return view;
    }

}
