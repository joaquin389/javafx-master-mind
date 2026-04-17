package com.example.javafxmastermindcontrole;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.IOException;

public class DialogueController {

    @FXML
    private Label labelDialogue;

    @FXML
    private ImageView imageChef;

    private String[] dialogues;
    private String[] imagesActuelles;
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
                "Ne t'approche pas trop, je voudrais pas que tu finisses en confettis avant la cuite de demain. Je suis coincé avec ce truc, et si je stresse trop, le compteur s'affole. C'est à toi de résoudre les énigmes pour trouver le code de désactivation.",
                "Je sais que d'habitude tu galères à trouver la réponse à \"2+2\", mais là, si tu te plantes, je vais repeindre les murs de la classe avec mon propre t-shirt. Pas de pression, hein, mais dépêche-toi !",
                "Allez, trouve les indices, déchiffre les codes, et sors-moi de là. Si tu réussis, je te jure que je te laisse copier sur moi jusqu'à la fin de l'année... si je survis."
        };
        imagesActuelles = new String[]{
                "premier-chef.png",
                "deuxieme-chef.png",
                "troisieme-chef.png",
                "quatrieme-chef.png"
        };
        indexDialogue = 0;
        afficherTexteAnime();
    }

    public void setModeFin(boolean victoire) {
        estJeuFini = true;
        if (timeline != null) timeline.stop();

        if (victoire) {
            dialogues = new String[]{
                    "Tu l'as fait... Tu as réussi à désamorcer la bombe et à sauver la classe. Je savais que tu en étais capable.",
                    "Bien joué, vraiment. Tu as prouvé qu'il n'y a rien que tu ne puisses accomplir. Je n'oublierai jamais ce jour."
            };
            imagesActuelles = new String[]{
                    "neuvieme-chef.png",
                    "neuvieme-chef.png"
            };
        } else {
            dialogues = new String[]{
                    "Ok, t'es là ! Frérot, je vibre de partout, je te jure c'est pas une blague. Le compteur est à 10 secondes",
                    "Vite ! Le code ! Taper le code ! C'est le moment de prouver que t'as pas que du vent entre les oreilles.",
                    "Attend... pourquoi tu tapes \"1234\" ? C'est pas ça du tout ! Mais qu'est-ce que tu fai— Non ! Pas le bouton roug—",
                    "... Super. Franchement, t'es un génie. Je suis actuellement mort. Et c'est 100% de ta faute. Bravo l'artiste !"
            };
            imagesActuelles = new String[]{
                    "cinquieme-chef.png",
                    "cinquieme-chef.png",
                    "septieme-chef.png",
                    "huitieme-chef.png"
            };
        }
        indexDialogue = 0;
        afficherTexteAnime();
    }

    private void afficherTexteAnime() {
        if (timeline != null) timeline.stop();

        String imagePath = getClass().getResource(imagesActuelles[indexDialogue]).toExternalForm();
        imageChef.setImage(new Image(imagePath));

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