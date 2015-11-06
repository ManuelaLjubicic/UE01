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

    private String _word;
    private boolean _endOfWord;
    private WordTransfer _wordTransfer = new WordTransfer();

    public CharToWordFilter(IPushPipe<WordTransfer> output) throws InvalidParameterException {
        super(output);
        _word = "";
        _endOfWord = false;
    }

    public CharToWordFilter(IPullPipe<CharTransfer> input) throws InvalidParameterException {
        super(input);
        _word = "";
        _endOfWord = false;
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

        if(charToWord(value)){
            _wordTransfer = new WordTransfer();
            _wordTransfer.setWord(_word + " ");
            _word = "";
            _wordTransfer.setLineLength(value.getLineLength());
            _wordTransfer.setIsEndOfSignal(value.isIsEndOfSignal());
            writeOutput(_wordTransfer);
        }
    }

    private boolean charToWord(CharTransfer value){

        WordTransfer wordTransfer = new WordTransfer();

        if((value.get_c() != ' ')){
            _word = _word + value.get_c();
        }else{
            return true;
        }
        return false;
    }
}
