package Buttons;

/**
 * Created by Alchemist on 2016/4/24.
 */
public class ComplexButton extends Button {
    private Button[] subButtons;

    public Button[] getSubButtons() {
        return subButtons;
    }

    public void setSubButtons(Button[] subButtons) {
        this.subButtons = subButtons;
    }
}
