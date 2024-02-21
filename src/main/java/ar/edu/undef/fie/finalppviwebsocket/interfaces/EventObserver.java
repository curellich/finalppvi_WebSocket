package ar.edu.undef.fie.finalppviwebsocket.interfaces;

import ar.edu.undef.fie.finalppviwebsocket.service.Subscriber;

public interface EventObserver {
    void update(Subscriber subscriber);
}
