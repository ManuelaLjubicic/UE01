package transferObject;

import interfaces.Writeable;

/**
 * Created by manue on 31.10.2015.
 */
public class LineWithLineNumber {

    private String _line;
    private int _lineNumber;
    private boolean _endOfSignal;

//    public LineWithLineNumber(String line, int lineNumber){
//        _line = line;
//        _lineNumber = lineNumber;
//    }

    public String getLine() {
        return _line;
    }

    public void setLine(String _line) {
        this._line = _line;
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

    public void setEndOfSignal(boolean endOfSignal) {
        this._endOfSignal = endOfSignal;
    }
}
