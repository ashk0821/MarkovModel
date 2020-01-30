package Unit5.bca.util;

public class BCAMapByArrayList<V> implements BCAMap<V>{
    protected BCAArrayList<BCAEntry<V>> arrayList = new BCAArrayList<>();

    @Override
    public int size() {
        return arrayList.listSize;
    }

    @Override
    public boolean isEmpty() {
        return (arrayList.listSize == 0);
    }

    @Override
    public boolean containsKey(String key) {
        if (key == null){
            throw new NullPointerException("The key is null.");
        }
        for (int i = 0; i < arrayList.listSize ; i++) {
            if (arrayList.get(i).key.equals(key)){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsValue(V value) {
        if (value == null){
            throw new NullPointerException("The value is null.");
        }
        for (int i = 0; i < arrayList.listSize; i++) {
            if (arrayList.get(i).value.equals(value)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public V get(String key) {
        V getVar = null;
        if (key == null){
            throw new NullPointerException("The key is null.");
        }
        for (int i = 0; i < arrayList.listSize; i++) {
            if (arrayList.get(i).key.equals(key)){
                getVar = arrayList.get(i).value;
                break;
            }
        }
        return getVar;
    }

    @Override
    public V getOrDefault(String key, V defaultValue) {
        if (key == null){
            throw new NullPointerException("The key is null.");
        }
        for (int i = 0; i < arrayList.listSize ; i++) {
            if (arrayList.get(i).key.equals(key)){
                return arrayList.get(i).value;
            }
        }
        return defaultValue;
    }

    @Override
    public V put(String key, V value) {
        V oldValue = null;
        boolean processFinished = false;

        if ((key == null) || (value == null)){
            throw new NullPointerException("The key or value is null.");
        }

        for (int i = 0; i < arrayList.listSize ; i++) {
            if (arrayList.get(i).key.equals(key)){
                oldValue = arrayList.get(i).value;
                arrayList.get(i).value = value;
                processFinished = true;
                break;
            }
        }
        if (processFinished){
            return oldValue;
        }
        else {
            BCAEntry<V> entry = new BCAEntry<>(key, value);
            arrayList.add(entry);
            return null;
        }
    }

    @Override
    public V remove(String key) {
        V oldValue = null;
        if (key == null){
            throw new NullPointerException("The key is null.");
        }
        for (int i = 0; i < arrayList.listSize ; i++) {
            if (arrayList.get(i).key.equals(key)){
                oldValue = arrayList.get(i).value;
                arrayList.remove(i);
            }
        }
        return oldValue;
    }

    @Override
    public void clear() {
        arrayList.clear();

    }

    @Override
    @SuppressWarnings("unchecked")
    public BCAEntry<V>[] toArray() {
        BCAEntry<V>[] bcaEntries = new BCAEntry[arrayList.listSize];
        for (int i = 0; i < arrayList.listSize ; i++) {
            bcaEntries[i] = arrayList.get(i);
        }
        return bcaEntries;
    }

    @Override
    public String[] keys() {
        String[] keysList = new String[arrayList.listSize];
        for (int i = 0; i < arrayList.listSize ; i++) {
            keysList[i] = arrayList.get(i).key;
        }
        return keysList;
    }

    @Override
    public BCAList<V> values() {
        BCAList<V> valuesList = new BCAArrayList<V>();
        for (int i = 0; i < arrayList.listSize; i++) {
            valuesList.add(arrayList.get(i).value);
        }
        return valuesList;
    }
}
