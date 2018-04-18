package io.reactivex;

import io.disposables.Disposable;
import io.emitter.ObservableEmitter;
import io.reactivex.observer.Observer;

public class ObservableCreate extends Observable {

	private ObservableOnSubscribe source;
	
	public ObservableCreate(ObservableOnSubscribe source) {
		this.source = source;
	}

	// observer是真正的Observer
	@Override
	protected void subscribeActual(Observer observer) {
		CreateEmitter parent = new CreateEmitter(observer);
		// 调用观察者的onSubscribe方法
		observer.onSubscribe(parent);
		
		try {
			// 调用发射枪，由用户实现,最终根据用户编写调用createEmitter的onNext方法
			source.subscribe(parent);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static class CreateEmitter implements ObservableEmitter,Disposable {

		private Observer observer;
		
	
		public CreateEmitter(Observer observer) {
			super();
			this.observer = observer;
		}

		@Override
		public void onNext(Object value) {
			if(value == null) {
				//onError(error);
			}
			if(!isDisposed()) {
				// 调用观察者
				observer.onNext(value);
			}
		}

		@Override
		public void onError(Throwable error) {
			
		}

		@Override
		public void onComplete() {
		
		}

		@Override
		public void dispose() {
			
		}

		@Override
		public void setDisposable(Disposable d) {
			
		}

		@Override
		public boolean isDisposed() {
			return false;
		}
		
	}

}
