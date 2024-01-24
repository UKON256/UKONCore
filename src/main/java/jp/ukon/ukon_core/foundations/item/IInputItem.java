package jp.ukon.ukon_core.foundations.item;

import java.util.ArrayList;

public interface IInputItem {
    public abstract ArrayList<int> getKeys();
    public void onInputDown(int key);
    public void onInputUp(int key);
    public void onInput(int key);
}
