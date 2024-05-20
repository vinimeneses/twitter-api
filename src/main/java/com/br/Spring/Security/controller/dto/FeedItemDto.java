package com.br.Spring.Security.controller.dto;

public record FeedItemDto(long tweetId,
                           String content,
                          String username) {
}
