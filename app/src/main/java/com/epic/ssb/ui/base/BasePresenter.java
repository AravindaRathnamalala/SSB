package com.epic.ssb.ui.base;

/**
 * Base presenter any presenter of the application must extend. It provides initial injections and
 * required methods.
 *
 * @param V the type of the View the presenter is based on
 * @property view the view the presenter is based on
 * @constructor Injects the required dependencies
 */

public class BasePresenter<V extends BaseView> implements Presenter<V> {


  private V view;

  @Override
  public void attachView(V view) {
    this.view = view;
  }

  @Override
  public void detachView() {
    view = null;
  }
  public boolean isViewAttached() {
    return view != null;
  }

  public V getView() {
    return view;
  }

  public void checkViewAttached() {
    if (!isViewAttached()) throw new MvpViewNotAttachedException();
  }

  public static class MvpViewNotAttachedException extends RuntimeException {
    public MvpViewNotAttachedException() {
      super("Please call Presenter.attachView(MvpView) before" +
          " requesting data to the Presenter");
    }
  }
}
