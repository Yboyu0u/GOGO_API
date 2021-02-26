package com.gogo.GoGo.repository;

import com.gogo.GoGo.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment,Long> {

}

