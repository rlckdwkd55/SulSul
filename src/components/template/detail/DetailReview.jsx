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

const ReviewWrap = styled.div`
  > div:nth-child(1) {
    background-color: lightgray;
    padding: 7px 20px;
    border-radius: 7px;

    display: flex;
    justify-content: space-between;

    > div:nth-child(2) span {
      margin-left: 10px;
    }
  }
  > div:nth-child(2) {
    padding: 20px;
  }
`;

const DetailReview = (props) => {
  const [ reviewList, setReviewList ] = useState([]);
  const [ count, setCount ] = useState(0);
  const [currentPage, setCurrentPage] = useState(1);
  const [postPerPage] = useState(5);
  const [indexOfLastPost, setIndexOfLastPost] = useState(0);
  const [indexOfFirstPost, setIndexOfFirstPost] = useState(0);
  const [currentPosts, setCurrentPosts] = useState(0);

  useEffect(() => {
    // get review list

    setReviewList([
      {
        userName: '임기창',
        reviewRate: 5,
        date: '2023.05.13',
        reviewContent: '포장상태도 좋고 배송도 빠르네요. 맛도 외국 브랜드보다 더 만족스럽네요. 자주 시킬게요!'
      },
      {
        userName: '임기창',
        reviewRate: 5,
        date: '2023.05.13',
        reviewContent: '포장상태도 좋고 배송도 빠르네요. 맛도 외국 브랜드보다 더 만족스럽네요. 자주 시킬게요!'
      },
      {
        userName: '임기창',
        reviewRate: 5,
        date: '2023.05.13',
        reviewContent: '포장상태도 좋고 배송도 빠르네요. 맛도 외국 브랜드보다 더 만족스럽네요. 자주 시킬게요!'
      },
      {
        userName: '임기창',
        reviewRate: 5,
        date: '2023.05.13',
        reviewContent: '포장상태도 좋고 배송도 빠르네요. 맛도 외국 브랜드보다 더 만족스럽네요. 자주 시킬게요!'
      },
      {
        userName: '임기창',
        reviewRate: 5,
        date: '2023.05.13',
        reviewContent: '포장상태도 좋고 배송도 빠르네요. 맛도 외국 브랜드보다 더 만족스럽네요. 자주 시킬게요!'
      },
      {
        userName: '임기창',
        reviewRate: 5,
        date: '2023.05.13',
        reviewContent: '포장상태도 좋고 배송도 빠르네요. 맛도 외국 브랜드보다 더 만족스럽네요. 자주 시킬게요!'
      },
      {
        userName: '임기창',
        reviewRate: 5,
        date: '2023.05.13',
        reviewContent: '포장상태도 좋고 배송도 빠르네요. 맛도 외국 브랜드보다 더 만족스럽네요. 자주 시킬게요!'
      },
    ]);

    setCount(reviewList.length);
    setIndexOfLastPost(currentPage * postPerPage);
    setIndexOfFirstPost(indexOfLastPost - postPerPage);
    setCurrentPosts(reviewList.slice(indexOfFirstPost, indexOfLastPost));
  }, [currentPage, indexOfLastPost, indexOfFirstPost, postPerPage]);

  const setPage = (error) => {
    setCurrentPage(error);
  }

  return (
    <Wrap>
      <h2>상품 후기</h2>
      {currentPosts && reviewList.length > 0 ? (
        currentPosts.map((data, idx) => (
          <ReviewWrap key={idx}> 
            {/* reivew 개발 되면 key 값 변경 필요 */}
            <div>
              <div>
                <span>{data.userName}</span>
              </div>
              <div>
                <span>{data.reviewRate}</span>
                <span>{data.date}</span>
              </div>
            </div>
            <div>
              <p>
                {data.reviewContent}
              </p>
            </div>
          </ReviewWrap>
        ))
      ) : (
        <div>등록 된 후기가 없습니다.</div>
      )}
      <Paging page={currentPage} count={count} setPage={setPage}/>
    </Wrap>
  )
}

export default DetailReview;