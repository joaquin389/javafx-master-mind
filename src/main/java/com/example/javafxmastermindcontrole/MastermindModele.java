package com.example.javafxmastermindcontrole;

import java.util.Random;

public class MastermindModele {
    private int[] combinaisonSecrete;
    private int tentativesRestantes;

    public MastermindModele() {
        this.combinaisonSecrete = new int[4];
        Random random = new Random();
        // Génère 4 nombres aléatoires entre 0 (inclus) et 5 (exclu)
        for (int i = 0; i < 4; i++) {
            this.combinaisonSecrete[i] = random.nextInt(5);
        }
        this.tentativesRestantes = 10;
    }

    public int getTentativesRestantes() {
        return tentativesRestantes;
    }

    public int[] verifierProposition(int[] proposition) {
        int bienPlaces = 0;
        int bonnesCouleurs = 0;
        boolean[] utiliseSecrete = new boolean[4];
        boolean[] utiliseProp = new boolean[4];

        for (int i = 0; i < 4; i++) {
            if (proposition[i] == combinaisonSecrete[i]) {
                bienPlaces++;
                utiliseSecrete[i] = true;
                utiliseProp[i] = true;
            }
        }

        for (int i = 0; i < 4; i++) {
            if (!utiliseProp[i]) {
                for (int j = 0; j < 4; j++) {
                    if (!utiliseSecrete[j] && proposition[i] == combinaisonSecrete[j]) {
                        bonnesCouleurs++;
                        utiliseSecrete[j] = true;
                        break;
                    }
                }
            }
        }

        tentativesRestantes--;
        return new int[]{bienPlaces, bonnesCouleurs};
    }
}