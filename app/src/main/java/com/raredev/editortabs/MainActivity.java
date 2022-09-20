package com.raredev.editortabs;

import android.view.View;
import android.widget.PopupMenu;
import androidx.appcompat.app.AppCompatActivity;
import android.os.*;
import com.google.android.material.tabs.TabLayout;
import com.raredev.editortabs.databinding.ActivityMainBinding;
import com.itsaky.androidide.logsender.LogSender;
import com.raredev.editortabs.ui.editor.widget.CodeEditorView;
import java.io.File;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding mBinding;
    
    private CodeEditorView editor;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        LogSender.startLogging(this);
        super.onCreate(savedInstanceState);
        mBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setSupportActionBar(mBinding.toolbar);
        setContentView(mBinding.getRoot());
        
        mBinding.fab.setOnClickListener(v -> {
            addTab();
        });
        
        editor = new CodeEditorView(this, mBinding.editorTab);
        mBinding.editorContainer.addView(editor);
        configureTab();
    }
    
    private void configureTab() {
        mBinding.editorTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            int currentTab = -1;
            
            @Override
            public void onTabUnselected(TabLayout.Tab p1) {
                try {
                    //var edit = editor.getEditorWithTag(editor.getTabsModel().getFilePath(p1.getPosition()));
                    
                }catch(Exception e) {}
            }
            @Override
            public void onTabReselected(TabLayout.Tab p1) {
                showPopupMenu(p1.view, currentTab);
                mBinding.editorTab.requestFocus();
            }

            @Override
            public void onTabSelected(TabLayout.Tab p1) {
                currentTab = p1.getPosition();
                try {
                    editor.setDisplayedChild(currentTab);
                    var edit = editor.getEditorAtIndex(p1.getPosition());
                    Objects.requireNonNull(edit);
                    edit.requestFocus();
                } catch(Exception e) {}
            }
        });
    }
    
    private void addTab() {
        File file = new File("/storage/emulated/0/VCSpace/java/main.java");
        File file2 = new File("/storage/emulated/0/VCSpace/java/index.html");
        if(editor.getTabsModel().containsFile(file)) {
            editor.openFile(file2);
            return;
        }
        editor.openFile(file);
    }
    
    private void showPopupMenu(View v, int index) {
        PopupMenu pm = new PopupMenu(this, v);
        pm.getMenu().add("Close");
        pm.getMenu().add("Close All");
        pm.setOnMenuItemClickListener(item -> {
            if (item.getTitle() == "Close") {
                editor.closeFile(index);
            } else if (item.getTitle() == "Close All") {
                editor.closeAllFiles();
            }
            return true;
        });
        pm.show();
    }
}
