package part6;

abstract class Counter {
    public abstract int count(String phrase);
}
class UniqueCharCounter extends Counter {
        @Override
    public int count( String phrase) {
           String lower = phrase.toLowerCase();
            char[] symbol = lower.toCharArray();
            int cnt = phrase.length();
            for (int i = 0; i < symbol.length; i++){
                if (i != lower.indexOf(symbol[i])) {
                    cnt--;
                }
            }
        return cnt ;
    }
}
class FindChar extends Counter {

    @Override
    public int count(String phrase) {

        int cnt = 0;
        String s = "5";
        char[] symbol = phrase.toCharArray();
        for (int i = 0; i < symbol.length; i++) {
            if (s.equals(String.valueOf(symbol[i]))) {
                return cnt++;

            }
        }
        return cnt;
    }
}
class CharCounterTest {


    public static void main(String[] args) {
        UniqueCharCounter charCounter = new UniqueCharCounter();
        FindChar  findChar= new FindChar();
        //3
        System.out.println(charCounter.count("123"));

        //4
        System.out.println(charCounter.count("ab100"));

        //3
        System.out.println(charCounter.count("Java"));
        //2
        System.out.println(findChar.count("1120"));

    }
}












