package go_to_occurrence;

import com.intellij.openapi.editor.Caret;
import com.intellij.openapi.editor.Editor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class EditorController {

    private ActionsHandler.EditorControllerFinishedListener finishedListener;
    private EditorService editorService;
    private String enteredText = "";

    EditorController(ActionsHandler.EditorControllerFinishedListener finishedListener, EditorService editorService) {
        this.finishedListener = finishedListener;
        this.editorService = editorService;
    }

    public void init(@NotNull Editor editor) {
        editorService.fireTooltip(editor);
    }

    public void letterTyped(@NotNull Editor editor, char character) {
        System.out.println("KeyPressed: " + character);
        enteredText += character;
        onTextChanged(editor);
    }

    public void enterPressed(@NotNull Editor editor, @Nullable Caret caret) {
        editorService.moveCursorToSelection(editor);
        editorService.clearSelection(editor);
        finishedListener.actionPerformed();
    }

    public void escapePressed(@NotNull Editor editor, @Nullable Caret caret) {
        editorService.clearSelection(editor);
        finishedListener.actionPerformed();
    }

    public void backspacePressed(@NotNull Editor editor, @Nullable Caret caret) {
        System.out.println("Backspace pressed");
        if (enteredText.length() == 0) {
            finishedListener.actionPerformed();
            return;
        }
        enteredText = enteredText.substring(0, enteredText.length() - 1);
        onTextChanged(editor);
    }

    private void onTextChanged(@NotNull Editor editor) {
        editorService.fireTooltip(editor, enteredText);
        editorService.findAndSelect(enteredText, editor);
    }
}
