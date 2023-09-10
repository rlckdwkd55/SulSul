package com.sulsulmarket.sulsul.qna.service;

import com.sulsulmarket.sulsul.Util.SulSulUtil;
import com.sulsulmarket.sulsul.dto.member.Member;
import com.sulsulmarket.sulsul.member.dao.MemberDao;
import com.sulsulmarket.sulsul.dto.qna.Qna;
import com.sulsulmarket.sulsul.qna.dao.QnaDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@Slf4j
public class QnaService {

    @Autowired
    private QnaDao qnaDao;

    @Autowired
    private MemberDao memberDao;

    @Transactional
    public void qnaWrite(Qna qnaDTO) {

        if(qnaDTO == null || Objects.isNull(qnaDTO)) {
            log.error("Qna Data is Null");
            throw new NullPointerException("문의 데이터가 없습니다.");
        }

        Member member = memberDao.getMemberByEmail(qnaDTO.getMEMBER_EMAIL());

        if (member == null || Objects.isNull(member)) {
            log.error("Member Is Not Found");
            throw new NullPointerException("회원 정보를 찾을 수 없습니다.");
        }
        qnaDTO.setQNA_NO(SulSulUtil.getNextSequence().intValue());
//        qnaDTO.setDEL_STATUS("N");
        log.info("Qna Data Check ==>> [{}]", qnaDTO.toString());
//        log.info("QNaNO ==> CHeck {}", qnaDTO.getDEL_STATUS());

        try {
            qnaDao.qnaWrite(qnaDTO);
        } catch (Exception e) {
            log.error("Exception : {}", e);
        }
        log.info("Qna Write Is Success ! ! !");
    }

    public Qna getQnaByQnaNo(int qnaNo) {

        if (qnaNo <= 0) {
            log.error("Qna No Is Error ! ! !");
            throw new IllegalArgumentException("문의 번호는 0보다 큰 값이여야 합니다.");
        }
        Qna qnaDTO = qnaDao.getQnaByQnaNo(qnaNo);
        if (qnaDTO == null || Objects.isNull(qnaDTO)) {
            log.error("Qna Data Is Null By Qna No");
            throw new NullPointerException("문의 번호로 해당 글을 찾을 수가 없습니다.");
        }
        return qnaDTO;
    }

    @Transactional
    public void qnaUpdate(Qna qnaDTO) {

        if (qnaDTO == null || Objects.isNull(qnaDTO)) {
            log.error("Qna Update Data Is Null");
            throw new NullPointerException("상품 문의 수정할 데이터가 없습니다.");
        }

        try {
            log.info("Qna Data Check ==>> [{}]", qnaDTO.toString());
            Qna qnaData = getQnaByQnaNo(qnaDTO.getQNA_NO());
            log.info("Find Qna Data By QnaNo {}", qnaData.toString());
        } catch (Exception e) {
            log.error("Qna No Check Exception : {}", e);
        }

        qnaDao.qnaUpdate(qnaDTO);
    }

    @Transactional
    public void qnaStatusUpdate(int qnaNo) {

        qnaDao.qnaUpdateStatus(qnaNo);
    }
}
