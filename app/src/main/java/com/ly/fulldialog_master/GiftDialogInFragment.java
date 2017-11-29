package com.ly.fulldialog_master;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
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

import static com.ly.fulldialog_master.R.id.tv_gift_send;
import static com.ly.fulldialog_master.R.mipmap.live_gift_noin_gray;


/**
 * @author Ly
 * @date 2017/4/21
 * 送礼的dialog
 */

public class GiftDialogInFragment extends DialogFragment implements PagingScrollHelper.onPageChangeListener {
    public GiftDialogInFragment() {
    }

    public static GiftDialogInFragment newInstance(String type) {
        Bundle args = new Bundle();
        args.putString("type", type);
        GiftDialogInFragment fragment = new GiftDialogInFragment();
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * 礼物列表
     */
    private RecyclerView mRlvGifList;
    /**
     * viewpager的指示器
     */
    private LinearLayout mLlGiftIndicator;


    protected Dialog dialog;

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
        final String type = getArguments().getString("type");
        final onGiftItemClickListener onGiftItemClickListener = (GiftDialogInFragment.onGiftItemClickListener) getTargetFragment();
        mRlvGifList = view.findViewById(R.id.rlv_gift_list);
        mLlGiftIndicator = view.findViewById(R.id.ll_gift_indicator);
        TextView tvGiftSend = view.findViewById(tv_gift_send);
        mRlvGifList.setLayoutManager(new HorizontalPageLayoutManager(ROWS, COLUMNS));
        GiftListAdapter mGiftListAdapter = new GiftListAdapter(getActivity());
        mRlvGifList.setAdapter(mGiftListAdapter);
        scrollHelper.setUpRecycleView(mRlvGifList);
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
            mLlGiftIndicator.addView(imageView);
            tvGiftSend.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (type.equals("2")) {
                        // startActivityForResult config
                        Intent intent = new Intent();
                        intent.putExtra("money", "礼物的id");
                        //获得目标Fragment,并将数据通过onActivityResult放入到intent中进行传值
                        getTargetFragment().onActivityResult(1, Activity.RESULT_OK, intent);
                        // end of startActivityForResult config
                    } else if (type.equals("1")) {
                        // callback config
                        if (onGiftItemClickListener != null) {
                            onGiftItemClickListener.onGiftSelectedListener("礼物的id");
                        }
                    }
                    // end of callback config
                }
            });
        }
    }

    /**
     * 更新状态
     *
     * @param position
     */
    private void updateIndicator(int position) {
        if (position < 0 || position > mLlGiftIndicator.getChildCount()) {
            return;
        } else {
            for (int i = 0; i < mLlGiftIndicator.getChildCount(); i++) {
                ((ImageView) mLlGiftIndicator.getChildAt(i)).setImageResource(live_gift_noin_gray);
            }
            ((ImageView) mLlGiftIndicator.getChildAt(position)).setImageResource(R.mipmap.live_gift_in_red);
        }
    }

    /**
     * 防止重复弹出
     *
     * @param fragment
     * @return
     */
    public static GiftDialogInFragment showDialog(Fragment fragment, String type) {
        FragmentManager fragmentManager = fragment.getActivity().getSupportFragmentManager();
        GiftDialogInFragment bottomDialogFragment =
                (GiftDialogInFragment) fragmentManager.findFragmentByTag(TAG);
        if (null == bottomDialogFragment) {
            bottomDialogFragment = newInstance(type);
        }
        if (!fragment.getActivity().isFinishing()
                && null != bottomDialogFragment
                && !bottomDialogFragment.isAdded()) {
            bottomDialogFragment.setTargetFragment(fragment, 1);
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


    /**
     * item点击的interface
     */
    public interface onGiftItemClickListener {
        /**
         * item点击的interface
         *
         * @param gift
         */
        void onGiftSelectedListener(String gift);
    }
}
