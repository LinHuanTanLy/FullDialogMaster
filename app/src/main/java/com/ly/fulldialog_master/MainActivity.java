package com.ly.fulldialog_master;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

/**
 * @author Administrator
 */
public class MainActivity extends AppCompatActivity implements GiftDialogInActivity.onGiftItemClickListener {
    private GiftDialogInActivity mGiftDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mGiftDialog = new GiftDialogInActivity();
    }

    /**
     * show the dialog
     *
     * @param view
     */
    public void showDialog(View view) {
        if (mGiftDialog == null) {
            mGiftDialog = new GiftDialogInActivity();
        }
        mGiftDialog.showDialog(this);
    }

    /**
     * show the fragment with callback
     *
     * @param view
     */
    public void toCallBack(View view) {
        FragmentFatherActivity.toFragmentFather(MainActivity.this,"1");
    }

    /**
     * show the fragment with startActivityForResult
     *
     * @param view
     */
    public void toForResult(View view) {
        FragmentFatherActivity.toFragmentFather(MainActivity.this,"2");
    }

    @Override
    public void onGiftSelectedListener(String gift) {
        Toast.makeText(this, gift, Toast.LENGTH_SHORT).show();
    }
}
