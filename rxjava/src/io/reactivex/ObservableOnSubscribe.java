package io.reactivex;

import io.emitter.ObservableEmitter;

public interface ObservableOnSubscribe {
	
	void subscribe(ObservableEmitter e) throws Exception;
}
