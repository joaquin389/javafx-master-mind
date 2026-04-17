package com.example.javafxmastermindcontrole;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.IOException;

public class DialogueController {

    @FXML
    private Label labelDialogue;

    private String[] dialogues;
    private int indexDialogue;
    private Timeline timeline;
    private String texteCourant;
    private int indexChar;
    private boolean estJeuFini;

    @FXML
    public void initialize() {
        estJeuFini = false;
        dialogues = new String[]{
                "Mec... me regarde pas comme ça, mais je crois que j'ai un gros souci. Tu vois le bruit de tic-tac depuis le début du cours ? Bah c'est pas ma montre. C’est moi. Je suis littéralement devenu une bombe humaine.",
                "Ne t'approche pas trop, je voudrais pas que tu finisses en confettis avant d'avoir fini ton DM. Je suis coincé avec ce truc sur le buffet, et si je stresse trop, le compteur s'affole. C'est à toi de résoudre les énigmes pour trouver le code de désactivation.",
                "Je sais que d'habitude tu galères à trouver la réponse à \"2+2\", mais là, si tu te plantes, je vais repeindre les murs de la ville avec mon propre t-shirt. Pas de pression, hein, mais dépêche-toi !",
                "Allez, trouve les indices, déchiffre les codes, et sors-moi de là. Si tu réussis, je te jure que je te laisse copier sur moi jusqu'à la fin de l'année... si je survis."
        };
        indexDialogue = 0;
        afficherTexteAnime();
    }

    public void setModeFin(boolean victoire) {
        estJeuFini = true;
        if (timeline != null) timeline.stop();

        if (victoire) {
            dialogues = new String[]{
                    "Tu l'as fait... Tu as réussi à désamorcer la bombe et à sauver la ville. Je savais que tu en étais capable.",
                    "Bien joué, vraiment. Tu as prouvé qu'il n'y a rien que tu ne puisses accomplir. Je n'oublierai jamais ce jour."
            };
        } else {
            dialogues = new String[]{
                    "Ok, t'es là ! Frérot, je vibre de partout, je te jure c'est pas une blague. Le compteur est à 10 secondes et j'ai une soudaine envie de dire que c'est moi qui ai rayé ta voiture l'été dernier !",
                    "Vite ! Le code ! Taper le code ! C'est le moment de prouver que t'as pas que du vent entre les oreilles.",
                    "Attend... pourquoi tu tapes \"1234\" ? C'est pas ça du tout ! Mais qu'est-ce que tu fai— Non ! Pas le bouton roug—",
                    "... Super. Franchement, t'es un génie. Je suis actuellement éparpillé sur trois quartiers différents. Je vais être en retard en cours, et c'est 100% de ta faute. Bravo l'artiste !"
            };
        }
        indexDialogue = 0;
        afficherTexteAnime();
    }

    private void afficherTexteAnime() {
        if (timeline != null) timeline.stop();
        texteCourant = dialogues[indexDialogue];
        labelDialogue.setText("");
        indexChar = 0;

        timeline = new Timeline(new KeyFrame(Duration.millis(40), e -> {
            if (indexChar < texteCourant.length()) {
                labelDialogue.setText(labelDialogue.getText() + texteCourant.charAt(indexChar));
                indexChar++;
            }
        }));
        timeline.setCycleCount(texteCourant.length());
        timeline.play();
    }

    @FXML
    public void dialogueSuivant() {
        try {
            if (timeline != null && timeline.getStatus() == Timeline.Status.RUNNING) {
                timeline.stop();
                labelDialogue.setText(texteCourant);
            } else {
                indexDialogue++;
                if (indexDialogue < dialogues.length) {
                    afficherTexteAnime();
                } else if (!estJeuFini) {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("mastermind.fxml"));
                    Parent root = loader.load();
                    Stage stage = (Stage) labelDialogue.getScene().getWindow();
                    stage.setScene(new Scene(root, 600, 500));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}