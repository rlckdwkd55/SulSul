package com.sulsulmarket.sulsul.reviewReply.mapper;

import com.sulsulmarket.sulsul.dto.reviewReply.ReviewReply;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReviewReplyMapper {
    int reviewReplyAdd(ReviewReply reviewReply);

    List<ReviewReply> getReviewReply(int reviewNo);
}
