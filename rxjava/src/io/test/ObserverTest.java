package io.test;

import io.disposables.Disposable;
import io.emitter.ObservableEmitter;
import io.functions.Function;
import io.functions.Predicate;
import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.observer.Observer;

public class ObserverTest {
	
	public static void main(String[] args) {
		
		// 从上到下包装
		// 每次都是一次包装的Observable
		Observable.create(new ObservableOnSubscribe() {
			
			@Override
			public void subscribe(ObservableEmitter e) throws Exception {
				e.onNext("subscribe...");
			}
			
		}).filter(new Predicate() {

			@Override
			public boolean test(Object t) throws Exception {
				return 1 == 1;
			}
			
		}).map(new Function() {

			@Override
			public Object apply(Object t) throws Exception {
				return t + "转换";
			}	
		}).subscribe(new Observer() {
			
			@Override
			public void onSubscribe(Disposable d) {
				System.out.println("onSubscribe...");
			}
			
			@Override
			public void onNext(Object o) {
				System.out.println(o);
			}
			
			@Override
			public void onError(Throwable e) {
				
			}
			
			@Override
			public void onCompete() {
				System.out.println("----------");
			}
		});
	}
}
