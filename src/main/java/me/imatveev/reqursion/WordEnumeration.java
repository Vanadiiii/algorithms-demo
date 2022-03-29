package me.imatveev.reqursion;

import java.util.LinkedList;
import java.util.List;

public class WordEnumeration {
    public List<String> enumerate(String word) {
        List<String> acc = new LinkedList<>();

        for (String shiftedWord : shift(word)) {
            String head = shiftedWord.substring(0, 1);
            String tail = shiftedWord.substring(1);

            enumerateHelper(head, tail, acc);
        }

        return acc;
    }

    private void enumerateHelper(String head, String tail, List<String> acc) {
        if (tail.length() <= 1) {
            acc.add(head + tail);
            return;
        }

        List<String> tails = shift(tail);
        for (String nextTail : tails) {
            String nextHead = head + nextTail.charAt(0);
            enumerateHelper(nextHead, nextTail.substring(1), acc);
        }
    }

    /**
     * @param word - any word
     * @return all shifts of this word
     */
    private List<String> shift(String word) {
        return shiftHelper(
                word,
                new LinkedList<>(),
                word.length()
        );
    }

    /**
     * @param word  - some word
     * @param acc   - results accumulator
     * @param count - count of recursive calls
     * @return acc
     */
    private List<String> shiftHelper(String word, List<String> acc, int count) {
        if (count == 0) {
            return acc;
        }
        acc.add(word);

        String head = word.substring(0, 1);
        String tail = word.substring(1);
        String newWord = tail + head;

        return shiftHelper(newWord, acc, --count);
    }
}
