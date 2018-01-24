package go_to_occurrence;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;

public class Action extends AnAction {

    private ActionsHandler controller;

    public Action() {
        super();
        controller = new ActionsHandler();
    }

    @Override
    public void actionPerformed(AnActionEvent event) {
        final Project project = event.getData(CommonDataKeys.PROJECT);
        final Editor editor = event.getData(CommonDataKeys.EDITOR);

        if (project == null || editor == null) {
            return;
        }

        controller.init(editor);
    }


}
