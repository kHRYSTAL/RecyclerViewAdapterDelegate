package me.khrystal.rvd;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

import me.khrystal.deglgationlib.base.DelegateAdapter;
import me.khrystal.deglgationlib.base.DelegateViewHolder;
import me.khrystal.deglgationlib.base.Delegation;
import me.khrystal.rvd.model.Blue;
import me.khrystal.rvd.model.Color;
import me.khrystal.rvd.model.Red;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Color> datas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        initView();
    }

    private void initData() {
       datas = new ArrayList<>();
//        for (int i = 1; i < 101; i++) {
//            Color color = new Color();
//            if (i % 2 == 0) color.delegationViewType = 2;
//            else color.delegationViewType = 1;
//            datas.add(color);
//
//        }
        for (int i = 1; i < 101 ; i++) {
            if (i % 2 == 0)datas.add(new Red());
            else datas.add(new Blue());
        }
        Collections.shuffle(datas);
    }

    private void initView() {
        DelegateAdapter<Color> adapter = new DelegateAdapter<>(this,datas);
        adapter.addDelegation(new Delegation<Color>(R.layout.red_item,1) {


            @Override
            protected void handle(final DelegateViewHolder holder, final Color data, final int position) {
                holder.getTextView(R.id.item_text).setText(data.getClass().getSimpleName()+position);
                holder.getTextView(R.id.item_button).setText(data.getClass().getSimpleName()+position);
                holder.getButton(R.id.item_button).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        Toast.makeText(MainActivity.this,data.getClass().getSimpleName()+position,Toast.LENGTH_SHORT).show();
                        holder.showToast(data.getClass().getSimpleName()+position,Toast.LENGTH_SHORT);
                    }
                });
            }
        });

        adapter.addDelegation(new Delegation<Color>(R.layout.blue_item,2) {


            @Override
            protected void handle(final DelegateViewHolder holder, final Color data, final int position) {
                holder.getTextView(R.id.item_text).setText(data.getClass().getSimpleName() + position);
                holder.getTextView(R.id.item_button).setText(data.getClass().getSimpleName() + position);
            }
        });

        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(false);
        recyclerView.setAdapter(adapter);
    }
}
