package com.tellh.library;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

abstract class RecyclerViewSensitiveScrollDetector extends RecyclerView.OnScrollListener {
    private int mFabOffset = 0;
    private int[] mFabHeight;
    private boolean mControlsVisible = true;
    private static final float HIDE_THRESHOLD = 10;
    private static final float SHOW_THRESHOLD = 70;
    private int mScrollThreshold;

    abstract void onScrollUp();

    abstract void onScrollDown();

    abstract void onMoved(int dis);

    public RecyclerViewSensitiveScrollDetector(int[] mFabHeight) {
        this.mFabHeight = mFabHeight;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {

        clipToolbarOffset();
        onMoved(mFabOffset);

        if ((mFabOffset < mFabHeight[0] && dy > 0) || (mFabOffset > 0 && dy < 0)) {
            mFabOffset += dy;
        }

    }

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);

        if (newState == RecyclerView.SCROLL_STATE_IDLE) {
            if (mControlsVisible) {
                if (mFabOffset > mScrollThreshold) {
                    setInvisible();
                } else {
                    setVisible();
                }
            } else {
                if ((mFabHeight[0] - mFabOffset) > mScrollThreshold) {
                    setVisible();
                } else {
                    if (isReachTop(recyclerView, newState) || isReachBottom(recyclerView, newState)) {
                        setVisible();
                    } else setInvisible();
                }
            }
        }
    }

    private void setVisible() {
        mFabOffset = 0;
        mControlsVisible = true;
        onScrollUp();
    }


    private void setInvisible() {
        mFabOffset = mFabHeight[0];
        mControlsVisible = false;
        onScrollDown();
    }

    public boolean isReachTop(RecyclerView recyclerView, int newState) {
        if (newState == RecyclerView.SCROLL_STATE_IDLE) {
            return ((LinearLayoutManager) recyclerView.getLayoutManager()).findFirstCompletelyVisibleItemPosition()
                    == 0;
        }
        return false;
    }

    public boolean isReachBottom(RecyclerView recyclerView, int newState) {
        if (newState == RecyclerView.SCROLL_STATE_IDLE) {
            return ((LinearLayoutManager) recyclerView.getLayoutManager()).findLastCompletelyVisibleItemPosition()
                    == recyclerView.getAdapter().getItemCount() - 1;
        }
        return false;
    }

    private void clipToolbarOffset() {
        if (mFabOffset > mFabHeight[0]) {
            mFabOffset = mFabHeight[0];
        } else if (mFabOffset < 0) {
            mFabOffset = 0;
        }
    }

    public void setScrollThreshold(int scrollThreshold) {
        mScrollThreshold = scrollThreshold;
    }
}