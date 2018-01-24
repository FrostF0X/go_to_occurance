package go_to_occurrence;

import com.intellij.openapi.actionSystem.IdeActions;
import com.intellij.openapi.editor.actionSystem.EditorActionHandler;
import com.intellij.openapi.editor.actionSystem.EditorActionManager;
import com.intellij.openapi.editor.actionSystem.TypedAction;
import com.intellij.openapi.editor.actionSystem.TypedActionHandler;
import go_to_occurrence.handler.BackspaceActionHandler;
import go_to_occurrence.handler.EditorTypedActionHandler;
import go_to_occurrence.handler.EnterActionHandler;
import go_to_occurrence.handler.EscapeActionHandler;

public class ActionsManager {

    private TypedActionHandler typedActionHandler;
    private EditorActionHandler enterActionHandler;
    private EditorActionHandler escapeActionHandler;
    private EditorActionHandler backspaceActionHandler;
    private boolean memorizedDefaultHandlers;

    ActionsManager() {
        memorizedDefaultHandlers = false;
    }

    public void setHandlers(ActionsHandler.KeyboardListener listener) {
        memorizeDefaultHandlers();
        registerEditorTypedActionHandler(new EditorTypedActionHandler(listener));
        registerHandler(new EnterActionHandler(listener), IdeActions.ACTION_EDITOR_ENTER);
        registerHandler(new EscapeActionHandler(listener), IdeActions.ACTION_EDITOR_ESCAPE);
        registerHandler(new BackspaceActionHandler(listener), IdeActions.ACTION_EDITOR_BACKSPACE);
    }

    public void setDefaultHandlers() {
        registerEditorTypedActionHandler(typedActionHandler);
        registerHandler(enterActionHandler, IdeActions.ACTION_EDITOR_ENTER);
        registerHandler(escapeActionHandler, IdeActions.ACTION_EDITOR_ESCAPE);
        registerHandler(backspaceActionHandler, IdeActions.ACTION_EDITOR_BACKSPACE);
    }

    private void memorizeDefaultHandlers() {
        if (memorizedDefaultHandlers) {
            return;
        }

        final EditorActionManager actionManager = EditorActionManager.getInstance();
        final TypedAction typedAction = actionManager.getTypedAction();

        typedActionHandler = typedAction.getHandler();
        escapeActionHandler = actionManager.getActionHandler(IdeActions.ACTION_EDITOR_ESCAPE);
        enterActionHandler = actionManager.getActionHandler(IdeActions.ACTION_EDITOR_ENTER);
        backspaceActionHandler = actionManager.getActionHandler(IdeActions.ACTION_EDITOR_BACKSPACE);

        memorizedDefaultHandlers = true;
    }

    private void registerEditorTypedActionHandler(TypedActionHandler handler) {
        final EditorActionManager actionManager = EditorActionManager.getInstance();
        final TypedAction typedAction = actionManager.getTypedAction();
        typedAction.setupHandler(handler);
    }

    private void registerHandler(EditorActionHandler handler, String eventName) {
        final EditorActionManager actionManager = EditorActionManager.getInstance();
        actionManager.setActionHandler(eventName, handler);
    }

}



