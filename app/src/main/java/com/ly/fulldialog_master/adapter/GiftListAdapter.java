package com.ly.fulldialog_master.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.ly.fulldialog_master.R;


/**
 * Created by Ly on 2017/4/21.
 * 礼物列表的适配器
 */

public class GiftListAdapter extends RecyclerView.Adapter<GiftListAdapter.GiftListViewHolder> {
    private Context context;

    public GiftListAdapter(Context context) {
        this.context = context;
    }

    @Override
    public GiftListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new GiftListViewHolder(LayoutInflater.from(context).inflate(R.layout.item_dialog_gift_list, parent, false));
    }

    @Override
    public void onBindViewHolder(final GiftListViewHolder holder, final int position) {
        switch (position) {
            case 1:
                holder.ivGiftPic.setImageResource(R.mipmap.live_gift_1);
                break;
            case 2:
                holder.ivGiftPic.setImageResource(R.mipmap.live_gift_2);
                break;
            case 3:
                holder.ivGiftPic.setImageResource(R.mipmap.live_gift_3);
                break;
            case 4:
                holder.ivGiftPic.setImageResource(R.mipmap.live_gift_4);
                break;
            case 5:
                holder.ivGiftPic.setImageResource(R.mipmap.live_gift_5);
                break;
            case 6:
                holder.ivGiftPic.setImageResource(R.mipmap.live_gift_6);
                break;
            case 7:
                holder.ivGiftPic.setImageResource(R.mipmap.live_gift_7);
                break;
            case 8:
                holder.ivGiftPic.setImageResource(R.mipmap.live_gift_8);
                break;
        }


    }


    @Override
    public int getItemCount() {
        return 12;
    }

    public static class GiftListViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivGiftPic;
//        @BindView(R.id.iv_gift_pic)
//        ImageView ivGiftPic;
//        @BindView(R.id.tv_gift_name)
//        TextView tvGiftName;
//        @BindView(R.id.tv_gift_price)
//        TextView tvGiftPrice;

        public GiftListViewHolder(View itemView) {
            super(itemView);
            ivGiftPic = (ImageView) itemView.findViewById(R.id.iv_gift_pic);
//            ButterKnife.bind(this, itemView);
        }
    }


}
