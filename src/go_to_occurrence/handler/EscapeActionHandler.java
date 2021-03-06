package go_to_occurrence.handler;

import com.intellij.codeInsight.editorActions.BaseEnterHandler;
import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.editor.Caret;
import com.intellij.openapi.editor.Editor;
import go_to_occurrence.ActionsHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class EscapeActionHandler extends BaseEnterHandler {


    private ActionsHandler.KeyboardListener listener;

    public EscapeActionHandler(ActionsHandler.KeyboardListener listener) {
        this.listener = listener;
    }

    @Override
    public void doExecute(@NotNull Editor editor, @Nullable Caret caret, DataContext dataContext) {
        listener.escapePressed(editor, caret, dataContext);
    }
}
