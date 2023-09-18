package com.sulsulmarket.sulsul.qna.mapper;

import com.sulsulmarket.sulsul.dto.qna.Qna;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface QnaMapper {
    int qnaWrite(Qna qnaDTO);

    Qna getQnaByQnaNo(int qnaNo);

    int qnaUpdate(Qna qnaDTO);

    int qnaUpdateStatus(int qnaNo);
}
