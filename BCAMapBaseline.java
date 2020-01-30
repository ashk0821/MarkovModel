package Unit5.bca.util;

import java.util.HashMap;
import java.util.Map;

public class BCAMapBaseline<V> implements BCAMap<V> {
    protected Map<String, V> m = new HashMap<String, V>();

    @Override
    public int size() {
        return m.size();
    }

    @Override
    public boolean isEmpty() {
        return m.isEmpty();
    }

    @Override
    public boolean containsKey(String key) {
        return m.containsKey(key);
    }

    @Override
    public boolean containsValue(V value) {
        return m.containsValue(value);
    }

    @Override
    public V get(String key) {
        return m.get(key);
    }

    @Override
    public V getOrDefault(String key, V defaultValue) {
        return m.getOrDefault(key, defaultValue);
    }

    @Override
    public V put(String key, V value) {
        if (key == null || value == null)
            throw new NullPointerException();
        return m.put(key, value);
    }

    @Override
    public V remove(String key) {
        return m.remove(key);
    }

    @Override
    public void clear() {
        m.clear();
    }

    @Override
    @SuppressWarnings("unchecked")
    public BCAEntry<V>[] toArray() {
      //This is a funky line - can't make arrays of
      //parameterized types (that is, with generics)
      BCAEntry<V>[] entries = new BCAEntry[size()];

      int i = 0;
      for (Map.Entry<String,V> e : m.entrySet()) {
          entries[i++] = new BCAEntry<V>(e.getKey(), e.getValue());
      }
      return entries;
    }

    /**
     * Returns an array containing the keys in the map.
     */
    @Override
    public String[] keys() {
        String[] array = new String[m.size()];
        m.keySet().toArray(array);
        return array;
    }

    /**
     * Returns a BCAList containing the values in the map.
     * (This is because generic arrays are a big pain in Java)
     */
    @Override
    public BCAList<V> values() {
      BCAList<V> list = new BCAArrayList<V>();
      for(V value : m.values())
          list.add(value);
      return list;
    }
}
