package bookIndexFilter;

import filter.AbstractFilter;
import interfaces.IPullPipe;
import interfaces.IPushPipe;
import transferObject.CharTransfer;
import transferObject.WordTransfer;

import java.io.StreamCorruptedException;
import java.security.InvalidParameterException;

/**
 * Created by manue on 05.11.2015.
 */
public class CharToWordFilter extends AbstractFilter<CharTransfer, WordTransfer> {

    private String _word;
    private WordTransfer _wordTransfer = new WordTransfer();

    public CharToWordFilter(IPushPipe<WordTransfer> output) throws InvalidParameterException {
        super(output);
        _word = "";
    }

    public CharToWordFilter(IPullPipe<CharTransfer> input) throws InvalidParameterException {
        super(input);
        _word = "";
    }

    @Override
    public WordTransfer read() throws StreamCorruptedException {

        CharTransfer charTransfer = readInput();

        while(!charToWord(charTransfer)&&(!charTransfer.isEndOfSignal())){
            charTransfer = readInput();
        }
        _wordTransfer = new WordTransfer();
        _wordTransfer.setWord(_word + " ");
        _word = "";
        _wordTransfer.setIsEndOfSignal(charTransfer.isEndOfSignal());
        return _wordTransfer;
    }

    @Override
    public void run() {

    }

    @Override
    public void write(CharTransfer value) throws StreamCorruptedException {

        if(charToWord(value) || value.isEndOfSignal()){
            _wordTransfer = new WordTransfer();
            _wordTransfer.setWord(_word + " ");
            _word = "";
            _wordTransfer.setIsEndOfSignal(value.isEndOfSignal());
            writeOutput(_wordTransfer);
        }
    }

    private boolean charToWord(CharTransfer value){

        if((value.get_c() != ' ')){
            _word = _word + value.get_c();
            return false;
        }

        return true;
    }
}
