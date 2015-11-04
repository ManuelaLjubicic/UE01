package bookIndexFilter;

import filter.AbstractFilter;
import interfaces.*;
import transferObject.LineWithLineNumber;
import java.io.*;
import java.security.InvalidParameterException;

/**
 * Created by manue on 31.10.2015.
 */
public class ReadLineFromRessourceFilter extends AbstractFilter<String, LineWithLineNumber> {

    FileReader _file;
    int _lineNumber = 1;

    //TODO beim zweiten Filter müssten wir hier das WordArray als Output mitgeben!!!
    public ReadLineFromRessourceFilter(IPushPipe<LineWithLineNumber> output) throws InvalidParameterException {
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
    public void write(String filePath) throws StreamCorruptedException {

        try {
            _file = new FileReader(filePath);
        } catch (FileNotFoundException e) {
            System.out.println("File doesn't exist.");
            e.printStackTrace();
        }

        BufferedReader br = new BufferedReader(_file);
        StringBuffer sb = new StringBuffer();
        String s;
        LineWithLineNumber lineEntity = new LineWithLineNumber();
        lineEntity.setEndOfSignal(false);

        int r;
        char c;

        try {

            while ((r = br.read()) != -1) {

                while ((r != 10) && (r != 13)) {
                    try {
                        c = (char) r;
                        sb.append(c);

                        r = br.read();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if(r != 13){
                    _lineNumber++;
                }

                s = sb.toString();
                //System.out.println(s);
                if (!s.isEmpty()) {
                    lineEntity.setLine(s);
                    lineEntity.setLineNumber(_lineNumber);

                    //die Zeile wird an die Pipe "ReadLineFromRessourcePipe" übergeben
                    writeOutput(lineEntity);
                }
                sb.delete(0, sb.length());

            }

            // end of File
            if(r == -1){
                lineEntity.setEndOfSignal(true);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
