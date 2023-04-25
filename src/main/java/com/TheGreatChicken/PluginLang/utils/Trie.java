package com.TheGreatChicken.PluginLang.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

public class Trie<K, V> {
    private class TrieNode {
        V value = null;
        HashMap<K, TrieNode> next = new HashMap<>();

        public void append ( List<K> key, int offset, V value ) {
            if (offset == key.size()) {
                this.value = value;
                return ;
            }

            if (!next.containsKey(key.get(offset)))
                next.put(key.get(offset), new TrieNode());
            
            next.get(key.get(offset)).append(key, offset + 1, value);
        }
        public TrieNode query (List<K> key, int offset) {
            if (key.size() == offset) return this;

            if (next.containsKey(key.get(offset)))
                return next.get(key.get(offset)).query(key, offset + 1);
            return null;
        }
        public V collision (List<K> key, int offset) {
            if (key.size() == offset) return this.value;

            V val0 = this.value;
            V val1 = null;
            if (next.containsKey(key.get(offset)))
                val1 = next.get(key.get(offset)).collision(key, offset + 1);
        
            if (val1 == null) return val0;
            return val1;
        }

        public String toString () {
            String str = (value == null ? "NULL" : value.toString()) + " ---";
            
            for (Entry<K, TrieNode> el : next.entrySet()) {
                str += "\n";

                String str2 = "K--- " + el.getKey().toString() + ":";
                String str3 = el.getValue().toString();
                String str4 = String.join("\n|", str3.split("\n"));
                
                if (el.getValue().next.size() == 0)
                    str += "K--- " + el.getKey().toString() + ":" + str3;
                else
                    str += str2 + str4;
            }

            return str;
        }
    }

    TrieNode root = new TrieNode();

    /**
     * Puts the value inside the trie
     * @param key
     * @param value
     */
    public void put (List<K> key, V value) {
        root.append(key, 0, value);
    }
    /**
     * Gets the value inside the trie
     */
    public V get (List<K> key) {
        TrieNode node = root.query(key, 0);

        if (node == null) return null;
        return node.value;
    }
    /**
     * Gets the deepest value on path inside the trie, with an offset on the key
     * @param key
     * @param offset
     * @return
     */
    public V collision (List<K> key, int offset) {
        return root.collision(key, offset);
    }
    /**
     * Gets the deepest value on path inside the trie
     * @param key
     * @return
     */
    public V collision (List<K> key) {
        return collision(key, 0);
    }

    public String toString () {
        return root.toString();
    }
    public void show () {
        System.out.println(this.toString());
    }
}
