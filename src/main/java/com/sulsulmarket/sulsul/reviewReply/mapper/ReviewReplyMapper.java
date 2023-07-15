package com.sulsulmarket.sulsul.reviewReply.mapper;

import com.sulsulmarket.sulsul.reviewReply.dto.ReviewReplyDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ReviewReplyMapper {
    int reviewReplyAdd(ReviewReplyDTO reviewReplyDTO);
}
