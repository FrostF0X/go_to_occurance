package go_to_occurrence;

import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.editor.Caret;
import com.intellij.openapi.editor.Editor;
import go_to_occurrence.generics.Listener;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ActionsHandler {

    private ActionsManager actionManager;

    private boolean inAction;

    ActionsHandler() {
        this.actionManager = new ActionsManager();
        this.inAction = false;
    }


    public void init(@NotNull Editor editor) {
        if (inAction) {
            return;
        }
        inAction = true;

        EditorController controller = new EditorController(
                new EditorControllerFinishedListener(this),
                new EditorService()
        );
        KeyboardListener listener = new KeyboardListener(controller);
        actionManager.setHandlers(listener);

        controller.init(editor);
    }

    private void uninit() {
        actionManager.setDefaultHandlers();
        inAction = false;
    }

    public class KeyboardListener {

        private EditorController controller;

        KeyboardListener(EditorController controller) {
            this.controller = controller;
        }

        public void letterTyped(@NotNull Editor editor, char character, @NotNull DataContext dataContext) {
            controller.letterTyped(editor, character);
        }

        public void enterPressed(@NotNull Editor editor, @Nullable Caret caret, DataContext dataContext) {
            controller.enterPressed(editor, caret);
        }

        public void escapePressed(@NotNull Editor editor, @Nullable Caret caret, DataContext dataContext) {
            controller.escapePressed(editor, caret);
        }

        public void backspacePressed(@NotNull Editor editor, @Nullable Caret caret, DataContext dataContext) {
            controller.backspacePressed(editor, caret);
        }
    }

    public class EditorControllerFinishedListener implements Listener {

        private ActionsHandler actionsHandler;

        EditorControllerFinishedListener(ActionsHandler actionsHandler) {
            this.actionsHandler = actionsHandler;
        }

        public void actionPerformed() {
            actionsHandler.uninit();
        }

    }
}
