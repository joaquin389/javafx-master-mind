package com.example.javafxmastermindcontrole;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class MastermindController {

    @FXML
    private Button btnCouleur0;
    @FXML
    private Button btnCouleur1;
    @FXML
    private Button btnCouleur2;
    @FXML
    private Button btnCouleur3;

    @FXML
    private Label labelTentatives;
    @FXML
    private Label labelBienPlacees;
    @FXML
    private Label labelBonnesCouleurs;

    private MastermindModele modele;
    private int[] proposition;
    private String[] couleursCSS;

    @FXML
    public void initialize() {
        modele = new MastermindModele();
        proposition = new int[]{0, 0, 0, 0};
        couleursCSS = new String[]{
                "-fx-background-color: red;",
                "-fx-background-color: green;",
                "-fx-background-color: blue;",
                "-fx-background-color: yellow;",
                "-fx-background-color: orange;"
        };
        mettreAJourBoutons();
    }

    @FXML
    public void changerCouleur0() {
        proposition[0] = (proposition[0] + 1) % 5;
        mettreAJourBoutons();
    }

    @FXML
    public void changerCouleur1() {
        proposition[1] = (proposition[1] + 1) % 5;
        mettreAJourBoutons();
    }

    @FXML
    public void changerCouleur2() {
        proposition[2] = (proposition[2] + 1) % 5;
        mettreAJourBoutons();
    }

    @FXML
    public void changerCouleur3() {
        proposition[3] = (proposition[3] + 1) % 5;
        mettreAJourBoutons();
    }

    private void mettreAJourBoutons() {
        btnCouleur0.setStyle(couleursCSS[proposition[0]]);
        btnCouleur1.setStyle(couleursCSS[proposition[1]]);
        btnCouleur2.setStyle(couleursCSS[proposition[2]]);
        btnCouleur3.setStyle(couleursCSS[proposition[3]]);
    }

    @FXML
    public void validerProposition() throws IOException {
        int[] resultat = modele.verifierProposition(proposition);

        labelBienPlacees.setText(String.valueOf(resultat[0]));
        labelBonnesCouleurs.setText(String.valueOf(resultat[1]));
        labelTentatives.setText("Tentatives restantes : " + String.valueOf(modele.getTentativesRestantes()));

        if (resultat[0] == 4) {
            chargerFin(true);
        } else if (modele.getTentativesRestantes() <= 0) {
            chargerFin(false);
        }
    }

    private void chargerFin(boolean victoire) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("dialogue.fxml"));
        Parent root = loader.load();
        DialogueController ctrl = loader.getController();
        ctrl.setModeFin(victoire);
        Stage stage = (Stage) labelTentatives.getScene().getWindow();
        stage.setScene(new Scene(root, 600, 500));
    }
}