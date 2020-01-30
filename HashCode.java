package Unit5;

import Unit5.bca.util.BCAEntry;

public class HashCode {
    public static void main(String[] args) {
        String s = "I love Chris so much oh my god he is so cute";
        Integer x = 1234567890;
        BCAEntry entry = new BCAEntry(s, x);

        System.out.println(s.hashCode());
        System.out.println(x.hashCode());
        System.out.println(entry.hashCode());
    }
}
