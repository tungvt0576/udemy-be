package com.team47.udemybackend.controllers;

import com.team47.udemybackend.dto.DiscussionDTO;
import com.team47.udemybackend.exception.DiscussionNotFoundException;
import com.team47.udemybackend.exception.UserNotFoundException;
import com.team47.udemybackend.service.DiscussionService;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/v1")
@Data
public class DiscussionController {
    private final DiscussionService discussionService;

    public DiscussionController(DiscussionService discussionService) {
        this.discussionService = discussionService;
    }

    @GetMapping("/discussion/{discussID}")
    public ResponseEntity<DiscussionDTO> getDisCussById(@PathVariable Integer discussID) throws DiscussionNotFoundException {
        return new ResponseEntity<>(discussionService.findDiscussionsById(discussID), HttpStatus.OK);
    }
    @GetMapping("/discussion/{lectureID}/{userID}")
    public ResponseEntity<DiscussionDTO> getDiscussByLectureIdAndUserId(@PathVariable Integer lectureID, @PathVariable Integer userID) throws DiscussionNotFoundException {
        return new ResponseEntity<>(discussionService.findDiscussionsByLectureIdAndUserId(lectureID, userID), HttpStatus.OK);
    }
    @DeleteMapping("/discussion/{discussionId}")
    public ResponseEntity<DiscussionDTO> delete(@PathVariable Integer discussionId) throws DiscussionNotFoundException {
        return new ResponseEntity<>(discussionService.deleteDiscussionById(discussionId), HttpStatus.OK);
    }
    @PutMapping("/discussion/{discussID}")
    public ResponseEntity<DiscussionDTO> updateDiscuss(@PathVariable Integer discussID, @RequestBody DiscussionDTO discussionDTO) throws DiscussionNotFoundException {
        return new ResponseEntity<>(discussionService.updateDiscussionById(discussID, discussionDTO), HttpStatus.OK);
    }
    @PostMapping("/discussion/{lectureID}/{userID}")
    public ResponseEntity<DiscussionDTO> createNewDiscuss(@PathVariable Integer lectureID, @PathVariable Integer userID, @RequestBody DiscussionDTO discussionDTO) throws UserNotFoundException {
        return new ResponseEntity<>(discussionService.createDiscussion(lectureID, userID, discussionDTO), HttpStatus.OK);
    }
}
