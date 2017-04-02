package com.example.yhdj.ad0420;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private MyView mMyView;
    private Button btnColor;
    private PopupMenu mPopupMenu;
    private Menu mMenu;
    private Button btnEraser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();

    }

    private void initViews() {
        mMyView = (MyView) findViewById(R.id.myView);
        btnEraser = (Button) findViewById(R.id.btnEraser);
        btnEraser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMyView.eraser();
            }
        });
        btnColor = (Button) findViewById(R.id.btnColor);
        mPopupMenu = new PopupMenu(this, btnColor);
        mMenu = mPopupMenu.getMenu();
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.mymenu, mMenu);
        mPopupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.red:
                        mMyView.changeColor(Constant.RED);
                        break;
                    case R.id.blue:
                        mMyView.changeColor(Constant.BLUE);
                        break;
                    case R.id.green:
                        mMyView.changeColor(Constant.GREEN);
                        break;
                    case R.id.black:
                        mMyView.changeColor(Constant.BLACK);
                        break;
                }
                return true;
            }
        });
        btnColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPopupMenu.show();
            }
        });
    }
}
