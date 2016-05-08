package com.example.administrator.searchpicturetool.view.fragment;

import android.view.ViewGroup;

import com.example.administrator.searchpicturetool.R;
import com.example.administrator.searchpicturetool.model.bean.NetImage;
import com.example.administrator.searchpicturetool.presenter.fragmentPresenter.CollectListPresenter;
import com.example.administrator.searchpicturetool.view.CollectImageListViewHolder;
import com.jude.beam.bijection.RequiresPresenter;
import com.jude.beam.expansion.list.BeamListFragment;
import com.jude.beam.expansion.list.ListConfig;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.utils.JUtils;

/**
 * Created by wenhuaijun on 2015/11/12 0012.
 */
@RequiresPresenter(CollectListPresenter.class)
public class CollectFragment extends BeamListFragment<CollectListPresenter,NetImage>{
    @Override
    protected ListConfig getConfig() {
        return super.getConfig()
                .setRefreshAble(true)
                .setNoMoreAble(false)
                .setLoadmoreAble(false)
                .setErrorAble(true)
                .setContainerProgressAble(false)
                .setContainerErrorAble(true)
                .setContainerErrorRes(R.layout.view_net_error)
                .setContainerProgressRes(R.layout.page_progress)
                .setLoadMoreRes(R.layout.page_loadmore);
    }

    @Override
    public void showError(Throwable e) {
        super.showError(e);
        JUtils.Log(e.getLocalizedMessage());
    }

    @Override
    protected BaseViewHolder getViewHolder(ViewGroup parent, int viewType) {
        return new CollectImageListViewHolder(parent);
    }
}
