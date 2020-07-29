package com.example.enterpriseapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class MenuDetails extends AppCompatActivity {

    private static RecyclerView.Adapter adapter;
    protected RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;
    private static ArrayList<DataModel> data;
    static View.OnClickListener myOnclickListener;
    private FirebaseFirestore firestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_details);
        myOnclickListener = new MyOnClickListener(this);
        firestore= FirebaseFirestore.getInstance();

        recyclerView = (RecyclerView)findViewById(R.id.menu_details_recyclerview);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        data = new ArrayList<DataModel>();

        firestore.collection("MenuBreakfast")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        for(DocumentSnapshot q:task.getResult()){
                            DataModel M=new DataModel(
                                    q.getString("Breakfastitemlist"),
                                    q.getString("VCompanyName"),
                                    q.getString("EPrice")
                                   // String.valueOf(q.getDouble("EPrice"))
                                    );
                            data.add(M);
                        }
                    }
                });

        firestore.collection("MenuLunch")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        for(DocumentSnapshot q:task.getResult()){
                            DataModel M=new DataModel(
                                    q.getString("LunchItemList"),
                                    q.getString("VCompanyName"),
                                    q.getString("EPrice")
                            );
                            data.add(M);
                        }
                    }
                });


        firestore.collection("MenuSnacks")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        for(DocumentSnapshot q:task.getResult()){
                            DataModel M=new DataModel(
                                    q.getString("SnacksitemList"),
                                    q.getString("VCompanyName"),
                                    q.getString("EPrice")
                            );
                            data.add(M);
                        }
                    }
                });


        firestore.collection("MenuDinner")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        for(DocumentSnapshot q:task.getResult()){
                            DataModel M=new DataModel(
                                    q.getString("DinnerItemList"),
                                    q.getString("VCompanyName"),
                                    q.getString("EPrice")
                            );
                            data.add(M);
                        }
                    }
                });










        adapter = new MenuAdapter(data);
        recyclerView.setAdapter(adapter);

    }


    @Override
    protected void onStart() {
        super.onStart();

        firestore.collection("MenuBreakfast").addSnapshotListener(this, new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if(error!=null){
                    return;
                }

                for(QueryDocumentSnapshot q:value) {
                    DataModel M=new DataModel(
                            q.getString("Breakfastitemlist"),
                            q.getString("VCompanyName"),
                            q.getString("EPrice")
                    );
                    data.add(M);

                    adapter = new MenuAdapter(data);
                    recyclerView.setAdapter(adapter);
                }
            }
        });

        firestore.collection("MenuLunch").addSnapshotListener(this, new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if(error!=null){
                    return;
                }

                for(QueryDocumentSnapshot q:value) {
                    DataModel M=new DataModel(
                            q.getString("LunchItemList"),
                            q.getString("VCompanyName"),
                            q.getString("EPrice")
                    );
                    data.add(M);

                    adapter = new MenuAdapter(data);
                    recyclerView.setAdapter(adapter);
                }
            }
        });

        firestore.collection("MenuSnacks").addSnapshotListener(this, new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if(error!=null){
                    return;
                }

                for(QueryDocumentSnapshot q:value) {
                    DataModel M=new DataModel(
                            q.getString("SnacksitemList"),
                            q.getString("VCompanyName"),
                            q.getString("EPrice")
                    );
                    data.add(M);

                    adapter = new MenuAdapter(data);
                    recyclerView.setAdapter(adapter);
                }
            }
        });

        firestore.collection("MenuDinner").addSnapshotListener(this, new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if(error!=null){
                    return;
                }

                for(QueryDocumentSnapshot q:value) {
                    DataModel M=new DataModel(
                            q.getString("DinnerItemList"),
                            q.getString("VCompanyName"),
                            q.getString("EPrice")
                    );
                    data.add(M);

                    adapter = new MenuAdapter(data);
                    recyclerView.setAdapter(adapter);
                }
            }
        });


    }

    static class MyOnClickListener implements View.OnClickListener{
        private final Context context;
        MyOnClickListener(Context context){
            this.context = context;
        }
        @Override
        public void onClick(View view) {
            Toast.makeText(context, "item Clicked!!", Toast.LENGTH_SHORT).show();
        }
    }

}