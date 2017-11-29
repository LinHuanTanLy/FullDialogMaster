package com.ly.fulldialog_master;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import static com.ly.fulldialog_master.GiftDialogInFragment.showDialog;

/**
 * @author Ly
 * @date 2017/11/29
 */

public class SonFragment extends Fragment implements GiftDialogInFragment.onGiftItemClickListener {
    private GiftDialogInFragment mGiftDialog;


    public static SonFragment newInstance(String type) {
        Bundle args = new Bundle();
        args.putString("type", type);
        SonFragment fragment = new SonFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return LayoutInflater.from(getActivity()).inflate(R.layout.fragment_son, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mGiftDialog = new GiftDialogInFragment();
        final String type = getArguments().getString("type");
        view.findViewById(R.id.bt_fragment_show).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mGiftDialog = GiftDialogInFragment.showDialog(SonFragment.this, type);
            }
        });
    }


    @Override
    public void onGiftSelectedListener(String gift) {
        Toast.makeText(getActivity(), "callback---" + gift, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            Toast.makeText(getActivity(), "onActivityResult---" + data.getStringExtra("money"), Toast.LENGTH_SHORT).show();
        }
    }
}

