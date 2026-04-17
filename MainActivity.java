package com.example.epsflashcardpro;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private TextView chapterBadge, frontText, backText, progressText, quizQuestionText, scoreText, highScoreText;
    private MaterialCardView cardView;
    private MaterialButton optionA, optionB, optionC, optionD, favoriteButton, flashModeButton, quizModeButton;
    private View quizSection, flashControls;
    private List<FlashcardItem> currentItems = new ArrayList<>();
    private int currentIndex = 0;
    private boolean showingBack = false;
    private int currentChapter = 1;
    private int score = 0;
    private int highScore = 0;
    private boolean quizMode = false;
    private FlashcardItem quizItem;
    private TextToSpeech tts;
    private final Random random = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindViews();
        initTts();
        setupButtons();
        highScore = Prefs.getHighScore(this);
        updateHighScoreLabel();
        loadChapter(1);
        generateQuiz();
        setQuizMode(false);
    }

    private void bindViews() {
        chapterBadge = findViewById(R.id.chapterBadge);
        frontText = findViewById(R.id.frontText);
        backText = findViewById(R.id.backText);
        progressText = findViewById(R.id.progressText);
        quizQuestionText = findViewById(R.id.quizQuestionText);
        scoreText = findViewById(R.id.scoreText);
        highScoreText = findViewById(R.id.highScoreText);
        cardView = findViewById(R.id.cardView);
        quizSection = findViewById(R.id.quizSection);
        flashControls = findViewById(R.id.flashControls);

        optionA = findViewById(R.id.optionA);
        optionB = findViewById(R.id.optionB);
        optionC = findViewById(R.id.optionC);
        optionD = findViewById(R.id.optionD);
        favoriteButton = findViewById(R.id.favoriteButton);
        flashModeButton = findViewById(R.id.flashModeButton);
        quizModeButton = findViewById(R.id.quizModeButton);
    }

    private void initTts() {
        tts = new TextToSpeech(this, status -> {
            if (status == TextToSpeech.SUCCESS) {
                int result = tts.setLanguage(Locale.KOREAN);
                if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                    Toast.makeText(this, "Data suara Korea belum tersedia di perangkat ini.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void setupButtons() {
        cardView.setOnClickListener(v -> {
            if (!quizMode) flipCard();
        });

        findViewById(R.id.prevButton).setOnClickListener(v -> {
            if (currentItems.isEmpty()) return;
            currentIndex = (currentIndex - 1 + currentItems.size()) % currentItems.size();
            showingBack = false;
            updateCard();
        });

        findViewById(R.id.nextButton).setOnClickListener(v -> {
            if (currentItems.isEmpty()) return;
            currentIndex = (currentIndex + 1) % currentItems.size();
            showingBack = false;
            updateCard();
        });

        findViewById(R.id.speakButton).setOnClickListener(v -> speakCurrent());
        favoriteButton.setOnClickListener(v -> toggleFavorite());

        findViewById(R.id.ch1Button).setOnClickListener(v -> loadChapter(1));
        findViewById(R.id.ch2Button).setOnClickListener(v -> loadChapter(2));
        findViewById(R.id.ch3Button).setOnClickListener(v -> loadChapter(3));
        findViewById(R.id.ch4Button).setOnClickListener(v -> loadChapter(4));

        flashModeButton.setOnClickListener(v -> setQuizMode(false));
        quizModeButton.setOnClickListener(v -> setQuizMode(true));

        optionA.setOnClickListener(v -> checkAnswer(optionA.getText().toString()));
        optionB.setOnClickListener(v -> checkAnswer(optionB.getText().toString()));
        optionC.setOnClickListener(v -> checkAnswer(optionC.getText().toString()));
        optionD.setOnClickListener(v -> checkAnswer(optionD.getText().toString()));
    }

    private void setQuizMode(boolean enabled) {
        quizMode = enabled;
        quizSection.setVisibility(enabled ? View.VISIBLE : View.GONE);
        flashControls.setVisibility(enabled ? View.GONE : View.VISIBLE);
        flashModeButton.setEnabled(enabled);
        quizModeButton.setEnabled(!enabled);
        if (enabled) {
            generateQuiz();
        } else {
            updateCard();
        }
    }

    private void loadChapter(int chapter) {
        currentChapter = chapter;
        currentItems = DataProvider.getByChapter(chapter);
        Collections.shuffle(currentItems);
        currentIndex = 0;
        showingBack = false;
        chapterBadge.setText("Bab " + chapter + " • " + currentItems.size() + " kata");
        updateCard();
        generateQuiz();
    }

    private void updateCard() {
        if (currentItems.isEmpty()) return;
        FlashcardItem item = currentItems.get(currentIndex);
        frontText.setText(item.korean);
        backText.setText(item.indonesian);
        backText.setVisibility(showingBack ? View.VISIBLE : View.GONE);
        progressText.setText((currentIndex + 1) + "/" + currentItems.size());
        favoriteButton.setText(Prefs.isFavorite(this, item) ? "Favorit ✓" : "Favorit");
    }

    private void flipCard() {
        showingBack = !showingBack;
        updateCard();
    }

    private void speakCurrent() {
        if (currentItems.isEmpty() || tts == null) return;
        FlashcardItem item = currentItems.get(currentIndex);
        tts.speak(item.korean, TextToSpeech.QUEUE_FLUSH, null, "eps_word");
    }

    private void toggleFavorite() {
        if (currentItems.isEmpty()) return;
        FlashcardItem item = currentItems.get(currentIndex);
        Prefs.toggleFavorite(this, item);
        updateCard();
    }

    private void generateQuiz() {
        if (currentItems.isEmpty()) return;
        List<FlashcardItem> pool = new ArrayList<>(currentItems);
        if (pool.size() < 4) return;

        quizItem = pool.get(random.nextInt(pool.size()));
        quizQuestionText.setText("Arti dari “ + quizItem.korean + "” adalah...");

        List<String> options = new ArrayList<>();
        options.add(quizItem.indonesian);
        while (options.size() < 4) {
            String candidate = pool.get(random.nextInt(pool.size())).indonesian;
            if (!options.contains(candidate)) options.add(candidate);
        }
        Collections.shuffle(options);
        optionA.setText(options.get(0));
        optionB.setText(options.get(1));
        optionC.setText(options.get(2));
        optionD.setText(options.get(3));
        scoreText.setText("Skor " + score);
    }

    private void checkAnswer(String selected) {
        if (quizItem == null) return;
        if (selected.equals(quizItem.indonesian)) {
            score++;
            Toast.makeText(this, "Benar!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Salah. Jawaban: " + quizItem.indonesian, Toast.LENGTH_SHORT).show();
        }
        Prefs.updateHighScore(this, score);
        highScore = Prefs.getHighScore(this);
        updateHighScoreLabel();
        generateQuiz();
    }

    private void updateHighScoreLabel() {
        highScoreText.setText("High " + highScore);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
    }
}
