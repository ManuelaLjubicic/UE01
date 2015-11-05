package transferObject;

/**
 * Created by manue on 05.11.2015.
 */
public class CharTransfer {
    private char _c;
    private int _lineLength;
    private boolean _isEndOfSignal;

    public char get_c() {
        return _c;
    }

    public void setC(char _c) {
        this._c = _c;
    }

    public int getLineLength() {
        return _lineLength;
    }

    public void setLineLength(int _lineLength) {
        this._lineLength = _lineLength;
    }

    public boolean isIsEndOfSignal() {
        return _isEndOfSignal;
    }

    public void setIsEndOfSignal(boolean _isEndOfSignal) {
        this._isEndOfSignal = _isEndOfSignal;
    }
}
