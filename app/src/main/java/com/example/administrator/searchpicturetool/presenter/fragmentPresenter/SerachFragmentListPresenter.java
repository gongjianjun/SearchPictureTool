package com.example.administrator.searchpicturetool.presenter.fragmentPresenter;

import android.content.Intent;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.example.administrator.searchpicturetool.model.GetImagelistModel;
import com.example.administrator.searchpicturetool.model.bean.NetImage;
import com.example.administrator.searchpicturetool.view.fragment.SearchFragment;
import com.example.administrator.searchpicturetool.view.activity.ShowLargeImgActivity;
import com.jude.beam.expansion.list.BeamListFragmentPresenter;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.utils.JUtils;
import java.util.ArrayList;
import java.util.Arrays;

import rx.Observer;
import rx.Subscriber;

/**
 * Created by wenhuaijun on 2015/11/3 0003.
 */
public class SerachFragmentListPresenter extends BeamListFragmentPresenter<SearchFragment,NetImage> implements RecyclerArrayAdapter.OnItemClickListener{

    private int page =0;
    private String tab;
    private ArrayList<NetImage> netImages;
    @Override
    protected void onCreateView(SearchFragment view) {
        super.onCreateView(view);
        view.getListView().getRecyclerView().setHasFixedSize(false);
        view.getListView().setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        tab =view.getArguments().getString("search");
        netImages = new ArrayList<NetImage>();
        onRefresh();

    }

    @Override
    public void onRefresh() {
        super.onRefresh();
        page=0;
        GetImagelistModel.getImageList(tab, page).subscribe(new Observer<NetImage[]>() {
            @Override
            public void onCompleted() {
                JUtils.Log("onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                JUtils.Log("onError");
                if (getAdapter().getCount() == 0) {
                    getRefreshSubscriber().onError(e);
                }
            }

            @Override
            public void onNext(NetImage[] imgs) {
                netImages.clear();
                netImages.addAll(Arrays.asList(imgs));
                getRefreshSubscriber().onNext(netImages);
                page+=imgs.length;
                getAdapter().setOnItemClickListener(SerachFragmentListPresenter.this);
            }
        });
    }

    @Override
    public void onLoadMore() {
        super.onLoadMore();
        GetImagelistModel.getImageList(tab,page).subscribe(new Subscriber<NetImage[]>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
                getMoreSubscriber().onError(e);
            }

            @Override
            public void onNext(NetImage[] imgs) {
                netImages.addAll(Arrays.asList(imgs));
                getMoreSubscriber().onNext(Arrays.asList(imgs));
                page+=imgs.length;
            }
        });
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent();
        intent.putExtra("position", position);
        intent.putExtra("netImages", netImages);
        intent.setClass(getView().getContext(), ShowLargeImgActivity.class);
        getView().getActivity().startActivityForResult(intent,100);
    }
}
