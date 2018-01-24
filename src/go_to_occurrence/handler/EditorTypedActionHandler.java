package go_to_occurrence.handler;

import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.editor.Editor;
import go_to_occurrence.ActionsHandler;
import org.jetbrains.annotations.NotNull;

public class EditorTypedActionHandler implements com.intellij.openapi.editor.actionSystem.TypedActionHandler {


    private ActionsHandler.KeyboardListener listener;

    public EditorTypedActionHandler(ActionsHandler.KeyboardListener listener) {
        this.listener = listener;
    }

    @Override
    public void execute(@NotNull Editor editor, char character, @NotNull DataContext dataContext) {
        listener.letterTyped(editor, character, dataContext);
    }
}