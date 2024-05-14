package com.br.Spring.Security.repository;

import com.br.Spring.Security.entities.Role;
import com.br.Spring.Security.entities.Tweet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TweetRepository extends JpaRepository<Tweet, Long> {
}
