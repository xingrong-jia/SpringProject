package com.jiaxingrong.service;

import com.jiaxingrong.model.Comment;
import com.jiaxingrong.model.Laypage;

import java.util.Map;

public interface CommentService {
    Map list(Laypage laypage);

    boolean reply(Comment comment);

    boolean deleteComment(Comment comment);
}
