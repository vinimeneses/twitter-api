package com.br.Spring.Security.controller;

import com.br.Spring.Security.controller.dto.CreateTweetDto;
import com.br.Spring.Security.controller.dto.FeedDto;
import com.br.Spring.Security.controller.dto.FeedItemDto;
import com.br.Spring.Security.entities.Role;
import com.br.Spring.Security.entities.Tweet;
import com.br.Spring.Security.repository.TweetRepository;
import com.br.Spring.Security.repository.UserRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@RestController
public class TweetController {

    private final TweetRepository tweetRepository;

    private final UserRepository userRepository;

    public TweetController(TweetRepository tweetRepository,
                           UserRepository userRepository) {
        this.tweetRepository = tweetRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/feed")
    public ResponseEntity<FeedDto> getFeed(@RequestParam(value = "page", defaultValue = "0") int page,
                                           @RequestParam(value = "size", defaultValue = "10") int size) {
        var tweets = tweetRepository.findAll(PageRequest.of(page,
                size,
                Sort.Direction.DESC,
                "creationTimeStamp"))
                .map(tweet -> new FeedItemDto(tweet.getTweetId(),
                        tweet.getContent(),
                        tweet.getUser().getUsername()));

        return ResponseEntity.ok(new FeedDto(tweets.getContent(),
                page,
                size,
                tweets.getTotalPages(),
                tweets.getTotalElements()));
    }

    @PostMapping("/tweets")
    public ResponseEntity<Void> newTweet(@RequestBody CreateTweetDto dto,
                                         JwtAuthenticationToken token) {
        var user = userRepository.findById(UUID.fromString(token.getName()));

        var tweet = new Tweet();
        tweet.setUser(user.get());
        tweet.setContent(dto.content());

        tweetRepository.save(tweet);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/tweets/{id}")
    public ResponseEntity<Void> deleteTweet(@PathVariable("id") Long tweetId,
                                            JwtAuthenticationToken token) {
        var user = userRepository.findById(UUID.fromString(token.getName()));
        var tweet = tweetRepository.findById(tweetId)
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        var isAdmin = user.get().getRoles()
                .stream().anyMatch(role -> role.getName().equalsIgnoreCase(Role.Values.ADMIN.name()));

        if (isAdmin || tweet.getUser().getUserId().equals(UUID.fromString(token.getName()))) {
            tweetRepository.deleteById(tweetId);
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        return ResponseEntity.ok().build();
    }
}
