package Unit5.bca.util;

public class BCAMapByHashedLinkedList<V> implements BCAMap<V>{
    protected BCALinkedList<BCAEntry<V>> buckets[] = null;

    public BCAMapByHashedLinkedList(int numBuckets) {
        buckets = new BCALinkedList[numBuckets];

        for (int i = 0; i< buckets.length; i++ ) {
            buckets[i] = new BCALinkedList<BCAEntry<V>>();
        }
    }

    @Override
    public boolean containsKey(String key) {
        if (getOrDefault(key, null) != null) {
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public boolean containsValue(V value) {
        if(value == null) {
            throw new NullPointerException();
        }

        for (int i = 0; i< buckets.length; i++) {
            BCALinkedList<BCAEntry<V>> list = buckets[i];
            for (int j = 0; j < list.listSize; j++) {
                if (list.get(j).value.equals(value))
                    return true;
            }
        }
        return false;
    }

    @Override
    public V get(String key) {
        return getOrDefault(key, null);
    }

    @Override
    public V getOrDefault(String key, V defaultValue) {
        if(key == null) {
            throw new NullPointerException("Key cannot be null!");
        }

        int bucket = Math.abs(key.hashCode()) % buckets.length;
        BCALinkedList<BCAEntry<V>> list = buckets[bucket];
        BCALinkedList<BCAEntry<V>>.Node node = list.head;

        for (int i = 0; i < list.listSize; i++) {
            if (node.data.key.equals(key)){
                return node.data.value;
            }
            node = node.next;
        }

        return defaultValue;
    }

    @Override
    public V put(String key, V value) {
        if(key == null) {
            throw new NullPointerException("Key cannot be null!");
        }

        if(value == null) {
            throw new NullPointerException("Value cannot be null!");
        }

        int bucket = Math.abs(key.hashCode()) % buckets.length;
        BCALinkedList<BCAEntry<V>> list = buckets[bucket];
        BCALinkedList<BCAEntry<V>>.Node node = list.head;

        for (int i = 0; i < list.listSize; i++) {
            if (node.data.key.equals(key)) {
                V temp= node.data.value;
                node.data.value = value;
                return temp;
            }
            node = node.next;
        }

        list.add (new BCAEntry<V> (key, value));

        return null;
    }

    @Override
    public V remove(String key) {
        if(key == null) {
            throw new NullPointerException();
        }

        int bucket = Math.abs(key.hashCode()) % buckets.length;
        BCALinkedList<BCAEntry<V>> list = buckets[bucket];
        BCALinkedList<BCAEntry<V>>.Node node = list.head;

        for (int i=0; i<list.listSize; i++) {
            if (node.data.key.equals(key)) {
                V o = node.data.value;
                list.remove(i);
                return o;
            }
            node = node.next;
        }

        return null;
    }

    @Override
    public void clear() {
        for (int i=0; i<buckets.length; i++) {
            buckets[i].clear();
        }
    }

    public boolean isEmpty() {
        for (int i = 0; i< buckets.length; i++) {
            if (!buckets[i].isEmpty()) {
                return false;
            }
        }
        return true;
    }

    public int size() {
        int size = 0;

        for (int i = 0; i< buckets.length; i++) {
            size += buckets[i].size();
        }
        return size;
    }

    public BCAEntry<V>[] toArray()
    {
        BCAEntry<V>[] entryList = new BCAEntry[size()];
        int index = 0;
        for (int i=0; i<buckets.length; i++) {
            for (int j=0; j<buckets[i].listSize; j++) {
                BCAEntry<V> e = buckets[i].get(j);
                entryList[index++] = new BCAEntry<V> (e.key, e.value);
            }
        }
        return entryList;
    }

    /**
     * Returns an array containing the keys in the map.
     */
    public String[] keys()
    {
        String[] keys = new String[size()];
        int index = 0;
        for (int i=0; i<buckets.length; i++) {
            for (int j=0; j<buckets[i].listSize; j++) {
                keys[index++] = (buckets[i].get(j)).key;
            }
        }
        return keys;
    }

    /**
     * Returns an array containing the values in the map.
     */
    public BCAList<V> values() {
        BCAList<V> values = new BCAArrayList<V>();
        int index = 0;
        for (int i=0; i<buckets.length; i++) {
            for (int j=0; j<buckets[i].listSize; j++) {
                values.add(buckets[i].get(j).value);
            }
        }
        return values;
    }
}
