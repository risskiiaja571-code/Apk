package com.example.epsflashcardpro;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashSet;
import java.util.Set;

public class Prefs {
    private static final String PREF = "eps_flashcard_pref";
    private static final String FAVORITES = "favorites";
    private static final String HIGH_SCORE = "high_score";

    public static Set<String> getFavorites(Context context) {
        SharedPreferences sp = context.getSharedPreferences(PREF, Context.MODE_PRIVATE);
        return new HashSet<>(sp.getStringSet(FAVORITES, new HashSet<>()));
    }

    public static boolean isFavorite(Context context, FlashcardItem item) {
        return getFavorites(context).contains(item.key());
    }

    public static void toggleFavorite(Context context, FlashcardItem item) {
        SharedPreferences sp = context.getSharedPreferences(PREF, Context.MODE_PRIVATE);
        Set<String> set = new HashSet<>(sp.getStringSet(FAVORITES, new HashSet<>()));
        if (set.contains(item.key())) {
            set.remove(item.key());
        } else {
            set.add(item.key());
        }
        sp.edit().putStringSet(FAVORITES, set).apply();
    }

    public static int getHighScore(Context context) {
        SharedPreferences sp = context.getSharedPreferences(PREF, Context.MODE_PRIVATE);
        return sp.getInt(HIGH_SCORE, 0);
    }

    public static void updateHighScore(Context context, int score) {
        if (score > getHighScore(context)) {
            context.getSharedPreferences(PREF, Context.MODE_PRIVATE)
                    .edit()
                    .putInt(HIGH_SCORE, score)
                    .apply();
        }
    }
}
