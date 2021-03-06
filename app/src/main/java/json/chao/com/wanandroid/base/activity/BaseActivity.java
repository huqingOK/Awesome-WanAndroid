package json.chao.com.wanandroid.base.activity;

import javax.inject.Inject;

import json.chao.com.wanandroid.R;
import json.chao.com.wanandroid.app.GeeksApp;
import json.chao.com.wanandroid.base.presenter.AbstractPresenter;
import json.chao.com.wanandroid.base.view.BaseView;
import json.chao.com.wanandroid.di.component.ActivityComponent;
import json.chao.com.wanandroid.di.component.DaggerActivityComponent;
import json.chao.com.wanandroid.di.module.ActivityModule;
import json.chao.com.wanandroid.utils.CommonUtils;

/**
 * MVP模式的Base Activity
 *
 * @author quchao
 * @date 2017/11/28
 */

public abstract class BaseActivity<T extends AbstractPresenter> extends AbstractSimpleActivity implements BaseView {

    @Inject
    protected T mPresenter;

    @Override
    protected void onDestroy() {
        if (mPresenter != null) {
            mPresenter.detachView();
        }
        super.onDestroy();
    }

    protected ActivityComponent getActivityComponent() {
        return DaggerActivityComponent.builder()
                .appComponent(GeeksApp.getAppComponent())
                .activityModule(new ActivityModule(this))
                .build();
    }

    @Override
    protected void onViewCreated() {
        super.onViewCreated();
        initInject();
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
    }

    @Override
    public void showErrorMsg(String errorMsg) {
        CommonUtils.showMessage(this, errorMsg);
    }

    @Override
    public void showError() {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void showCollectFail() {
        CommonUtils.showMessage(this, getString(R.string.collect_fail));
    }

    @Override
    public void showCancelCollectFail() {
        CommonUtils.showMessage(this, getString(R.string.cancel_collect_fail));
    }

    @Override
    public void showCollectSuccess() {

    }

    @Override
    public void showCancelCollectSuccess() {

    }

    @Override
    public void showLoginView() {

    }

    @Override
    public void showLogoutView() {

    }


    /**
     * 注入当前Activity所需的依赖
     */
    protected abstract void initInject();

}
