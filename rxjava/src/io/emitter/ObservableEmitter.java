package io.emitter;

import io.disposables.Disposable;

public interface ObservableEmitter extends Emitter {
	
	void setDisposable(Disposable d);
	
	boolean isDisposed();
	
}
