package others;

import java.util.HashSet;

/**
 * Created by manue on 08.11.2015.
 */
public class UselessWord {

    private static HashSet<String> _uselessWords;

    static{
        _uselessWords = new HashSet<>();
        fillUseLessWords();
    }


    public static void setUseLessWord(String uselessWord){
        _uselessWords.add(uselessWord);
    }

    public static HashSet<String> getUselessWords(){
        return _uselessWords;
    }

    private static void fillUseLessWords(){
        _uselessWords.add("the");
        _uselessWords.add("are");
        _uselessWords.add("and");
        _uselessWords.add("is");
        _uselessWords.add("for");
        _uselessWords.add("it");
        _uselessWords.add("or");
        _uselessWords.add("by");
        _uselessWords.add("a");
        _uselessWords.add("of");
        _uselessWords.add("at");
        _uselessWords.add("no");
        _uselessWords.add("to");
        _uselessWords.add("our");
        _uselessWords.add("with");
        _uselessWords.add("such");
        _uselessWords.add("be");
        _uselessWords.add("in");
    }
}
