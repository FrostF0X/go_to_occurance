package go_to_occurrence;

import com.intellij.ide.IdeTooltip;
import com.intellij.ide.IdeTooltipManager;
import com.intellij.openapi.editor.CaretModel;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.SelectionModel;
import com.intellij.openapi.ui.popup.Balloon;
import go_to_occurrence.generics.StringSearchUtilities;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;

public class EditorService {

    public void findAndSelect(String needle, @NotNull Editor editor) {
        final Document document = editor.getDocument();

        CaretModel caret = editor.getCaretModel();

        String documentText = document.getText();
        int occurrenceIndex = StringSearchUtilities.findClosestOccurrence(documentText, needle, caret.getOffset());

        if (occurrenceIndex == -1) {
            clearSelection(editor);
            return;
        }

        this.setSelection(occurrenceIndex, occurrenceIndex + needle.length(), editor);
    }

    public void clearSelection(@NotNull Editor editor) {
        SelectionModel selectionModel = editor.getSelectionModel();
        selectionModel.removeSelection();
    }

    private void setSelection(int startIndex, int endIndex, @NotNull Editor editor) {
        SelectionModel selectionModel = editor.getSelectionModel();
        selectionModel.setSelection(startIndex, endIndex);
    }

    public void moveCursorToSelection(@NotNull Editor editor) {
        SelectionModel selectionModel = editor.getSelectionModel();
        editor.getCaretModel().moveToOffset(selectionModel.getSelectionStart());
    }

    public void fireTooltip(@NotNull Editor editor) {
        fireTooltip(editor, "");
    }

    public void fireTooltip(@NotNull Editor editor, String text) {
        if (text.length() == 0) {
            text = " ";
        }

        CaretModel caretModel = editor.getCaretModel();
        Point point = editor.logicalPositionToXY(caretModel.getLogicalPosition());

        IdeTooltip tooltip = new IdeTooltip(editor.getContentComponent(), point, new JLabel(text))
                .setPreferredPosition(Balloon.Position.above);
        IdeTooltipManager.getInstance().show(tooltip, true, false);
    }

}
