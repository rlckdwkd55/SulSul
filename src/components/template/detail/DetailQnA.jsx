import { useState, useEffect } from "react";
import axios from "axios";
import Paging from "../../module/Paging";
import styled from "styled-components";

const Wrap = styled.div`
  border-top: 1px solid lightgray;
  margin-top: 30px;
  padding: 30px 0;

  > h2 {
    margin-bottom: 20px;
  } 
`;
const QnAWrap = styled.div`
`;

const DetailQnA = (props) => {
  const [ qnaList, setQnaList ] = useState([]);
  const [ count, setCount ] = useState(0);
  const [currentPage, setCurrentPage] = useState(1);
  const [postPerPage] = useState(5);
  const [indexOfLastPost, setIndexOfLastPost] = useState(0);
  const [indexOfFirstPost, setIndexOfFirstPost] = useState(0);
  const [currentPosts, setCurrentPosts] = useState(0);

  useEffect(() => {
    // get Qna list

    setQnaList([
      
    ]);

    setCount(qnaList.length);
    setIndexOfLastPost(currentPage * postPerPage);
    setIndexOfFirstPost(indexOfLastPost - postPerPage);
    setCurrentPosts(qnaList.slice(indexOfFirstPost, indexOfLastPost));
  }, [currentPage, indexOfLastPost, indexOfFirstPost, postPerPage]);

  const setPage = (error) => {
    setCurrentPage(error);
  }

  return (
    <Wrap>
      <h2>상품 문의</h2>
      {currentPosts && qnaList.length > 0 ? (
        currentPosts.map((data, idx) => (
        <QnAWrap key={idx}>
            {/* qna 개발 되면 key 값 변경 필요 */}
        </QnAWrap>
        ))
      ) : (
        <div>등록 된 문의가 없습니다.</div>
      )}      
      <Paging page={currentPage} count={count} setPage={setPage}/>
    </Wrap>
  )
}

export default DetailQnA;