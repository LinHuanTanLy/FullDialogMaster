package com.ly.fulldialog_master;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;

/**
 * @author Ly
 * @date 2017/11/29
 */

public class FragmentFatherActivity extends AppCompatActivity {
    /**
     * def is callback
     */
    private String mType = "1";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_father);
        mType=getIntent().getStringExtra("type");
        getSupportFragmentManager().beginTransaction().replace(R.id.ll_fragment_father, SonFragment.newInstance(mType))
                .commit();
    }

    /**
     * @param activity
     * @param type     1 callback   2 startActivityForResult
     */
    public static void toFragmentFather(Activity activity, String type) {
        Intent intent = new Intent(activity, FragmentFatherActivity.class);
        intent.putExtra("type", type);
        activity.startActivity(intent);
    }
}
