package com.example.epsflashcardpro;

public class FlashcardItem {
    public final int chapter;
    public final String korean;
    public final String indonesian;

    public FlashcardItem(int chapter, String korean, String indonesian) {
        this.chapter = chapter;
        this.korean = korean;
        this.indonesian = indonesian;
    }

    public String key() {
        return chapter + "|" + korean;
    }
}
