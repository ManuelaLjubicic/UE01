package bookIndexFilter;

import bookIndexPipe.LineInWordsPipe;
import filter.AbstractFilter;
import interfaces.IPushPipe;
import interfaces.Writeable;
import transferObject.LineWithLineNumber;
import transferObject.WordArray;

import java.io.StreamCorruptedException;
import java.security.InvalidParameterException;

/**
 * Created by Karin on 31.10.2015.
 */

//TODO Datentyp Object muss noch durch das Array das weiter geschickt wird ausgetauscht werden
public class LineToWordFilter extends AbstractFilter<LineWithLineNumber, WordArray>{

    public LineToWordFilter(IPushPipe<WordArray> output) throws InvalidParameterException {
        super(output);
    }


    @Override
    public WordArray read() throws StreamCorruptedException {
        return null;
    }

    @Override
    public void run() {

    }

    @Override
    public void write(LineWithLineNumber value) throws StreamCorruptedException {
        // TODO implement methods for dividing the Line in words and building an ArrayList of Words
    }

}
