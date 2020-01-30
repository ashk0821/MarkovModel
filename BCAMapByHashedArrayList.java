package Unit5.bca.util;

public class BCAMapByHashedArrayList<V> implements BCAMap<V> {
    protected BCAArrayList<BCAEntry<V>> buckets[];

    public BCAMapByHashedArrayList(int numBuckets) {
        buckets = new BCAArrayList[numBuckets];

        for (int i=0; i<buckets.length;i++ ) {
            buckets[i] = new BCAArrayList<BCAEntry<V>>();
        }
    }

    @Override
    public boolean containsKey(String key) {
        if (getOrDefault(key, null) != null)
            return true;
        else
            return false;
    }

    @Override
    public boolean containsValue(V value) {
        if(value == null) {
            throw new NullPointerException ("value cannot be null!");
        }

        for (int i=0; i<buckets.length; i++) {
            BCAArrayList<BCAEntry<V>> list = buckets[i];

            for (int j=0; j<list.listSize; j++) {
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
            throw new NullPointerException ("key cannot be null!");
        }

        int bucket = Math.abs(key.hashCode()) % buckets.length;
        BCAArrayList<BCAEntry<V>> list = buckets[bucket];

        for (int i=0; i<list.listSize; i++) {
            if (list.get(i).key.equals(key))
                return (list.get(i)).value;
        }

        return defaultValue;
    }

    @Override
    public V put(String key, V value) {
        if(key == null) {
            throw new NullPointerException ("key cannot be null!");
        }
        if(value == null) {
            throw new NullPointerException ("value cannot be null!");
        }

        int bucket = Math.abs(key.hashCode()) % buckets.length;
        BCAArrayList<BCAEntry<V>> list = buckets[bucket];

        for (int i=0; i<list.listSize; i++) {
            if (list.get(i).key.equals(key)) {
                V oldVal = list.get(i).value;
                list.get(i).value = value;
                return oldVal;
            }
        }

        list.add (new BCAEntry<V> (key, value));

        return null;
    }

    @Override
    public V remove(String key) {
        if(key == null) {
            throw new NullPointerException ("key cannot be null!");
        }

        int bucket = Math.abs(key.hashCode()) % buckets.length;
        BCAArrayList<BCAEntry<V>> list = buckets[bucket];

        for (int i=0; i<list.listSize; i++) {
            if ((list.get(i)).key.equals(key)) {
                V o = (list.get(i)).value;
                list.remove(i);
                return o;
            }
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
        for (int i=0; i<buckets.length; i++) {
            if (buckets[i].isEmpty() == false) {
                return false;
            }
        }
        return true;
    }

    public int size() {
        int size = 0;

        for (int i=0; i<buckets.length; i++) {
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
