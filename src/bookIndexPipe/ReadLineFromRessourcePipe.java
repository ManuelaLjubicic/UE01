package bookIndexPipe;

import transferObject.LineWithLineNumber;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by manue on 31.10.2015.
 */
public class ReadLineFromRessourcePipe {

    private static ReadLineFromRessourcePipe _instance;
    private Queue<LineWithLineNumber> _queue;

    private ReadLineFromRessourcePipe(){
    _queue = new LinkedList<>();

    }

    public static ReadLineFromRessourcePipe getInstance(){

        if(_instance == null) {
            _instance = new ReadLineFromRessourcePipe();
        }
            return _instance;
    }


    public Queue<LineWithLineNumber> getQueue() {
        return _queue;
    }

    public void setQueue(Queue<LineWithLineNumber> queue) {
        this._queue = queue;
    }

   //holt das erste Element und löscht dieses von der Queue
    public LineWithLineNumber pollEntity(){
       return _queue.poll();
    }
    //fügt ein Element zur Queue hinzu
    public void addEntity(LineWithLineNumber entity){
        _queue.add(entity);
    }

    public boolean isEmpty (){
        return _queue.isEmpty();
    }
}
