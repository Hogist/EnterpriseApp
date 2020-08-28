package com.example.enterpriseapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class OrderNow extends AppCompatActivity {
    public static final String TAG = "TAG";
    Button submit , viewOrderList;
    TextView BMenu , LMenu , SMenu , DMenu ;
    EditText BQuantity , LQuantity , SQuantity , DQuantity ;
    FirebaseFirestore orderStore;
    Long OrderID ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_now);

        viewOrderList = findViewById(R.id.order_now_vieworderlist);
        submit = findViewById(R.id.submitOrder);
        BMenu = findViewById(R.id.bmenu);
        LMenu = findViewById(R.id.lmenu);
        SMenu = findViewById(R.id.smenu);
        DMenu = findViewById(R.id.dmenu);
        BQuantity = findViewById(R.id.bfquantity);
        LQuantity = findViewById(R.id.lquantity);
        SQuantity = findViewById(R.id.squantity);
        DQuantity = findViewById(R.id.dquantity);
        orderStore = FirebaseFirestore.getInstance();

        

        DocumentReference orderIDRef = orderStore.collection("OrderID").document("oID");
        orderIDRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot documentO = task.getResult();
                    assert documentO != null;
                    if (documentO.exists()) {
                        OrderID = documentO.getLong("order_counter");
                        Log.v("TAG", "order_counter: " + documentO.getData());
                    } else {
                        Log.v("TAG", "No such document");
                    }
                } else {
                    Log.v("TAG", "get failed with ", task.getException());
                }
            }
        });
        viewOrderList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OrderNow.this, OrderList.class);
                startActivity(intent);
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String breakfastQuantity = BQuantity.getText().toString().trim();
                final String lunchQuantity = LQuantity.getText().toString().trim();
                final String snacksQuantity = SQuantity.getText().toString().trim();
                final String dinnerQuantity = DQuantity.getText().toString().trim();
                final String bMenu = BMenu.getText().toString();
                final String lMenu = LMenu.getText().toString();
                final String sMenu = SMenu.getText().toString();
                final String dMenu = DMenu.getText().toString();
                if(TextUtils.isEmpty(breakfastQuantity)){
                    BQuantity.setError("BreakFast QUANTITY is Required");
                    return;
                }
                if(TextUtils.isEmpty(lunchQuantity)){
                    LQuantity.setError("Lunch QUANTITY is Required");
                    return;
                }
                if(TextUtils.isEmpty(snacksQuantity)){
                    SQuantity.setError("Snacks QUANTITY is Required");
                    return;
                }
                if(TextUtils.isEmpty(dinnerQuantity)){
                    DQuantity.setError("Dinner Menu is Required");
                    return;
                }
                DocumentReference orderIDRef = orderStore.collection("OrderID").document("oID");
                orderIDRef.update("order_counter", FieldValue.increment(1));
                OrderID++;
                String orderID = OrderID.toString();
                DocumentReference orderDetails = orderStore.collection("OrderDetails").document();

                Map<String,Object> orderEntry = new HashMap<>();
                orderEntry.put("Created At", FieldValue.serverTimestamp());
                orderEntry.put("BreakItemList",bMenu);
                orderEntry.put("LunchItemList",lMenu);
                orderEntry.put("SnacksItemList",sMenu);
                orderEntry.put("DinnerItemList",dMenu);
                orderEntry.put("BreakfastQuantity",breakfastQuantity);
                orderEntry.put("LunchQuantity",lunchQuantity);
                orderEntry.put("SnacksQuantity",snacksQuantity);
                orderEntry.put("DinnerQuantity",dinnerQuantity);

                orderDetails.set(orderEntry).addOnSuccessListener(new OnSuccessListener<Void>(){
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d("LOG","onSuccess: order is created ");
                        Toast.makeText(OrderNow.this, "order created ", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("LOG", "onFailure:" + e.toString());
                        Toast.makeText(OrderNow.this, "problem in order creation ", Toast.LENGTH_SHORT).show();
                    }

                });


        DocumentReference covidCreate = orderStore.collection("Covid19hygieniccheck").document();
        Map<String,Object> covidEntry = new HashMap<>();
        covidEntry.put("CheckListCreatedAt", FieldValue.serverTimestamp());
        covidEntry.put("Contactless Delivery",false);
        covidEntry.put("Rider's Temperature Check",false);
        covidEntry.put("Sanitize Employees",false);
        covidEntry.put("Sanitize Kitchen",false);
        covidEntry.put("Social Distancing",false);
        covidEntry.put("Sterlize Raw Material",false);
        covidEntry.put("Temperature Check",false);
        covidEntry.put("Wear Mask",false);
        covidEntry.put("OrderID",orderID);
        covidCreate.set(covidEntry).addOnSuccessListener(new OnSuccessListener<Void>(){
            @Override
            public void onSuccess(Void aVoid) {
                Log.d("LOG","onSuccess: order is created ");
                Toast.makeText(OrderNow.this, "covid check list created ", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("LOG", "onFailure:" + e.toString());
                Toast.makeText(OrderNow.this, "problem in covid check list creation ", Toast.LENGTH_SHORT).show();
            }
        });
    }
});
        }
        }