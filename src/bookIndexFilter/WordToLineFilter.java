package bookIndexFilter;

import align.Alignment;
import filter.AbstractFilter;
import interfaces.IPullPipe;
import interfaces.IPushPipe;
import transferObject.LineWithLineNumber;
import transferObject.WordTransfer;

import java.io.StreamCorruptedException;
import java.security.InvalidParameterException;

/**
 * Created by manue on 05.11.2015.
 */
public class WordToLineFilter extends AbstractFilter<WordTransfer, LineWithLineNumber> {

    private LineWithLineNumber _lineWithLineNumber;
    private boolean _isEndOfLine;
    private StringBuffer _sb;
    private int _lineNumber;
    private int _lineLength;
    final int _MIN_LINE_LENGTH = 60;
    Alignment _align;

    public WordToLineFilter(IPushPipe<LineWithLineNumber> output, int lineLength, Alignment align) throws InvalidParameterException {
        super(output);
        init(lineLength, align);
    }

    public WordToLineFilter(IPullPipe<WordTransfer> input, int lineLength, Alignment align) throws InvalidParameterException {
        super(input);
        init(lineLength, align);
    }

    private void init(int lineLength, Alignment align){
        _sb = new StringBuffer();
        _lineWithLineNumber = new LineWithLineNumber();
        _isEndOfLine = false;
        _lineLength = lineLength;
        _align = align;
        if (_lineLength < _MIN_LINE_LENGTH) {
            _lineLength = _MIN_LINE_LENGTH;
        }
    }

    @Override
    public LineWithLineNumber read() throws StreamCorruptedException {

        WordTransfer wordTransfer = readInput();

        while(!wordToLine(wordTransfer) && (!wordTransfer.getIsEndOfSignal())){
            wordTransfer = readInput();
        }
        return _lineWithLineNumber;
    }

    @Override
    public void run() {

    }

    @Override
    public void write(WordTransfer value) throws StreamCorruptedException {

        if(wordToLine(value)){
            writeOutput(_lineWithLineNumber);
        }
    }


    private boolean wordToLine(WordTransfer value) {

        if(value.getIsEndOfSignal()){
            setTransferObject(value);
            return true;
        }

        if ((_sb.length() + value.getWord().length() < _lineLength)) {
            _sb.append(value.getWord());
        } else {
            setTransferObject(value);
            return true;
        }
        return false;
    }

    private void setTransferObject(WordTransfer value){

        String line = _sb.toString();
        int spaceValue = _lineLength - line.length();

        if(_align == Alignment.CENTER){
            spaceValue = spaceValue / 2;
            for(int i = 0; i <= spaceValue; i++){
                line = " " + line;
            }
        }else if(_align == Alignment.RIGHT){
            for(int i = 0; i <= spaceValue; i++){
                line = " " + line;
            }
        }

        _lineNumber++;
        _lineWithLineNumber = new LineWithLineNumber();
        _lineWithLineNumber.setLine(line);
        _lineWithLineNumber.setLineNumber(_lineNumber);
        _lineWithLineNumber.setEndOfSignal(value.getIsEndOfSignal());
        _sb = new StringBuffer();
        _sb.append(value.getWord());
    }
}
