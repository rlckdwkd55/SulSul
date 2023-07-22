package com.sulsulmarket.sulsul.qna.dao;

import com.sulsulmarket.sulsul.qna.dto.QnaDTO;
import com.sulsulmarket.sulsul.qna.mapper.QnaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class QnaDao implements QnaMapper {

    @Autowired
    private QnaMapper qnaMapper;

    @Override
    public int qnaWrite(QnaDTO qnaDTO) {
        return qnaMapper.qnaWrite(qnaDTO);
    }

    @Override
    public QnaDTO getQnaByQnaNo(int qnaNo) {
        return qnaMapper.getQnaByQnaNo(qnaNo);
    }

    @Override
    public int qnaUpdate(QnaDTO qnaDTO) {
        return qnaMapper.qnaUpdate(qnaDTO);
    }

    @Override
    public int qnaUpdateStatus(int qnaNo) {
        return qnaMapper.qnaUpdateStatus(qnaNo);
    }
}
