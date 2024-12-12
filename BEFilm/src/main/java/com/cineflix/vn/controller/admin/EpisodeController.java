package com.cineflix.vn.controller.admin;

import com.cineflix.vn.model.episode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/episode")
public class EpisodeController {

    @GetMapping
    public ResponseEntity<List<episode>> getEpisode(@RequestParam(required = false) String username ) {


        return null;
    }
}
