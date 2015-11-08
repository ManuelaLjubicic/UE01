package bookIndexFilter;

import filter.AbstractFilter;
import interfaces.IPullPipe;
import interfaces.IPushPipe;
import transferObject.LineWithLineNumber;

import java.io.StreamCorruptedException;
import java.security.InvalidParameterException;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

/**
 * Created by manue on 02.11.2015.
 */
public class CreateIndexFilter extends AbstractFilter<LinkedList<LineWithLineNumber>, LinkedList<String>> {

    private LinkedList<String> _indexList = new LinkedList<>();

    public CreateIndexFilter(IPushPipe<LinkedList<String>> output) throws InvalidParameterException {
        super(output);
    }

    public CreateIndexFilter(IPullPipe<LinkedList<LineWithLineNumber>> input) throws InvalidParameterException {
        super(input);
    }

    @Override
    public LinkedList<String> read() throws StreamCorruptedException {

        boolean endSignalReached = false;
        LinkedList<LineWithLineNumber> lines = readInput();

        while(!endSignalReached) {
            for (LineWithLineNumber l : lines) {
                if(l.isEndOfSignal()) {
                    endSignalReached = true;
                    createIndex(l);
                    break;
                }

                createIndex(l);
            }

            lines = readInput();
        }

        return _indexList;

    }

    @Override
    public void run() {

    }

    @Override
    public void write(LinkedList<LineWithLineNumber> value) throws StreamCorruptedException {

        for(LineWithLineNumber l : value) {
            if (createIndex(l)) {
                writeOutput(_indexList);
            }
        }
    }

    private boolean createIndex(LineWithLineNumber value){
        if(!value.isEndOfSignal()){
            if(value.getLine() != null){
                _indexList.add(value.getLine());
            }

        }else{
            Collections.sort(_indexList, new SortIgnoreCase());
        }
        return value.isEndOfSignal();
    }

    public class SortIgnoreCase implements Comparator {

        @Override
        public int compare(Object o1, Object o2) {
            String s1 = (String) o1;
            String s2 = (String)o2;
            if(s1 == null){
                return -1;
            }
            if(s2 == null){
                return 1;
            }
            return s1.toLowerCase().compareTo((s2.toLowerCase()));
        }
    }
}
