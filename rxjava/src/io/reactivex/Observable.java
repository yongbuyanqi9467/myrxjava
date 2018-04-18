package io.reactivex;

import io.functions.Function;
import io.functions.Predicate;
import io.operator.ObservableFilter;
import io.operator.ObservableMap;
import io.reactivex.observer.Observer;

/**
 * 被观察者
 * @author zws
 *
 */
public abstract class Observable implements ObservableSource {

	
	protected abstract void subscribeActual(Observer observer);
	
	public static Observable create(ObservableOnSubscribe source) {
		return new ObservableCreate(source); 
	}
		
	/**
	 * 此处用了模板方法模式
	 */
	@Override
	public void subscribe(Observer observer) {
		subscribeActual(observer);
	}

	// 转换
	public Observable map(Function mapper) {
		// 传递的this是ObservableCreate
		System.out.println("this: " + this);
		return new ObservableMap(this,mapper);
	}
	
	public Observable filter(Predicate predicate) {
		System.out.println("this: " + this);
		return new ObservableFilter(this,predicate);
	}
}
