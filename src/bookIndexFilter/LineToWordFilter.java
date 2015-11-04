package bookIndexFilter;

import filter.AbstractFilter;
import interfaces.IPushPipe;
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

        WordArray wordArray = new WordArray();
        wordArray.setLineNumber(value.getLineNumber());
        wordArray.setEndOfSignal(value.isEndOfSignal());

        String[] splitArray = value.getLine().split(" ");
        for(String s : splitArray){
            wordArray.addToWordArray(s);
        }

        //TODO Testausgabe löschen
        //TODO ï»¿ diese Satzzeichen löschen und sonstige Satzzeichen (, . " @ etc.)
        //System.out.println(wordArray.getLineNumber());
        //wordArray.getWordArray().forEach(System.out::println);

        writeOutput(wordArray);
    }

}
