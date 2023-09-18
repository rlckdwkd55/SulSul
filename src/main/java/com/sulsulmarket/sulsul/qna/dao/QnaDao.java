package com.sulsulmarket.sulsul.qna.dao;

import com.sulsulmarket.sulsul.dto.qna.Qna;
import com.sulsulmarket.sulsul.qna.mapper.QnaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class QnaDao implements QnaMapper {

    @Autowired
    private QnaMapper qnaMapper;

    @Override
    public int qnaWrite(Qna qnaDTO) {
        return qnaMapper.qnaWrite(qnaDTO);
    }

    @Override
    public Qna getQnaByQnaNo(int qnaNo) {
        return qnaMapper.getQnaByQnaNo(qnaNo);
    }

    @Override
    public int qnaUpdate(Qna qnaDTO) {
        return qnaMapper.qnaUpdate(qnaDTO);
    }

    @Override
    public int qnaUpdateStatus(int qnaNo) {
        return qnaMapper.qnaUpdateStatus(qnaNo);
    }
}
