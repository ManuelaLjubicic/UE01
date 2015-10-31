package transferObject;

import interfaces.Writeable;

/**
 * Created by manue on 31.10.2015.
 */
public class LineWithLineNumber {

    String _line;
    int _lineNumber;

    public LineWithLineNumber(String line, int lineNumber){
        _line = line;
        _lineNumber = lineNumber;
    }

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
}
