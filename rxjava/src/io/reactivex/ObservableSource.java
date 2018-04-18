package io.reactivex;

import io.reactivex.observer.Observer;

public interface ObservableSource {
	
	void subscribe(Observer observer);
}
