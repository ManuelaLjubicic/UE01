package bookIndexPipe;

import transferObject.WordArray;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by Karin on 31.10.2015.
 */
public class LineInWordsPipe {
    private Queue<WordArray> _wordArray;
    private static LineInWordsPipe _instance;
    private LineInWordsPipe(){
        _wordArray = new LinkedList<>();
    }

    public static LineInWordsPipe getInstance(){
        if (_instance == null){
            _instance = new LineInWordsPipe();
        }
        return _instance;
    }

    // fügt eine Entity in die Queue ein
    public void addEntity(WordArray wa){
        _wordArray.add(wa);
    }

    public WordArray pollEntity(){
        return _wordArray.poll();
    }

    public boolean isEmpty(){
        return _wordArray.isEmpty();
    }

        }
