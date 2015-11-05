package bookIndexFilter;

import filter.AbstractFilter;
import interfaces.IPullPipe;
import interfaces.IPushPipe;
import transferObject.LineWithLineNumber;
import transferObject.WordArray;

import java.io.StreamCorruptedException;
import java.security.InvalidParameterException;

/**
 * Created by manue on 02.11.2015.
 */
public class ShiftFilter extends AbstractFilter<WordArray, LineWithLineNumber>{

    public ShiftFilter(IPushPipe<LineWithLineNumber> output) throws InvalidParameterException {
        super(output);
    }

    public ShiftFilter(IPullPipe<WordArray> input) throws InvalidParameterException {
        super(input);
    }

    @Override
    public LineWithLineNumber read() throws StreamCorruptedException {

        return shift(readInput());
    }

    @Override
    public void run() {

    }

    @Override
    public void write(WordArray value) throws StreamCorruptedException {

        writeOutput(shift(value));

    }

    private LineWithLineNumber shift(WordArray value) {
        LineWithLineNumber lineWithLineNumber = new LineWithLineNumber();

        if(!value.isEndOfSignal()){
            int arrayLength = value.getWordArray().size();

            for(int i = 0; i < arrayLength; i++){
                value.getWordArray().add(value.getWordArray().remove(0));
                ;
                lineWithLineNumber.setLine(value.toString());
                lineWithLineNumber.setEndOfSignal(value.isEndOfSignal());
            }
        }else{
            lineWithLineNumber.setEndOfSignal(true);
        }
        return lineWithLineNumber;
    }
}
