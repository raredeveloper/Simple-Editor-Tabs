package com.raredev.editortabs.ui.editor.widget;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import com.google.android.material.tabs.TabLayout;
import com.raredev.editortabs.databinding.LayoutEditorBinding;
import com.raredev.editortabs.ui.editor.model.EditorTabsModel;
import com.raredev.editortabs.ui.editor.widget.VCSpaceEditor;
import java.io.File;

public class CodeEditorView extends LinearLayout {
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
        if(!mModel.containsFile(file)) {
            addTab(mModel.getSize(), file);
        }
    }
    
    public VCSpaceEditor getEditorWithTag(Object tag) {
        return findViewWithTag(tag);
    }
    
    public void closeFile(int index) {
        try {
            removeView(getEditorWithTag(mModel.getFilePath(index)));
            mTabLayout.removeTabAt(index);
            mModel.remove(index);
        } catch(Exception e) {}
    }
    
    public void closeAllFiles() {
        try {
            removeAllViews();
            mTabLayout.removeAllTabs();
            mModel.clear();
        } catch(Exception e) {}
    }
    
    private void addTab(int index, File file) {
        VCSpaceEditor editor = new VCSpaceEditor(getContext());
        editor.setFile(file);
        editor.setTag(file.getAbsolutePath());
        addView(editor);
        
        mTabLayout.addTab(mTabLayout.newTab().setText(file.getName()));
        mModel.addFile(index, file);
    }
}
