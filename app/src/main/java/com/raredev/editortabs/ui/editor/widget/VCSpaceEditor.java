package com.raredev.editortabs.ui.editor.widget;

import android.content.Context;
import android.util.AttributeSet;
import io.github.rosemoe.sora.widget.CodeEditor;
import java.io.File;

public class VCSpaceEditor extends CodeEditor {
    public static final String KEY_FILE = "editor_file";
    
    private File file;
    
    public VCSpaceEditor(Context context) {
        super(context);
    }
    
    public VCSpaceEditor(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    
    public void setFile(File file) {
        this.file = file;
    }
    
    public File getFile() {
        return file;
    }
}
