package com.raredev.editortabs.ui.editor.model;

import java.io.File;
import java.util.LinkedList;

public class EditorTabsModel {
    private final LinkedList<File> mFiles = new LinkedList<>();
    
    public void addFile(int index, File file) {
        mFiles.add(index, file);
    }
    
    public LinkedList<File> getFileList() {
        return mFiles;
    }
    
    public int getSize() {
        return mFiles.size();
    }
    
    public File getFile(int index) {
        return mFiles.get(index);
    }
    
    public String getFilePath(int index) {
        return mFiles.get(index).getAbsolutePath();
    }
    
    public void remove(Object index) {
        mFiles.remove(index);
    }
    
    public void clear() {
        mFiles.clear();
    }
    
    public boolean containsFile(File file) {
        return mFiles.contains(file);
    }
}
