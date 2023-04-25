package com.TheGreatChicken.PluginLang.utils;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class CharList implements List<Character> {
    private final String str;
    public CharList (String s) {
        str = s;
    }

    @Override
    public boolean add(Character arg0) { throw new UnsupportedOperationException("Unimplemented method 'add'");}
    @Override
    public void add(int arg0, Character arg1) { throw new UnsupportedOperationException("Unimplemented method 'add'");}
    @Override
    public boolean addAll(Collection<? extends Character> arg0) { throw new UnsupportedOperationException("Unimplemented method 'addAll'");}
    @Override
    public boolean addAll(int arg0, Collection<? extends Character> arg1) { throw new UnsupportedOperationException("Unimplemented method 'addAll'");}
    @Override
    public void clear() { throw new UnsupportedOperationException("Unimplemented method 'clear'");}
    @Override
    public boolean contains(Object arg0) { throw new UnsupportedOperationException("Unimplemented method 'contains'");}
    @Override
    public boolean containsAll(Collection<?> arg0) { throw new UnsupportedOperationException("Unimplemented method 'containsAll'");}
    @Override
    public int indexOf(Object arg0) { throw new UnsupportedOperationException("Unimplemented method 'indexOf'");}
    @Override
    public boolean isEmpty() { throw new UnsupportedOperationException("Unimplemented method 'isEmpty'");}
    @Override
    public Iterator<Character> iterator() { throw new UnsupportedOperationException("Unimplemented method 'iterator'");}
    @Override
    public int lastIndexOf(Object arg0) { throw new UnsupportedOperationException("Unimplemented method 'lastIndexOf'");}
    @Override
    public ListIterator<Character> listIterator() { throw new UnsupportedOperationException("Unimplemented method 'listIterator'");}
    @Override
    public ListIterator<Character> listIterator(int arg0) { throw new UnsupportedOperationException("Unimplemented method 'listIterator'");}
    @Override
    public boolean remove(Object arg0) { throw new UnsupportedOperationException("Unimplemented method 'remove'");}
    @Override
    public Character remove(int arg0) { throw new UnsupportedOperationException("Unimplemented method 'remove'");}
    @Override
    public boolean removeAll(Collection<?> arg0) { throw new UnsupportedOperationException("Unimplemented method 'removeAll'");}
    @Override
    public boolean retainAll(Collection<?> arg0) { throw new UnsupportedOperationException("Unimplemented method 'retainAll'");}
    @Override
    public Character set(int arg0, Character arg1) { throw new UnsupportedOperationException("Unimplemented method 'set'");}
    @Override
    public List<Character> subList(int arg0, int arg1) { throw new UnsupportedOperationException("Unimplemented method 'subList'");}
    @Override
    public Object[] toArray() { throw new UnsupportedOperationException("Unimplemented method 'toArray'");}
    @Override
    public <T> T[] toArray(T[] arg0) { throw new UnsupportedOperationException("Unimplemented method 'toArray'"); }

    @Override
    public int size() {
        return str.length();
    }
    @Override
    public Character get(int index) {
        return str.charAt(index);
    }
}