package org.example.controllers;


import lombok.RequiredArgsConstructor;
import org.example.dto.DNAStatsDTO;
import org.example.services.StatsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("stats")
public class StatsController {

    private final StatsService statsService;

    @GetMapping()
    public ResponseEntity<DNAStatsDTO> getStats() {
        DNAStatsDTO dnaStatsDTO = statsService.getStats();
        return ResponseEntity.ok(dnaStatsDTO);
    }
}
