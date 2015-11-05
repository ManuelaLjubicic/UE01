package bookIndexFilter;

import filter.AbstractFilter;
import interfaces.IPullPipe;
import interfaces.IPushPipe;
import transferObject.CharTransfer;
import transferObject.LineWithLineNumber;
import transferObject.WordArray;
import transferObject.WordTransfer;

import java.io.StreamCorruptedException;
import java.security.InvalidParameterException;

/**
 * Created by manue on 05.11.2015.
 */
public class CharToWordFilter extends AbstractFilter<CharTransfer, WordTransfer> {

    private String _word = "";

    public CharToWordFilter(IPushPipe<WordTransfer> output) throws InvalidParameterException {
        super(output);
    }

    public CharToWordFilter(IPullPipe<CharTransfer> input) throws InvalidParameterException {
        super(input);
    }

    @Override
    public WordTransfer read() throws StreamCorruptedException {
        return null;
    }

    @Override
    public void run() {

    }

    @Override
    public void write(CharTransfer value) throws StreamCorruptedException {

        writeOutput(charToWord(value));
    }

    private WordTransfer charToWord(CharTransfer value){

        WordTransfer wordTransfer = new WordTransfer();
        System.out.println(value.get_c());

        if((value.get_c() != ' ')){
            System.out.println(value.get_c());
            _word = _word + value.get_c();
        }else{

            wordTransfer.setIsEndOfSignal(value.isIsEndOfSignal());
            wordTransfer.setWord(_word);
            wordTransfer.setLineLength(value.getLineLength());

            _word = "";
            return wordTransfer;
        }
        return null;
    }
}
