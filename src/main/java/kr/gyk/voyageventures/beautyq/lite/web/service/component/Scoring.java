package kr.gyk.voyageventures.beautyq.lite.web.service.component;

import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Component
public class Scoring {

    public int getMatchingScore (int [][] stCosmetic, int [][] stUser, Set<String> tagCosmetic, Set<String> tagUser, int scoreError) {
        double scoreMatching = 100 - getSimEuclidean((int) Arrays.stream(stCosmetic[0]).count(), stCosmetic[0], stUser[0]) / 2;
        double scoreTag = getSimJaccard(tagCosmetic, tagUser) * 10;

        int scoreTotal = (int) Math.floor(Math.floor(scoreMatching / 10) * 10 + scoreTag + scoreError);
        if (scoreTotal > 100) scoreTotal = 100;

        return scoreTotal;
    }

    public int getScoreError () {
        return (int)((Math.random() * 6) - 3);
    }

    private double getSimEuclidean (int n, int [] matrixA, int [] matrixB) {
        double similarity = 0.0;

        for (int i = 0; i < n; i++) if (matrixA[i] - matrixB[i] != 0) similarity += Math.pow(Math.abs(matrixA[i] - matrixB[i]), 2);
        similarity = Math.sqrt(similarity);

        return similarity;
    }

    private double getSimJaccard (Set<String> setA, Set<String> setB) {
        Set<String> unionSet = new HashSet<>(setA);
        unionSet.addAll(setB);

        Set<String> intersectionSet = new HashSet<>(setA);
        intersectionSet.retainAll(setB);

        return (double) unionSet.size() / intersectionSet.size();

    }

}