package kr.gyk.voyageventures.beautyq.lite.web.service.service.web;

import kr.gyk.voyageventures.beautyq.lite.web.service.dto.web.ExpoDiagnosisResultDTO;
import kr.gyk.voyageventures.beautyq.lite.web.service.form.DiagnosisTestForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class ExpoDiagnosisService {


    public ExpoDiagnosisResultDTO getSkinTypeResult(DiagnosisTestForm diagnosisTestForm) {

        String baumannSkintype = (diagnosisTestForm.getQuestion1() ? "D" : "O")
                            + (diagnosisTestForm.getQuestion2() ? "S" : "R")
                            + (diagnosisTestForm.getQuestion3() ? "P" : "N")
                            + (diagnosisTestForm.getQuestion4() ? "W" : "T");
        String skintype = diagnosisTestForm.getQuestion2() ? "민감성피부" : diagnosisTestForm.getQuestion1() ? "건성피부" : "지성피부";

        Map<Integer, Integer> score = new HashMap<>();
        for (int i = 0; i < 5; i++) score.put(i, 0);
        if (diagnosisTestForm.getQuestion1()) {
            score.put(2, score.get(2) + 3);
            score.put(3, score.get(3) + 5);
        }
        if (diagnosisTestForm.getQuestion2()) score.put(4, score.get(4) + 5);
        else score.put(0, score.get(0) + 2);
        if (diagnosisTestForm.getQuestion3()) score.put(1, score.get(1) + 5);
        else score.put(0, score.get(0) + 2);
        if (diagnosisTestForm.getQuestion4()) score.put(2, score.get(2) + 5);
        else score.put(3, score.get(3) + 2);
        List<Integer> scoreKeySet = new ArrayList<>(score.keySet());
        scoreKeySet.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return score.get(o1).compareTo(score.get(o2));
            }
        });
        List<Integer> needs = new ArrayList<>();
        needs.add(scoreKeySet.get(0));
        needs.add(scoreKeySet.get(1));
        needs.add(scoreKeySet.get(2));

        return ExpoDiagnosisResultDTO.builder()
                .baumannSkintype(baumannSkintype)
                .skintype(skintype)
                .needs(needs)
                .build();
    }

}
