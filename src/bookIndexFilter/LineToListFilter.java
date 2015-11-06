package bookIndexFilter;

import filter.AbstractFilter;
import interfaces.IPullPipe;
import interfaces.IPushPipe;
import transferObject.CharTransfer;
import transferObject.LineWithLineNumber;
import transferObject.WordTransfer;

import java.io.StreamCorruptedException;
import java.security.InvalidParameterException;
import java.util.LinkedList;

/**
 * Created by manue on 05.11.2015.
 */
public class LineToListFilter extends AbstractFilter<LineWithLineNumber, LinkedList<String>> {

    LinkedList<String> _lines;


    public LineToListFilter(IPushPipe<LinkedList<String>> output) throws InvalidParameterException {
        super(output);
        _lines = new LinkedList<>();
    }

    public LineToListFilter(IPullPipe<LineWithLineNumber> input) throws InvalidParameterException {
        super(input);
        _lines = new LinkedList<>();
    }

    @Override
    public LinkedList<String> read() throws StreamCorruptedException {
        return null;
    }

    @Override
    public void run() {

    }

    @Override
    public void write(LineWithLineNumber value) throws StreamCorruptedException {
        if(lineToList(value)){
            writeOutput(_lines);
        }
    }

    private boolean lineToList(LineWithLineNumber value){

        if(!value.isEndOfSignal()){
            _lines.add(value.getLine());
            System.out.println(_lines.getLast());
        }
        return value.isEndOfSignal();
    }
}
