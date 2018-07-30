package com.example.sasham.testproject.messages;

import com.example.sasham.testproject.model.Message;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MessagesListingPresenterImp implements MessagesListingPresenter {

    private MessagesListingInteractor interactor;
    private MessagesListingView view;

    @Inject
    public MessagesListingPresenterImp(MessagesListingInteractor interactor) {
        this.interactor = interactor;
    }

    @Override
    public void setView(MessagesListingView view) {

        this.view = view;
    }

    @Override
    public void fetchMessages(final String themeId) {

        view.onLoading();

        interactor.fetchMessages(themeId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Message>>() {
                               @Override
                               public void accept(List<Message> messages) throws Exception {
                                   displayMessages(messages);
                               }
                           },
                        new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                if (isViewAttached()) {
                                    view.onError(throwable.getMessage());
                                }
                            }
                        });
    }

    private void displayMessages(List<Message> messages) {
        if (isViewAttached()) {
            view.showThemeMessages(messages);
        }
    }

    private boolean isViewAttached() {
        return view != null;
    }

    @Override
    public void destroy() {
        view = null;
    }

    @Override
    public void loadNewData(String themeId) {
        fetchMessages(themeId);
    }
}
