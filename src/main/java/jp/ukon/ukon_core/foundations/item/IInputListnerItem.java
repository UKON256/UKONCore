package jp.ukon.ukon_core.foundations.item;

import java.util.ArrayList;

public interface IInputListnerItem {
    void onInputDownTick(int key);
    void onInputUpTick(int key);
    void onInputDown(int key);
    void onInputUp(int key);
}
