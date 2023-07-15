package com.sulsulmarket.sulsul.reviewReply.dao;

import com.sulsulmarket.sulsul.reviewReply.dto.ReviewReplyDTO;
import com.sulsulmarket.sulsul.reviewReply.mapper.ReviewReplyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ReviewReplyDao implements ReviewReplyMapper {

    @Autowired
    private ReviewReplyMapper reviewReplyMapper;

    @Override
    public int reviewReplyAdd(ReviewReplyDTO reviewReplyDTO) {
        return reviewReplyMapper.reviewReplyAdd(reviewReplyDTO);
    }
}
