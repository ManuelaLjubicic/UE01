package bookIndexFilter;

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

    private StringBuffer _sb;
    private int _lineNumber;

    public WordToLineFilter(IPushPipe<LineWithLineNumber> output) throws InvalidParameterException {
        super(output);
        _sb = new StringBuffer();
    }

    public WordToLineFilter(IPullPipe<WordTransfer> input) throws InvalidParameterException {
        super(input);
        _sb = new StringBuffer();
    }


    @Override
    public LineWithLineNumber read() throws StreamCorruptedException {
        return null;
    }

    @Override
    public void run() {

    }

    @Override
    public void write(WordTransfer value) throws StreamCorruptedException {

        LineWithLineNumber lineWithLineNumber = wordToLine(value);
        if(lineWithLineNumber != null){
            writeOutput(wordToLine(value));
        }
    }


    private LineWithLineNumber wordToLine(WordTransfer value){

        LineWithLineNumber lineWithlineNumber;

        if (value.getWord().length() < value.getLineLength()){
            if((_sb.length() + value.getWord().length()) < value.getLineLength()){
                _sb.append(value.getWord());
            }else{
                lineWithlineNumber = new LineWithLineNumber();
                _lineNumber++;
                lineWithlineNumber.setLine(_sb.toString());
                lineWithlineNumber.setLineNumber(_lineNumber);
                lineWithlineNumber.setEndOfSignal(value.getIsEndOfSignal());

                _sb = new StringBuffer();
                _sb.append(value.getWord());
                System.out.println(lineWithlineNumber.getLine());
                return lineWithlineNumber;
            }


        }else{
            //TODO Wort spliten od. Fehlermeldung

            int i = 0;
            int j = value.getLineLength()-1;
            while((value.getWord().length()-j) > value.getLineLength()){
                _sb = new StringBuffer();
                _sb.append(value.getWord().substring(i, j));
                i = i + value.getLineLength();
                j = j + value.getLineLength();

                lineWithlineNumber = new LineWithLineNumber();
                _lineNumber++;
                lineWithlineNumber.setLine(_sb.toString());
                lineWithlineNumber.setLineNumber(_lineNumber);
                lineWithlineNumber.setEndOfSignal(value.getIsEndOfSignal());
                return lineWithlineNumber;
            }

        }
        return lineWithlineNumber;
    }
}
