package io.operator;

import io.disposables.Disposable;
import io.functions.Predicate;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.observer.Observer;

public class ObservableFilter extends Observable {

	final Predicate predicate;
	private ObservableSource source;
	
	public ObservableFilter(Observable source, Predicate predicate) {
		this.source = source;
		this.predicate = predicate;
	}

	@Override
	protected void subscribeActual(Observer observer) {
		source.subscribe(new FilterObserver(observer,predicate));
	}
	
	static final class FilterObserver implements Observer,Disposable {

		private Predicate predicate;
		private Observer observer;
		
		public FilterObserver(Observer observer, Predicate predicate) {
			this.observer = observer;
			this.predicate = predicate;
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
			try {
				boolean b = predicate.test(o);
				if(b) {
					observer.onNext(o);
				}
			} catch (Exception e) {
				e.printStackTrace();
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
