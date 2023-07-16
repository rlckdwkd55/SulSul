package com.sulsulmarket.sulsul.reviewReply.mapper;

import com.sulsulmarket.sulsul.reviewReply.dto.ReviewReplyDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReviewReplyMapper {
    int reviewReplyAdd(ReviewReplyDTO reviewReplyDTO);

    List<ReviewReplyDTO> getReviewReply(int reviewNo);
}
