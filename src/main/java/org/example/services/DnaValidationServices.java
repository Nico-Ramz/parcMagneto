package org.example.services;

import org.example.entities.DnaValidation;
import org.example.letter.DNALetters;
import org.example.repositories.DnaValidationRepository;
import org.example.utility.MutantProperties;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class DnaValidationServices {

    private  DnaValidationRepository dnaValidationRepository;

    public DnaValidationServices(DnaValidationRepository dnaValidationRepository) {
        this.dnaValidationRepository = dnaValidationRepository;
    }

    public  boolean isMutant(String[] dna) {
        if (!isDNAChainValid(dna)) return false;

        Optional<DnaValidation> dnaValidationBD = dnaValidationRepository.findByDna(dna);
        if (dnaValidationBD.isPresent()) {
            return dnaValidationBD.get().isMutant();
        }

        long numberSequenceMutantMatch = countMatchMutantSequence(getDnaChains(dna));
        boolean isMutant = numberSequenceMutantMatch >= MutantProperties.DEFAULT_COUNT_SEQUENCES_MATCH_MUTANT;
        dnaValidationRepository.save(DnaValidation.builder().dna(dna).isMutant(isMutant).build());

        return isMutant;
    }

    private  boolean isDNAChainValid(String[] dna) {
        var dnaBase = Arrays.stream(DNALetters.values())
                .map(Enum::name)
                .collect(Collectors.joining());
        String regex = String.join("", ".*[^", dnaBase, "].*");
        return Arrays.stream(dna).allMatch(dnaChain -> dnaChain.length() == dna.length)
                && !String.join("", dna).toUpperCase().matches(regex);
    }

    private  long countMatchMutantSequence(List<String[]> dnaChainsList) {
        return dnaChainsList.stream()
                .flatMap(Arrays::stream)
                .filter(this::hasMutantSequence)
                .count();
    }

    private  boolean hasMutantSequence(String sequence) {
        for (int i = 0; i <= sequence.length() - MutantProperties.DEFAULT_SEQUENCE_SIZE_MUTANT; i++) {
            char currentChar = sequence.charAt(i);
            boolean isMutant = true;
            for (int j = 1; j < MutantProperties.DEFAULT_SEQUENCE_SIZE_MUTANT; j++) {
                if (sequence.charAt(i + j) != currentChar) {
                    isMutant = false;
                    break;
                }
            }
            if (isMutant) return true;
        }
        return false;
    }

    private List<String[]> getDnaChains(String[] dnaHorizontal) {
        int n = dnaHorizontal.length;
        String[] dnaVertical = new String[n];
        List<String[]> dnaChainsList = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dnaVertical[j] = (dnaVertical[j] == null ? "" : dnaVertical[j]) + dnaHorizontal[i].charAt(j);
            }
        }

        dnaChainsList.add(dnaHorizontal);
        dnaChainsList.add(dnaVertical);
        dnaChainsList.addAll(getDiagonals(dnaHorizontal, n));

        return dnaChainsList;
    }

    private List<String[]> getDiagonals(String[] dna, int n) {
        List<String[]> diagonals = new ArrayList<>();

        for (int i = 0; i <= n - 4; i++) {
            diagonals.add(extractDiagonal(dna, i, 0, 1, 1, n));
            if (i > 0) diagonals.add(extractDiagonal(dna, 0, i, 1, 1, n));
        }

        for (int i = 0; i <= n - 4; i++) {
            diagonals.add(extractDiagonal(dna, i, n - 1, 1, -1, n));
            if (i > 0) diagonals.add(extractDiagonal(dna, 0, n - 1 - i, 1, -1, n));
        }

        return diagonals;
    }

    private String[] extractDiagonal(String[] dna, int startX, int startY, int dx, int dy, int n) {
        StringBuilder diagonal = new StringBuilder();
        int x = startX;
        int y = startY;
        while (x < n && y < n && y >= 0) {
            diagonal.append(dna[x].charAt(y));
            x += dx;
            y += dy;
        }
        return new String[]{diagonal.toString()};
    }
}
