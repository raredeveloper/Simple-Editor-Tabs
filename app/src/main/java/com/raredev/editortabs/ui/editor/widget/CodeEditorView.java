package com.raredev.editortabs.ui.editor.widget;

import android.app.Activity;
import android.content.Context;
import android.widget.ViewFlipper;
import com.google.android.material.tabs.TabLayout;
import com.raredev.editortabs.ui.editor.model.EditorTabsModel;
import java.io.File;

public class CodeEditorView extends ViewFlipper {
    private Activity activity;
    
    private EditorTabsModel mModel;
    private TabLayout mTabLayout;
    
    public CodeEditorView(Context context, TabLayout mTabLayout) {
        super(context);
        this.mTabLayout = mTabLayout;
        mModel = new EditorTabsModel();
    }
    
    public EditorTabsModel getTabsModel() {
        return mModel;
    }
    
    public void openFile(File file) {
        if(mModel.containsFile(file)) {
            for(int i = 0; i < mModel.getSize(); i++) {
                if (file.getAbsolutePath().equals(mModel.getFilePath(i))) {
                    setCurrentPosition(i);
                }
            }
            return;
        }
        addTab(mModel.getSize(), file);
    }
    
    public void closeFile(int index) {
        File find = null;
        for (File file : mModel.getFileList()) {
            if (file.getAbsolutePath().equals(mModel.getFilePath(index))) {
                find = file;
            }
        }
        if(find != null) {
            removeView(getEditorAtIndex(index));
            mTabLayout.removeTabAt(index);
            mModel.remove(find);
        }
    }
    
    public void closeOthers(int index) {
        File find = null;
        for (File file : mModel.getFileList()) {
            if (file.getAbsolutePath().equals(mModel.getFilePath(index))) {
                find = file;
            }
        }
        if (find != null) {
            mTabLayout.removeAllTabs();
            removeAllViews();
            mModel.clear();
            openFile(find);
        }
    }
    
    public void closeAllFiles() {
        removeAllViews();
        mTabLayout.removeAllTabs();
        mModel.clear();
    }
    
    public VCSpaceEditor getEditorAtIndex(int index) {
        return (VCSpaceEditor)getChildAt(index);
    }
    
    private void addTab(int index, File file) {
        VCSpaceEditor editor = new VCSpaceEditor(getContext());
        addView(editor, index);
        
        mModel.addFile(index, file);
        mTabLayout.addTab(mTabLayout.newTab().setText(file.getName()));
        setCurrentPosition(index);
    }
    
    private void setCurrentPosition(int index) {
        final var tab = mTabLayout.getTabAt(index);
        if (tab != null && index >= 0 && !tab.isSelected()) {
            tab.select();
        }
    }
}