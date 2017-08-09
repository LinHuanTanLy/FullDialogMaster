package com.ly.fulldialog_master;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ly.fulldialog_master.adapter.GiftListAdapter;
import com.ly.fulldialog_master.utils.DeviceUtils;
import com.ly.fulldialog_master.utils.HorizontalPageLayoutManager;
import com.ly.fulldialog_master.utils.PagingScrollHelper;

import static com.ly.fulldialog_master.R.mipmap.live_gift_noin_gray;


/**
 * Created by Ly on 2017/4/21.
 * 送礼的dialog
 */

public class GiftDialog extends DialogFragment implements PagingScrollHelper.onPageChangeListener {
    public GiftDialog() {
    }

    public static GiftDialog newInstance() {
        Bundle args = new Bundle();
        GiftDialog fragment = new GiftDialog();
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * 礼物列表
     */
    private RecyclerView recyclerViewText;
    /**
     * viewpager的指示器
     */
    private LinearLayout llGiftIndicator;
    /**
     * 账户余额
     */
    private TextView tvGiftBalance;
    /**
     * 发送
     */
    private CardView cvGiftSend;

    protected Dialog dialog;
    /**
     * 加载的适配器
     */
    private GiftListAdapter giftListAdapter;

    /**
     * 滑动监听控制器
     */
    PagingScrollHelper scrollHelper = new PagingScrollHelper();
    /**
     * 配置常量  使用2行4列的方式进行布局
     */
    private static final int ROWS = 2, COLUMNS = 4;
    /**
     * 就是一个标志位，我想写“The most handsome LingYu”
     * 但是想想还是不要那么骚包好了
     */
    private static final String TAG = "BottomDialogFragment";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return LayoutInflater.from(getActivity()).inflate(R.layout.gift_anim_dialog_layout, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.dialog);
        View view = View.inflate(getActivity(), R.layout.gift_anim_dialog_layout, null);
        builder.setView(view);
        dialog = builder.create();
        dialog.setCanceledOnTouchOutside(true);
        // 设置宽度为屏宽、靠近屏幕底部。
        Window window = dialog.getWindow();
        window.getDecorView().setPadding(0, 0, 0, 0);
        WindowManager.LayoutParams wlp = window.getAttributes();
        wlp.gravity = Gravity.BOTTOM;
        //设置没有效果
        wlp.width = WindowManager.LayoutParams.MATCH_PARENT;
        // TODO: 2017/8/10   要全屏的话
//        wlp.height = WindowManager.LayoutParams.MATCH_PARENT;
        window.setAttributes(wlp);
        initView(view);
        return dialog;
    }

    private void initView(View view) {
        recyclerViewText = (RecyclerView) view.findViewById(R.id.rlv_gift_list);
        llGiftIndicator = (LinearLayout) view.findViewById(R.id.ll_gift_indicator);
        recyclerViewText.setLayoutManager(new HorizontalPageLayoutManager(ROWS, COLUMNS));
        giftListAdapter = new GiftListAdapter(getActivity());
        recyclerViewText.setAdapter(giftListAdapter);
        scrollHelper.setUpRecycleView(recyclerViewText);
        scrollHelper.setOnPageChangeListener(this);
        for (int i = 0; i < 2; i++) {
            ImageView imageView = new ImageView(getActivity());
            LinearLayout.LayoutParams layoutParams =
                    new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(DeviceUtils.px2dip(getActivity(), 12),
                    DeviceUtils.px2dip(getActivity(), 2),
                    DeviceUtils.px2dip(getActivity(), 12),
                    DeviceUtils.px2dip(getActivity(), 2));
            imageView.setLayoutParams(layoutParams);
            if (i == 0) {
                imageView.setImageResource(R.mipmap.live_gift_in_red);
            } else {
                imageView.setImageResource(live_gift_noin_gray);
            }
            llGiftIndicator.addView(imageView);
        }
    }

    /**
     * 更新状态
     *
     * @param position
     */
    private void updateIndicator(int position) {
        if (position < 0 || position > llGiftIndicator.getChildCount()) {
            return;
        } else {
            for (int i = 0; i < llGiftIndicator.getChildCount(); i++) {
                ((ImageView) llGiftIndicator.getChildAt(i)).setImageResource(live_gift_noin_gray);
            }
            ((ImageView) llGiftIndicator.getChildAt(position)).setImageResource(R.mipmap.live_gift_in_red);
        }
    }

    //防止重复弹出
    public static GiftDialog showDialog(AppCompatActivity appCompatActivity) {
        FragmentManager fragmentManager = appCompatActivity.getSupportFragmentManager();
        GiftDialog bottomDialogFragment =
                (GiftDialog) fragmentManager.findFragmentByTag(TAG);
        if (null == bottomDialogFragment) {
            bottomDialogFragment = newInstance();
        }

        if (!appCompatActivity.isFinishing()
                && null != bottomDialogFragment
                && !bottomDialogFragment.isAdded()) {
            fragmentManager.beginTransaction()
                    .add(bottomDialogFragment, TAG)
                    .commitAllowingStateLoss();
        }
        return bottomDialogFragment;
    }

    @Override
    public void onPageChange(int index) {
        updateIndicator(index);
    }
}
