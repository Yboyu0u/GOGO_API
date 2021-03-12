package com.gogo.GoGo.repository.community;

import com.gogo.GoGo.domain.community.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommunityCommentRepository extends JpaRepository<Comment,Long> {

}

