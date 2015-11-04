package transferObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Karin on 31.10.2015.
 */
public class WordArray {
    List<String> _wordArray ;
    int _lineNumber;
    boolean _endOfSignal;

    public WordArray(){
        _wordArray = new ArrayList<>();
    }

    public void addToWordArray(String word){
        _wordArray.add(word);
    }

    public List<String> getWordArray() {
        return _wordArray;
    }

    public void setWordArray(List<String> _wordArray) {
        this._wordArray = _wordArray;
    }

    public int getLineNumber() {
        return _lineNumber;
    }

    public void setLineNumber(int _lineNumber) {
        this._lineNumber = _lineNumber;
    }

    public boolean isEndOfSignal() {
        return _endOfSignal;
    }

    public void setEndOfSignal(boolean _endOfSignal) {
        this._endOfSignal = _endOfSignal;
    }
}
