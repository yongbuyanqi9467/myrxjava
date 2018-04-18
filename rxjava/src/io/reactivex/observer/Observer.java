package io.reactivex.observer;

import io.disposables.Disposable;

public interface Observer {
	
	void onSubscribe(Disposable d);
	
	void onNext(Object o);
	
	void onError(Throwable e);
	
	void onCompete();
}
