package io.emitter;

/**
 * 发射器
 * @author Administrator
 *
 * @param <T>
 */
public interface Emitter {
	
	void onNext(Object value);
	
	void onError(Throwable error);
	
	void onComplete();
}
