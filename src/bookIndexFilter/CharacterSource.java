package bookIndexFilter;

import filter.AbstractFilter;
import interfaces.IPushPipe;
import transferObject.CharTransfer;
import transferObject.LineWithLineNumber;

import java.io.*;
import java.security.InvalidParameterException;
import java.util.LinkedList;

/**
 * Created by manue on 05.11.2015.
 */
public class CharacterSource extends AbstractFilter<String, CharTransfer> {

    private String _filePath;
    private FileReader _file;
    private LinkedList<CharTransfer> _chars;
    private int _lineLength;

    public CharacterSource(IPushPipe<CharTransfer> output, int lineLength) throws InvalidParameterException {
        super(output);
        _lineLength = lineLength;
    }

    public CharacterSource(String filePath, int lineLength) throws InvalidParameterException {
        this._filePath = filePath;
        this._lineLength = lineLength;
        readCharacter();
    }


    @Override
    public CharTransfer read() throws StreamCorruptedException {
        return null;
    }

    @Override
    public void run() {

    }

    @Override
    public void write(String filePath) throws StreamCorruptedException {
        _filePath = filePath;
        readCharacter();
        for (CharTransfer c : _chars) {
            writeOutput(c);
        }

    }

    private void readCharacter(){

        try {
            _file = new FileReader(_filePath);
        } catch (FileNotFoundException e) {
            System.out.println("File doesn't exist.");
            e.printStackTrace();
        }

        BufferedReader br = new BufferedReader(_file);
        CharTransfer charTransfer = new CharTransfer();
        charTransfer.setIsEndOfSignal(false);
        _chars = new LinkedList<>();

        int r;
        char c;
        try {
            while ((r = br.read()) != -1) {
                while ((r != 10) && (r != 13)) {

                    try {
                        c = (char) r;
                        charTransfer.setC(c);
                        charTransfer.setLineLength(_lineLength);
                        _chars.add(charTransfer);
                       //System.out.print(charTransfer.get_c());

                        r = br.read();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            if (r == -1) {
                charTransfer = new CharTransfer();
                charTransfer.setIsEndOfSignal(true);
                charTransfer.setLineLength(_lineLength);
                _chars.add(charTransfer);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
