package com.raredev.editortabs.ui.editor.widget;

import android.app.Activity;
import android.content.Context;
import android.widget.ViewFlipper;
import com.google.android.material.tabs.TabLayout;
import com.raredev.editortabs.ui.editor.model.EditorTabsModel;
import java.io.File;

public class CodeEditorView extends ViewFlipper {
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
                final var tab = mTabLayout.getTabAt(i);
                if (file.getAbsolutePath() == mModel.getFilePath(i) && tab != null && i >= 0 && !tab.isSelected()) {
                    tab.select();
                }
            }
            return;
        }
        addTab(mModel.getSize(), file);
    }
    
    public void closeFile(int index) {
        try {
            removeView(getEditorAtIndex(index));
            mTabLayout.removeTabAt(index);
            mModel.remove(index);
        } catch(Exception e) {}
    }
    
    public void closeOthers(int index) {
        for(int i = 0; i < mModel.getSize(); i++) {
            if(index != i) {
                closeFile(i);
            }
        }
    }
    
    public void closeAllFiles() {
        try {
            removeAllViews();
            mTabLayout.removeAllTabs();
            mModel.clear();
        } catch(Exception e) {}
    }
    
    public VCSpaceEditor getEditorAtIndex(int index) {
        return (VCSpaceEditor)getChildAt(index);
    }
    
    private void addTab(int index, File file) {
        VCSpaceEditor editor = new VCSpaceEditor(getContext());
        addView(editor, index);
        
        mTabLayout.addTab(mTabLayout.newTab().setText(file.getName()));
        
        final var tab = mTabLayout.getTabAt(index);
        if (tab != null && index >= 0 && !tab.isSelected()) {
            tab.select();
        }
        
        mModel.addFile(index, file);
    }
}