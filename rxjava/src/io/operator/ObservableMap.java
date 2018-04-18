package io.operator;

import io.disposables.Disposable;
import io.functions.Function;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.observer.Observer;

public class ObservableMap extends Observable {

	private Function function;
	private ObservableSource source;
	
	public ObservableMap(Observable source, Function mapper) {
		this.source = source;
		this.function = mapper;
	}

	/**
	 * 此处参数observer是用户创建observer
	 */
	@Override
	protected void subscribeActual(Observer observer) {
		// source代表ObservateCreate
		source.subscribe(new MapObserver(observer,function));
	}

	static final class MapObserver implements Observer,Disposable {

		private Function mapper;
		private Observer observer;
		
		public MapObserver(Observer observer, Function function) {
			this.observer = observer;
			this.mapper = function;
		}

		@Override
		public void dispose() {
		
		}

		@Override
		public boolean isDisposed() {
			return false;
		}

		@Override
		public void onSubscribe(Disposable d) {
			observer.onSubscribe(this);
		}

		@Override
		public void onNext(Object o) {
			if(!isDisposed()) {
				try {
					observer.onNext(mapper.apply(o));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		@Override
		public void onError(Throwable e) {
		
		}

		@Override
		public void onCompete() {
			
		}
		
	}
}
