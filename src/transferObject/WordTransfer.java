package transferObject;

/**
 * Created by manue on 05.11.2015.
 */
public class WordTransfer {
    private String _word;
    private boolean _isEndOfSignal;
    private int _lineLength;

    public String getWord() {
        return _word;
    }

    public void setWord(String _word) {
        this._word = _word;
    }

    public boolean getIsEndOfSignal() {
        return _isEndOfSignal;
    }

    public void setIsEndOfSignal(boolean _isEndOfSignal) {
        this._isEndOfSignal = _isEndOfSignal;
    }

    public int getLineLength() {
        return _lineLength;
    }

    public void setLineLength(int _lineLength) {
        this._lineLength = _lineLength;
    }
}
