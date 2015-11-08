package bookIndexFilter;

import filter.AbstractFilter;
import interfaces.IPullPipe;
import interfaces.IPushPipe;
import others.UselessWord;
import transferObject.LineWithLineNumber;
import transferObject.WordArray;

import javax.sound.sampled.Line;
import java.io.StreamCorruptedException;
import java.security.InvalidParameterException;
import java.util.LinkedList;

/**
 * Created by manue on 02.11.2015.
 */
public class ShiftFilter extends AbstractFilter<WordArray, LinkedList<LineWithLineNumber>>{

    public ShiftFilter(IPushPipe<LinkedList<LineWithLineNumber>> output) throws InvalidParameterException {
        super(output);
    }

    public ShiftFilter(IPullPipe<WordArray> input) throws InvalidParameterException {
        super(input);
    }

    @Override
    public LinkedList<LineWithLineNumber> read() throws StreamCorruptedException {

        return shift(readInput());
    }

    @Override
    public void run() {

    }

    @Override
    public void write(WordArray value) throws StreamCorruptedException {

        writeOutput(shift(value));

    }

    private LinkedList<LineWithLineNumber> shift(WordArray value) {
        LinkedList<LineWithLineNumber> lines = new LinkedList<>();
        LineWithLineNumber lineWithLineNumber;

        if(!value.isEndOfSignal()){
            int arrayLength = value.getWordArray().size();

            for(int i = 0; i < arrayLength; i++){

                value.getWordArray().add(value.getWordArray().remove(0));

                if(!UselessWord.getUselessWords().contains(value.getWordArray().get(0))) {
                    lineWithLineNumber = new LineWithLineNumber();
                    lineWithLineNumber.setLine(value.toString());
                    lineWithLineNumber.setEndOfSignal(value.isEndOfSignal());
                    lines.add(lineWithLineNumber);
                }
            }
        }else{
            lineWithLineNumber = new LineWithLineNumber();
            lineWithLineNumber.setEndOfSignal(true);
            lines.add(lineWithLineNumber);
        }

        return lines;
    }
}
