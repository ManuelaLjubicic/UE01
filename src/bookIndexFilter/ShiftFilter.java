package bookIndexFilter;

import filter.AbstractFilter;
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

    @Override
    public LineWithLineNumber read() throws StreamCorruptedException {
        return null;
    }

    @Override
    public void run() {

    }

    @Override
    public void write(WordArray value) throws StreamCorruptedException {

        String line = "";
        int startIndex = 0;
        int arrayLength = value.getWordArray().size();
        int endIndex = arrayLength - 1;
        int currentIndex = 0;

        while(startIndex < arrayLength){
            currentIndex = startIndex;

            while(currentIndex != endIndex){
                line = line + value.getWordArray().get(currentIndex) + " ";
                currentIndex++;
                if(currentIndex > (arrayLength-1)){
                    currentIndex = 0;
                }
            }

            LineWithLineNumber lineWithLineNumber = new LineWithLineNumber();
            lineWithLineNumber.setEndOfSignal(value.isEndOfSignal());
            lineWithLineNumber.setLineNumber(value.getLineNumber());
            lineWithLineNumber.setLine(line);
//            System.out.println(lineWithLineNumber.getLineNumber());
//            System.out.println(lineWithLineNumber.getLine());
            writeOutput(lineWithLineNumber);

            line = "";

            startIndex++;
            endIndex++;
            if(endIndex > arrayLength - 1){
                endIndex = 0;
            }
        }
    }
}
