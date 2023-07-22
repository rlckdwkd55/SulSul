package com.sulsulmarket.sulsul.qna.mapper;

import com.sulsulmarket.sulsul.qna.dto.QnaDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface QnaMapper {
    int qnaWrite(QnaDTO qnaDTO);

    QnaDTO getQnaByQnaNo(int qnaNo);

    int qnaUpdate(QnaDTO qnaDTO);

    int qnaUpdateStatus(int qnaNo);
}
