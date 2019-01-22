package zesley.sergey.homework2;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.subjects.PublishSubject;

public class EventBus {
    private Observable<String> src1;
    private Observable<String> src2;
    private PublishSubject<String> mStringPublishSubject;
    private Observable<String> allsrs;


    public EventBus(Observable<String> src1, Observable<String> src2) {
        this.src1 = src1;
        this.src2 = src2;
        mStringPublishSubject = PublishSubject.create();
        allsrs = Observable.merge(src1, src2, mStringPublishSubject);
    }

    public void onNext(String s) {
        mStringPublishSubject.onNext(s);
    }

    public void subscribe(Observer<String> observer) {
        allsrs.subscribe(observer);
    }
}
